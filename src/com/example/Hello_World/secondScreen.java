package com.example.Hello_World;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jacky on 7/12/14.
 */
public class secondScreen extends Activity {

    private Button changeImageButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.secondscreen);
        this.changeImageButton = (Button)this.findViewById(R.id.changeImage);

        this.changeImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = sharedUtils.setupImageIntents(getPackageManager());
                startActivityForResult(intent,sharedUtils.SELECT_IMG_REC);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data!=null){

            sharedData.getInstance().setImageURI(data.getData());
            //startActivity(new Intent(this.getBaseContext(),MyActivity.class));
            finish();
        }
    }
}