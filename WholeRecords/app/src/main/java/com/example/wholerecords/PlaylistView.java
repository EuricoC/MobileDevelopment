package com.example.wholerecords;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlaylistView extends AppCompatActivity implements View.OnClickListener{


    private PlaylistCoverAdapter adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        int x = Playlist.getId();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlistv);

        Button button1 = findViewById(R.id.Back);
        Button button2 = findViewById(R.id.Delete);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        DatabaseH myDB = new DatabaseH(this);

        ListView listView = findViewById(R.id.cover);

        Cursor cursor = myDB.getimageP(x);
        adaptor = new PlaylistCoverAdapter(getApplicationContext(), cursor, 0);
        listView.setAdapter(adaptor);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.songrow,

                myDB.getPsong(x),
                new String[] { "Title", "Artist" },
                new int[] { R.id.b , R.id.c },
                0);

        ListView listViewS;
        listViewS = findViewById(R.id.srows);
        listViewS.setAdapter(adapter);

        listViewS.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int x = (int) l;

                Song.setID(x);
                Song.setState(1);

                Song();
            }
        });


    }

    @Override
    public void onClick(View v)
    {
        DatabaseH myDB = new DatabaseH(this);

        switch (v.getId())
        {
            case R.id.Back:


                Back();
                break;

            case R.id.Delete:
                int x = Playlist.getId();

                myDB.deletePlaylist(x);
                Back();

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
