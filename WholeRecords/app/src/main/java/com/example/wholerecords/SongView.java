package com.example.wholerecords;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class SongView extends AppCompatActivity implements View.OnClickListener{

    private SongVAdapter adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int x = Song.getState();

        if(x == 0) {
            setContentView(R.layout.songv);
        }
        else
        {
            setContentView(R.layout.songvinp);
            Button button3 = findViewById(R.id.del);
            button3.setOnClickListener(this);
        }

        Button button1 = findViewById(R.id.Back);
        Button button2 = findViewById(R.id.add);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        DatabaseH myDB = new DatabaseH(this);

        int id = Song.getID();

        ListView listView = findViewById(R.id.sdisplay);
        Cursor cursor = myDB.getSongbID(id);

        adaptor = new SongVAdapter(getApplicationContext(), cursor, 0);
        listView.setAdapter(adaptor);

        myDB.close();
    }

    @Override
    public void onClick(View v) {

        DatabaseH myDB = new DatabaseH(this);
        int p = Playlist.getId();
        int s = Song.getID();

        int x = Song.getState();
        switch (v.getId()) {
            case R.id.Back:
                if(x == 0) {
                    Search();
                    break;
                }
                else
                {
                    Playl();
                    break;
                }

            case R.id.add:
                addSong();
                break;

            case R.id.del:

                myDB.deleteSP(s, p);
                Playl();
                break;
        }
    }

    public void Playl()
    {
        Intent intent = new Intent(this,PlaylistView.class);
        startActivity(intent);
    }

    public void Search()
    {
        Intent intent = new Intent(this,Search.class);
        startActivity(intent);
    }

    public void addSong()
    {
        Intent intent = new Intent(this,AddPlay.class);
        startActivity(intent);
    }
}
