package net.sourceforge.cartdroonize;

/**
 * This class provides methods used to process and manipulate an image file so 
 * that it can be automatically turned into a cartoon like one.
 * @author Jascha Casadio
 * @version 0.20130825
 */

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;


public class Cartdroonize {
	
	private Bitmap image_input; 	/** The Bitmap that holds the input file 	*/
	private Bitmap image_output; 	/** The Bitmap that holds the output file 	*/
	private int image_max_size;		/** The max size the image can have when 
										displayed in the device. Does not 
										affect the real size of the image 		*/
	
	public Cartdroonize(Bitmap in_image_input){
		/** 
		 * Instantiates Cartdroonize with a mutable Bitmap.
		 * @author Jascha Casadio 
		 * @version 0.20130821
		 * @param in_image_input: The mutable input Bitmap.
		 */
		this(in_image_input, 400);
	}
	
	public Cartdroonize(Bitmap in_image_input, int in_image_max_size){
		/** 
		 * Instantiates Cartdroonize with a mutable Bitmap and max_size.
		 * @author Jascha Casadio 
		 * @version 0.20130821
		 * @param in_image_input: The mutable input Bitmap.
		 * @param in_image_max_size: The max size of the image in the ImageView.
		 */
		image_input = in_image_input;
		image_output = copyBitmap(image_input);
		image_max_size = in_image_max_size;
	}
	
	public void blackAndWhite(){
		/** 
		 * Turns the image into grayscale using the lightness method.
		 * @author Jascha Casadio 
		 * @version 0.20130825
		 */
		for(int i = 0; i < image_input.getWidth(); i++){
			for(int j = 0;j < image_input.getHeight(); j++){  
				int pixel = image_input.getPixel(i, j);
				int red_current = Color.red(pixel);
				int green_current = Color.green(pixel);
				int blue_current = Color.blue(pixel);
				int max = Math.max(red_current, Math.max(green_current, blue_current));
				int min = Math.min(red_current, Math.min(green_current, blue_current));
				int avg = (int)(max + min) / 2;
                image_input.setPixel(i, j, Color.rgb(avg,avg,avg));
			}
		}
	}
	
	private Bitmap clearImage(Bitmap image){
		/**
		 * Clears a Bitmap setting all its pixels to (255,255,255).
		 * @author Jascha Casadio
		 * @version 0.20130823
		 * @input: The mutable Bitmap to clear.
		 * @return The cleared mutable Bitmap.
		 */
		for(int i = 0; i < image.getWidth(); i++){
			for(int j = 0; j < image.getHeight(); j++){
				image.setPixel(i, j, Color.rgb(255, 255, 255));
				}
			}
		return image;
	}
	
	private Bitmap copyBitmap(Bitmap input){
		/**
		 * Returns a mutable copy of the input Bitmap.
		 * @author Jascha Casadio
		 * @versin 0.20130829
		 * @input: The mutable Bitmap to copy.
		 * @return A mutable copy of the input Bitmap.
		 */
		return input.copy(input.getConfig(), true);
	}
	
