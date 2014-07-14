package com.example.Hello_World;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacky on 7/13/14.
 */
public class sharedUtils {

    public static final int SELECT_IMG_REC = 1;

    public static Intent setupImageIntents(PackageManager pkgmng){
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "helloDir" + File.separator);
        root.mkdirs();
        final String fname = "img_" + System.currentTimeMillis() + ".jpg";
        final File sdImageMainDictionary = new File(root,fname);
        final Uri outputFileURI = Uri.fromFile(sdImageMainDictionary);

        //Camera Intent
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = pkgmng;
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent,0);
        for (ResolveInfo res : listCam){
            final String pkgname = res.activityInfo.packageName;
            final String name = res.activityInfo.name;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(
                    new ComponentName(pkgname,name)
            );
            intent.setPackage(pkgname);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,outputFileURI);
            cameraIntents.add(intent);

        }

        //fs
        final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");


        //choosing fs
        final Intent chooserIntent = Intent.createChooser(galleryIntent,"Select Source");

        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));
        //startActivityForResult(chooserIntent,SELECT_IMG_REC);
        return chooserIntent;

    }

}
