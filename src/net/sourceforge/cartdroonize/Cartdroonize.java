package net.sourceforge.cartdroonize;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;


public class Cartdroonize {
	
	private Bitmap image_input;
	private Bitmap image_output;
	private int image_max_size = 400;
	
	public Cartdroonize(){
		/** 
		 * Instantiates Cartdroonize.
		 * @author Jascha Casadio 
		 * @version 0.20130819
		 * @param image_input_filename: The input image filename.
		 */
		//image_input = readImage(image_input_filename);
	}
	
	public Cartdroonize(Bitmap image){
		image_input = image;
	}
	
	public Cartdroonize(int max_size){
		setImageMaxSize(max_size);
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
	
	public Bitmap getImageInput(int max_size){
		/** 
		 * Getter: returns the rescaled Bitmap of the input image.
		 * @author Jascha Casadio 
		 * @version 0.20130819
		 * @param max_size: The max size of the image.
		 * @return The rescaled Bitmap of the input image.
		 */
		return rescaleImage(image_input, max_size);
	}
	
	public Bitmap getImageOutput(int max_size){
		/** 
		 * Getter: returns the rescaled Bitmap of the output image.
		 * @author Jascha Casadio 
		 * @version 0.20130819
		 * @param max_size: The max size of the image.
		 * @return The rescaled Bitmap of the output (posterized) image.
		 */
		return rescaleImage(image_output, max_size);
	}
	
	public int getImageMaxSize(){
		return image_max_size;
	}
	
	private void posterize(int posterization_strength){
		/** 
		 * Posterizes the input image with the given strength.
		 * @author Jascha Casadio 
		 * @version 0.20130819
		 * @param posterization_strength: The strength of the posterization effect.
		 */
	}
	
	private Bitmap readImage(String image_input_filename){
		/** 
		 * Reads and returns the image whose path was provided as input.
		 * @author Jascha Casadio 
		 * @version 0.20130819
		 * @param image_input_filename: The path of the image filename.
		 * @return The Bitmap of the image.
		 */
		File image_input_file = new File(image_input_filename);
		return BitmapFactory.decodeFile(image_input_file.getAbsolutePath());
	}
	
	public Bitmap rescaleImage(Bitmap image, int max_size){
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
	
	public void setImageMaxSize(int new_max_size){
		image_max_size = new_max_size;
	}
	
}
