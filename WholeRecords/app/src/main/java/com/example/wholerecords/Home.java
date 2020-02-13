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

public class Home extends AppCompatActivity implements View.OnClickListener{

    private ImageAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Button button1 = findViewById(R.id.Home);
        Button button2 = findViewById(R.id.Search);
        Button button3 = findViewById(R.id.Submit);
        Button button4 = findViewById(R.id.makeP);
        Button button5 = findViewById(R.id.logout);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);

        //using cursor adapter to set the playlist on the listView
        DatabaseH myDB = new DatabaseH(this);

        String name = User.getName();

        ListView listView = findViewById(R.id.list);
        Cursor cursor = myDB.getListPlaylist(name);

        adaptor = new ImageAdaptor(getApplicationContext(), cursor, 0);
        listView.setAdapter(adaptor);
        myDB.close();


        //on item click for list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int x = (int) l;

                Playlist.setId(x);

                OPlay();
            }
        });


    }


    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.Home:
                Home();
                break;

            case R.id.makeP:
                makeplayL();
                break;

            case R.id.logout:
                logout();
                break;

            case R.id.Submit:
                sub();
                break;

            case R.id.Search:
                search();
                break;
        }

    }

    public void OPlay()
    {
        Intent intent = new Intent(Home.this,PlaylistView.class);
        startActivity(intent);
    }

    public void Home()
    {
        Intent intent = new Intent(Home.this,Home.class);
        startActivity(intent);
    }

    public void makeplayL()
    {
        Intent intent = new Intent(Home.this,MakePlaylist.class);
        startActivity(intent);
    }

    public void logout()
    {
        Intent intent = new Intent(Home.this,MainActivity.class);
        startActivity(intent);
    }

    public void sub()
    {
        Intent intent = new Intent(Home.this,Submit.class);
        startActivity(intent);
    }

    public void search()
    {
        Intent intent = new Intent(Home.this,Search.class);
        startActivity(intent);
    }
}