	private void edgeConvolution(int threshold, int high){
		/** 
		 * input: the_image, out_image, detect_type, threshold, high, bits_per_pixel
		 * Convolutes the image with masks.
		 * @author Jascha Casadio 
		 * @version 0.20130823
		 */
		
		int sum;
		
		int max = 50,
		new_hi = 10,
		new_low = 2;
		
		//mask_0, mask_1, mask_2, mask_3, mask_4, mask_5, mask_6, mask_7 = edgeSetupMask(detect_type);
		int[][]mask_0 = {{ 5,  5, 5},  {-3, 0, -3}, {-3, -3, -3}};
		int[][]mask_1 = {{-3,  5, 5},  {-3, 0,  5}, {-3, -3, -3}};
		int[][]mask_2 = {{-3, -3, 5},  {-3, 0,  5}, {-3, -3,  5}};
		int[][]mask_3 = {{-3, -3, -3}, {-3, 0,  5}, {-3,  5,  5}};
		int[][]mask_4 = {{-3, -3, -3}, {-3, 0, -3}, { 5,  5,  5}};
		int[][]mask_5 = {{-3, -3, -3}, { 5, 0, -3}, { 5,  5, -3}};
		int[][]mask_6 = {{ 5, -3, -3}, { 5, 0, -3}, { 5, -3, -3}};
		int[][]mask_7 = {{ 5,  5, -3}, { 5, 0, -3}, {-3, -3, -3}};
		
		/* clear output image array */
		//this.image_input = clearImage(this.image_input);
		
		for(int i=1; i < image_input.getWidth()-1; i++){
			for(int j=1; j < image_input.getHeight()-1; j++){
		
				/* 0 direction */
				sum = 0;
				for(int a=-1; a<2; a++){
					for(int b=-1; b<2; b++){
						sum = sum + image_input.getPixel(i+a, j+b) * mask_0[a+1][b+1];
					}
				}
		
				if(sum > max){sum = max;}
				if(sum < 0){sum = 0;}	
				if(sum > image_input.getPixel(i, j)){
					image_input.setPixel(i, j, sum);
				}
		
				/* 1 direction */
				sum = 0;
				for(int a=-1; a<2; a++){
					for(int b=-1; b<2; b++){
						sum = sum + image_input.getPixel(i+a, j+b) * mask_1[a+1][b+1];
					}
				}
				
				if(sum > max){sum = max;}
				if(sum < 0){sum = 0;}	
				if(sum > image_input.getPixel(i, j)){
					image_input.setPixel(i, j, sum);
				}
				
				/* 2 direction */
				sum = 0;
				for(int a=-1; a<2; a++){
					for(int b=-1; b<2; b++){
						sum = sum + image_input.getPixel(i+a, j+b) * mask_2[a+1][b+1];
					}
				}
				
				if(sum > max){sum = max;}
				if(sum < 0){sum = 0;}	
				if(sum > image_input.getPixel(i, j)){
					image_input.setPixel(i, j, sum);
				}
				
				/* 3 direction */
				sum = 0;
				for(int a=-1; a<2; a++){
					for(int b=-1; b<2; b++){
						sum = sum + image_input.getPixel(i+a, j+b) * mask_3[a+1][b+1];
					}
				}
				
				if(sum > max){sum = max;}
				if(sum < 0){sum = 0;}	
				if(sum > image_input.getPixel(i, j)){
					image_input.setPixel(i, j, sum);
				}
				
				/* 4 direction */
				sum = 0;
				for(int a=-1; a<2; a++){
					for(int b=-1; b<2; b++){
						sum = sum + image_input.getPixel(i+a, j+b) * mask_4[a+1][b+1];
					}
				}
				
				if(sum > max){sum = max;}
				if(sum < 0){sum = 0;}	
				if(sum > image_input.getPixel(i, j)){
					image_input.setPixel(i, j, sum);
				}
				
				/* 5 direction */
				sum = 0;
				for(int a=-1; a<2; a++){
					for(int b=-1; b<2; b++){
						sum = sum + image_input.getPixel(i+a, j+b) * mask_5[a+1][b+1];
					}
				}
				
				if(sum > max){sum = max;}
				if(sum < 0){sum = 0;}	
				if(sum > image_input.getPixel(i, j)){
					image_input.setPixel(i, j, sum);
				}
				
				/* 6 direction */
				sum = 0;
				for(int a=-1; a<2; a++){
					for(int b=-1; b<2; b++){
						sum = sum + image_input.getPixel(i+a, j+b) * mask_6[a+1][b+1];
					}
				}
				
				if(sum > max){sum = max;}
				if(sum < 0){sum = 0;}	
				if(sum > image_input.getPixel(i, j)){
					image_input.setPixel(i, j, sum);
				}
				
				/* 7 direction */
				sum = 0;
				for(int a=-1; a<2; a++){
					for(int b=-1; b<2; b++){
						sum = sum + image_input.getPixel(i+a, j+b) * mask_7[a+1][b+1];
					}
				}
				
				if(sum > max){sum = max;}
				if(sum < 0){sum = 0;}	
				if(sum > image_input.getPixel(i, j)){
					image_input.setPixel(i, j, sum);
				}
			}
		}
		
		if(threshold == 1){
			for(int i=0; i<image_input.getWidth(); i++){
				for(int j=0; j<image_input.getHeight(); j++){
					if(image_input.getPixel(i, j) > high){
						image_input.setPixel(i, j, new_hi);
						}
					else{
						image_input.setPixel(i, j, new_low);
						}
					}
				}
			}
	} 
	
	public void edgeDetection(){			
		/** 
		 * Detects the edges of the input image.
		 * @author Jascha Casadio 
		 * @version 0.20130822
		 */
		this.edgeConvolution(1,40); //all input
		//this.edgeFixEdges(); //out_image, 1, rows, cols
	}
	
