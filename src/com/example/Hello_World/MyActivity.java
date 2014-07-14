package com.example.Hello_World;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class MyActivity extends Activity {
    private Button loadButton;
    private Button secondScreenEntrance;
    private TextView imagePath;
    private ImageView selectedImage;


    private final List<Intent> imageIntents = new ArrayList<Intent>();

    private String selectedImagePath;
    private Uri outputFileURI;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        this.setContentView(R.layout.main);
        this.imagePath = (TextView)this.findViewById(R.id.imagePath);
        this.loadButton = (Button)this.findViewById(R.id.loadImage);
        this.secondScreenEntrance = (Button)this.findViewById(R.id.secondScreenEntrance);
        this.selectedImage = (ImageView)this.findViewById(R.id.selectedImage);

        //retrieve image if saved
        if(sharedData.getInstance().getImageURI()!=null){
            selectedImage.setImageURI(sharedData.getInstance().getImageURI());
        }

        //onclick callback registers
        this.loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = sharedUtils.setupImageIntents(getPackageManager());
                startActivityForResult(intent,sharedUtils.SELECT_IMG_REC);

            }
        });
        this.secondScreenEntrance.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),secondScreen.class));
            }

        });

    }
      
    
    public void onActivityResult(int reqCode, int resCode, Intent data){
        if(resCode == RESULT_OK){
            Uri selectedImageURI = null;
            if(reqCode == sharedUtils.SELECT_IMG_REC){
                if(data!=null){
                    selectedImageURI = data.getData();
                    selectedImagePath = selectedImageURI.getPath();
                    imagePath.setText(selectedImagePath);
                }else{
                    try{
                        selectedImageURI = outputFileURI;
                        Log.w("Logging", "Output File URI :" + selectedImageURI);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
                selectedImage.setImageURI(selectedImageURI);
                sharedData.getInstance().setImageURI(selectedImageURI);

            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sharedData.getInstance().getImageURI()!=null){
            selectedImage.setImageURI(sharedData.getInstance().getImageURI());
        }
    }
}
