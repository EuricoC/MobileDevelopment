package com.example.wholerecords;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddPlay extends AppCompatActivity implements View.OnClickListener {

    private ImageAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplay);

        Button button1 = findViewById(R.id.back);
        Button button2 = findViewById(R.id.newp);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        final DatabaseH myDB = new DatabaseH(this);

        String name = User.getName();

        ListView listView = findViewById(R.id.list);
        Cursor cursor = myDB.getListPlaylist(name);

        adaptor = new ImageAdaptor(getApplicationContext(), cursor, 0);
        listView.setAdapter(adaptor);


        //on item click for list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int x = (int) l;

                Playlist.setId(x);

                int p = Playlist.getId();
                int s = Song.getID();

                boolean exists;
                exists = myDB.Songmatch(s, p);

                if(exists == true)
                {
                    Toast toast = Toast.makeText(AddPlay.this,
                            "Song already in playlist",
                            Toast.LENGTH_SHORT);

                    toast.show();

                }
                else
                {
                    myDB.addtoplay(p, s);
                    myDB.close();

                    Toast toast = Toast.makeText(AddPlay.this,
                            "Song added successfully",
                            Toast.LENGTH_SHORT);

                    toast.show();

                    Back();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back:
                Back();
                break;

            case R.id.newp:
                Newp();
                break;
        }

    }

    public void Back()
    {
        Intent intent = new Intent(this,SongView.class);
        startActivity(intent);
    }

    public void Newp()
    {
        Intent intent = new Intent(this,MakePlaylist.class);
        startActivity(intent);
    }
}