	private void edgeEnhancement(){
		/** 
		 * Enhances the edges of the input image.
		 * @author Jascha Casadio 
		 * @version 0.20130822
		 */
	}
	
	private void edgeFixEdges(){
		/** 
		 * Restores the border of the image after convolution.
		 * @author Jascha Casadio 
		 * @version 0.20130822
		 */
	}
	
	public Bitmap getImageInput(){
		/** 
		 * Getter: returns the Bitmap of the input image.
		 * @author Jascha Casadio 
		 * @version 0.20130821
		 * @return The input Bitmap of the image.
		 */
		return image_input;
	}
	
	public Bitmap getImageOutput(){
		/** 
		 * Getter: returns the Bitmap of the output image.
		 * @author Jascha Casadio 
		 * @version 0.20130821
		 * @return The Bitmap of the output (posterized) image.
		 */
		return image_output;
	}
	
	public Bitmap getRescaledImageInput(){
		/** 
		 * Getter: returns the rescaled Bitmap of the input image.
		 * @author Jascha Casadio 
		 * @version 0.20130820
		 * @return The rescaled Bitmap of the input image.
		 */
		return rescaleImage(image_input, image_max_size);
	}
	
	public Bitmap getRescaledImageOutput(){
		/** 
		 * Getter: returns the rescaled Bitmap of the output image.
		 * @author Jascha Casadio 
		 * @version 0.20130819
		 * @return The rescaled Bitmap of the output (posterized) image.
		 */
		return rescaleImage(image_output, image_max_size);
	}
	
	public void invertColors(){
		/**
		 * Inverts the colors of the given mutable Bitmap.
		 * @author Jascha Casadio
		 * @version 0.20130829
		 * @param image: The input mutable Bitmap.
		 */
		Canvas c = new Canvas(image_input);
		Paint p = new Paint();
		float invert_matrix[] = 	{	
									-1.0f, 0.0f,  0.0f, 1.0f, 1.0f,
							 		0.0f,  -1.0f, 0.0f, 1.0f, 1.0f,
							 		0.0f,  0.0f, -1.0f, 1.0f, 1.0f, 
							 		0.0f,  0.0f,  0.0f, 1.0f, 0.0f
						 			};
		p.setColorFilter(new ColorMatrixColorFilter(new ColorMatrix(invert_matrix)));
		c.drawBitmap(image_input, 0, 0, p);
	}
	
	public void posterize(int posterization_strength){
		/** 
		 * Posterizes the input image with the given strength.
		 * @author Jascha Casadio 
		 * @version 0.20130821
		 * @param posterization_strength: The strength of the posterization effect.
		 */
		for(int i = 0; i < image_input.getWidth(); i++){
			for(int j = 0;j < image_input.getHeight(); j++){  
				int pixel = image_input.getPixel(i, j);
				int red_current = Color.red(pixel);
				int green_current = Color.green(pixel);
				int blue_current = Color.blue(pixel);
				int red_new = (red_current - (red_current % posterization_strength));
				int green_new = (green_current - (green_current % posterization_strength));
				int blue_new = (blue_current - (blue_current % posterization_strength));
                image_input.setPixel(i, j, Color.rgb(red_new, green_new, blue_new));
			}
		}
	}
	
	private Bitmap rescaleImage(Bitmap image, int max_size){
		/** 
		 * Rescales the Bitmap preserving ratio.
		 * @author Jascha Casadio 
		 * @version 0.20130819
		 * @param image: The Bitmap of the image to rescale.
		 * @param max_size: The max size of the image (the larger between height and width).
		 * @return The rescaled Bitmap.
		 */
		int image_width = image.getWidth();
		int image_height = image.getHeight();
		
		int scaled_width = (image_width >= image_height) ? max_size : (int)((float)max_size * ((float)image_width / (float)image_height));
		int scaled_height = (image_height >= image_width) ? max_size : (int)((float)max_size * ((float)image_height / (float)image_width));
		
		Bitmap rescaled_image = Bitmap.createScaledBitmap(image, scaled_width, scaled_height, true);
		return rescaled_image;
	}
	
	public void setImageMaxSize(int in_image_max_size){
		/** 
		 * Changes the max size of the images ti dusplay in the ImageView. This won't affect the size of the image itself.
		 * @author Jascha Casadio 
		 * @version 0.20130820
		 * @param in_image_max_size: The new max size of the image.
		 */
		image_max_size = in_image_max_size;
	}
	
}
