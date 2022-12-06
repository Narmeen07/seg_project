package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;
import java.util.Date;

public class ChequeRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheque_registration);
        File file = new File(Environment.getExternalStorageDirectory()+"/DCIM/", "image"
                + new Date().getTime()+".pnj");
        Uri imgUri = Uri.fromFile(file);
        String imgPath= file.getAbsolutePath();
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /*
        intent.putExtra(MediaStore.EXTRA_OUTPUT.setImageUri());
        startActityForResult(intent, CAPTURE_IMAGE);

        protected void onActivityResult(int requestCode, int resultCode, Intent data){
            if(resultCode != Activity.RESULT_CANCELED){
                if(requestCode != CAPTURE_IMAGE){
                    ImageView imageView = (ImageView) findViewById(R.id.imgView);
                    image.View.setImageBitmap(BitmapFactory.decodeFile(imgPath));
                }
            }
        }

         */
    }
}