package com.example.gml;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.UriCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    UriViewModel model;

    private Button uploadImage, uploadVideo;
    private static final Integer PICK_IMAGE_REQ = 1311;
    private static final Integer PICK_VID_REQ = 2383;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadImage = findViewById(R.id.upload_image);
        uploadVideo = findViewById(R.id.upload_video);

        model = new ViewModelProvider(this).get(UriViewModel.class);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(i, "Select Picture"), PICK_IMAGE_REQ);
            }
        });

        uploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("video/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Video"), PICK_VID_REQ);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQ && resultCode == RESULT_OK && data != null) {
            model.imageUri = data.getData();
            Toast.makeText(this, "Image Selected Successfully", Toast.LENGTH_SHORT).show();
        }

        if (requestCode == PICK_VID_REQ && resultCode == RESULT_OK && data != null) {
            model.videoUri = data.getData();
            Toast.makeText(this, "Video Selected Successfully", Toast.LENGTH_SHORT).show();
        }
    }
}