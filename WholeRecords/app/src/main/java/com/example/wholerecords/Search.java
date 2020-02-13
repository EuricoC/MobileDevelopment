package com.example.wholerecords;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        Button button1 = findViewById(R.id.back);
        Button button2 = findViewById(R.id.go);


        button1.setOnClickListener(this);
        button2.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        DatabaseH myDB = new DatabaseH(this);

        EditText text1 = findViewById(R.id.search);
        String search = text1.getText().toString();

        switch (v.getId()) {
            case R.id.back:
                Back();
                break;

            case R.id.go:

                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                        R.layout.songrow,

                        myDB.getSong(search),
                        new String[] { "Title", "Artist" },
                        new int[] { R.id.b , R.id.c },
                        0);

                ListView listView;
                listView = findViewById(R.id.list);
                listView.setAdapter(adapter);

                myDB.close();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        int x = (int) l;

                        Song.setID(x);
                        Song.setState(0);

                        Song();
                    }
                });

                break;
        }

    }

    public void Back()
    {
        Intent intent = new Intent(this,Home.class);
        startActivity(intent);
    }

    public void Song()
    {
        Intent intent = new Intent(this,SongView.class);
        startActivity(intent);
    }
}
