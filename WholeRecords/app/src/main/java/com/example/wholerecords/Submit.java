package com.example.wholerecords;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Submit  extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit);

        Button button1 = findViewById(R.id.sub);
        Button button2 = findViewById(R.id.Back);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        DatabaseH myDB = new DatabaseH(this);

        EditText text1 = findViewById(R.id.title);
        EditText text2 = findViewById(R.id.artist);
        EditText text3 = findViewById(R.id.url);

        String name = text1.getText().toString();
        String artist = text2.getText().toString();
        String url = text3.getText().toString();

        switch (v.getId())
        {
            case R.id.sub:
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(artist) || TextUtils.isEmpty(url))
                {

                    Toast toast = Toast.makeText(this,
                            "One or more fields were left empty",
                            Toast.LENGTH_SHORT);

                    toast.show();
                }
                else
                {
                    myDB.addSong(name, artist, url);
                    myDB.close();
                    Back();
                }
                break;

            case R.id.Back:
                Back();
                break;
        }
    }

    public void Back()
    {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }
}
