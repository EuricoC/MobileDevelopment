package com.example.wholerecords;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class MakePlaylist extends AppCompatActivity implements View.OnClickListener{

    private static final int loadedimage = 1;
    ImageView imageupload;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makeplaylist);

        imageupload = findViewById(R.id.image);
        Button button1 = findViewById(R.id.create);
        Button button2 = findViewById(R.id.Back);

        imageupload.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        DatabaseH myDB = new DatabaseH(this);

        EditText text1 = findViewById(R.id.name);
        String name = text1.getText().toString();
        String uname = User.getName();
        String img = ImageUtil.getEncode();

        switch (v.getId())
        {
            case R.id.image:
                Intent gallleryintent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallleryintent, loadedimage);
                break;
            case R.id.create:
                if(TextUtils.isEmpty(name))
                {

                    Toast toast = Toast.makeText(this,
                            "Please select a playlist name",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else
                {
                    if(TextUtils.isEmpty(img))
                    {
                        Toast toast = Toast.makeText(this,
                                "Please select a playlist image",
                                Toast.LENGTH_SHORT);

                        toast.show();
                    }
                    else
                    {
                        //adding a new Playlist to the database
                        myDB.addPlaylist(name, uname, img, 0);
                        myDB.close();
                        ImageUtil.setEncode(null);
                        Back();
                    }
                }
                break;

            case R.id.Back:
                Back();
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == loadedimage && resultCode == RESULT_OK && data != null) {
            Uri selectImage = data.getData();
            imageupload.setImageURI(selectImage);

            InputStream imageStream = null;
            try {
                imageStream = getContentResolver().openInputStream(selectImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
            String encodedImage = ImageUtil.convertB(selectedImage);

            ImageUtil.setEncode(encodedImage);
        }
    }

    public void Back()
    {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

}
