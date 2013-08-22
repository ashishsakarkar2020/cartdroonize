package net.sourceforge.cartdroonize;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;


public class Cartdroonize {
	
	private Bitmap image_input;
	private Bitmap image_output;
	private int image_max_size;
	
	private class KirschMasks {
		/**
		 * Defines the eight 3x3 Kirsch's masks used to convolute an image.
		 */
		int[][] kirsch_mask_0 = {{ 5,  5, 5},  {-3, 0, -3}, {-3, -3, -3}};
		int[][] kirsch_mask_1 = {{-3,  5, 5},  {-3, 0,  5}, {-3, -3, -3}};
		int[][] kirsch_mask_2 = {{-3, -3, 5},  {-3, 0,  5}, {-3, -3,  5}};
		int[][] kirsch_mask_3 = {{-3, -3, -3}, {-3, 0,  5}, {-3,  5,  5}};
		int[][] kirsch_mask_4 = {{-3, -3, -3}, {-3, 0, -3}, { 5,  5,  5}};
		int[][] kirsch_mask_5 = {{-3, -3, -3}, { 5, 0, -3}, { 5,  5, -3}};
		int[][] kirsch_mask_6 = {{ 5, -3, -3}, { 5, 0, -3}, { 5, -3, -3}};
		int[][] kirsch_mask_7 = {{ 5,  5, -3}, { 5, 0, -3}, {-3, -3, -3}};
	}
	
	private class PrewittMasks {
		/**
		 * Defines the eight 3x3 Prewitt's masks used to convolute an image.
		 */
		int[][] prewitt_mask_0 = {{ 1,  1,  1}, { 1, -2,  1}, {-1, -1, -1}};
		int[][] prewitt_mask_1 = {{ 1,  1,  1}, { 1, -2, -1}, { 1, -1, -1}};
		int[][] prewitt_mask_2 = {{ 1,  1, -1}, { 1, -2, -1}, { 1,  1, -1}};
		int[][] prewitt_mask_3 = {{ 1, -1, -1}, { 1, -2, -1}, { 1,  1,  1}};
		int[][] prewitt_mask_4 = {{-1, -1, -1}, { 1, -2,  1}, { 1,  1,  1}};
		int[][] prewitt_mask_5 = {{-1, -1,  1}, {-1, -2,  1}, { 1,  1,  1}};
		int[][] prewitt_mask_6 = {{-1,  1,  1}, {-1, -2,  1}, {-1,  1,  1}};
		int[][] prewitt_mask_7 = {{ 1,  1,  1}, {-1, -2,  1}, {-1, -1,  1}};
	}
	
	private class SobelMasks {
		/**
		 * Defines the eight 3x3 Sobel's masks used to convolute an image.
		 */
		int[][] sobel_mask_0 = {{ 1,  2,  1},{ 0, 0,  0},{-1, -2, -1}};
		int[][] sobel_mask_1 = {{ 2,  1,  0},{ 1, 0, -1},{ 0, -1, -2}};
		int[][] sobel_mask_2 = {{ 1,  0, -1},{ 2, 0, -2},{ 1,  0, -1}};
		int[][] sobel_mask_3 = {{ 0, -1, -2},{ 1, 0, -1},{ 2,  1,  0}};
		int[][] sobel_mask_4 = {{-1, -2, -1},{ 0, 0,  0},{ 1,  2,  1}};
		int[][] sobel_mask_5 = {{-2, -1,  0},{-1, 0,  1},{ 0,  1,  2}};
		int[][] sobel_mask_6 = {{-1,  0,  1},{-2, 0,  2},{-1,  0,  1}};
		int[][] sobel_mask_7 = {{ 0,  1,  2},{-1, 0,  1},{-2, -1,  0}};
	}
	
	
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
		image_max_size = in_image_max_size;
	}
	
	private void edgeConvolution(){
		/** 
		 * Convolutes the image with masks.
		 * @author Jascha Casadio 
		 * @version 0.20130822
		 */
	}
	
	private void edgeDetection(){
		/** 
		 * Detects the edges of the input image.
		 * @author Jascha Casadio 
		 * @version 0.20130822
		 */
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
		return this.image_input;
	}
	
	public Bitmap getImageOutput(){
		/** 
		 * Getter: returns the Bitmap of the output image.
		 * @author Jascha Casadio 
		 * @version 0.20130821
		 * @return The Bitmap of the output (posterized) image.
		 */
		return this.image_output;
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
	
	public void posterize(int posterization_strength){
		/** 
		 * Posterizes the input image with the given strength.
		 * @author Jascha Casadio 
		 * @version 0.20130821
		 * @param posterization_strength: The strength of the posterization effect.
		 */
		Log.v("Mutable", String.valueOf(this.image_input.isMutable()));
		for(int i = 0; i < this.image_input.getWidth(); i++){
			for(int j = 0;j < this.image_input.getHeight(); j++){  
				int pixel = this.image_input.getPixel(i, j);
				int red_current = Color.red(pixel);
				int green_current = Color.green(pixel);
				int blue_current = Color.blue(pixel);
				int red_new = (red_current - (red_current % posterization_strength));
				int green_new = (green_current - (green_current % posterization_strength));
				int blue_new = (blue_current - (blue_current % posterization_strength));
                this.image_input.setPixel(i, j, Color.rgb(red_new, green_new, blue_new));
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
