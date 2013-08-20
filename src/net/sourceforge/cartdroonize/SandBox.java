package net.sourceforge.cartdroonize;

import java.io.FileNotFoundException;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SandBox extends Activity implements OnClickListener {
	
	private int max_size = 800;
	Cartdroonize img = new Cartdroonize(max_size);
	//private ImageView picture_init 	= (ImageView) this.findViewById(R.id.picture_init);
	//private ImageView picture_final = (ImageView) this.findViewById(R.id.picture_final);
	ImageView chosenImageView;
	Button choosePicture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sandbox);
		chosenImageView = (ImageView) this.findViewById(R.id.picture_init);
		choosePicture = (Button) this.findViewById(R.id.button_select);
		choosePicture.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		Intent choosePictureIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(choosePictureIntent, 0);
		}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
			super.onActivityResult(requestCode, resultCode, intent);
			
			if (resultCode == RESULT_OK) {
				Uri imageFileUri = intent.getData();
				
				try {
					BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
					Bitmap bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, bmpFactoryOptions);
					bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), null, bmpFactoryOptions);
					bmp=img.rescaleImage(bmp, max_size);
					chosenImageView.setImageBitmap(bmp);
					}
				catch(FileNotFoundException e) {}
				}
		}
}
