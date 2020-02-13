package com.example.wholerecords;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class SongVAdapter extends CursorAdapter {


    // Default constructor
    public SongVAdapter(Context context, Cursor cursor, int flags)
    {

        super(context, cursor, 0);

    }

    public void bindView(View view, Context context, Cursor cursor)
    {

        TextView b = view.findViewById(R.id.b);
        TextView c = view.findViewById(R.id.c);
        WebView a = view.findViewById(R.id.a);


        String title = cursor.getString(cursor.getColumnIndexOrThrow("Title"));
        String artist = cursor.getString(cursor.getColumnIndexOrThrow("Artist"));
        String url = cursor.getString(cursor.getColumnIndexOrThrow("Url"));

        b.setText(title);
        c.setText(artist);

        a.getSettings().setJavaScriptEnabled(true);
        a.setWebViewClient(new WebViewClient());
        a.loadUrl(url);



    }

    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.sdisplay, parent, false);
    }
}
