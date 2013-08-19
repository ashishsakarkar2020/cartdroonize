package net.sourceforge.cartdroonize;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;

public class SandBox extends Activity {
	
	private int max_size = 400;
	
	private ImageView picture_init 	= (ImageView) this.findViewById(R.id.picture_init);
	private ImageView picture_final = (ImageView) this.findViewById(R.id.picture_final);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sandbox);
		
		Cartdroonize img = new Cartdroonize("path_to_image");
		picture_init.setImageBitmap(img.getImageInput(max_size));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sandbox, menu);
		return true;
	}

}
