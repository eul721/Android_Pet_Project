package com.example.Hello_World;

import android.net.Uri;

/**
 * Created by Jacky on 7/13/14.
 */
public class sharedData {

    private static sharedData instance;

    private Uri selectedImageUri;

    public static sharedData getInstance(){
        if (instance == null){
            instance = new sharedData();
            instance.selectedImageUri = null;
        }
        return instance;
    }

    public Uri getImageURI(){
        return selectedImageUri;
    }
    public void setImageURI(Uri uri){
        selectedImageUri = uri;
    }



}
