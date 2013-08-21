package net.sourceforge.cartdroonize;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;


public class Cartdroonize {
	
	private Bitmap image_input;
	private Bitmap image_output;
	private int image_max_size;
	
	public Cartdroonize(Bitmap in_image_input){
		/** 
		 * Instantiates Cartdroonize with a Bitmap.
		 * @author Jascha Casadio 
		 * @version 0.20130820
		 * @param in_image_input: The input Bitmap.
		 */
		this(in_image_input, 400);
	}
	
	public Cartdroonize(Bitmap in_image_input, int in_image_max_size){
		/** 
		 * Instantiates Cartdroonize with a Bitmap and max_size.
		 * @author Jascha Casadio 
		 * @version 0.20130820
		 * @param in_image_input: The input Bitmap.
		 * @param in_image_max_size: The max size of the image in the ImageView.
		 */
		image_input = in_image_input;
		image_max_size = in_image_max_size;
	}
	
	private void edgeDetection(){
		/** 
		 * Detects the edges of the input image.
		 * @author Jascha Casadio 
		 * @version 0.20130819
		 */
	}
	
	private void edgeEnhancement(){
		/** 
		 * Enhances the edges of the input image.
		 * @author Jascha Casadio 
		 * @version 0.20130819
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
