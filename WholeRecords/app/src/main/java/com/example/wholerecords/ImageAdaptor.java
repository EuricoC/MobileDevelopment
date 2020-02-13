package com.example.wholerecords;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdaptor extends CursorAdapter {


    // Default constructor
    public ImageAdaptor(Context context, Cursor cursor, int flags)
    {

        super(context, cursor, 0);

    }

    public void bindView(View view, Context context, Cursor cursor)
    {

        TextView b = view.findViewById(R.id.b);
        TextView c = view.findViewById(R.id.c);
        ImageView a = view.findViewById(R.id.a);


        String name = cursor.getString(cursor.getColumnIndexOrThrow("Playlist"));
        int amount = cursor.getInt(cursor.getColumnIndexOrThrow("Numbersongs"));
        String image = cursor.getString(cursor.getColumnIndexOrThrow("Image"));

        String Samount = Integer.toString(amount);

        b.setText("  " +name);
        c.setText(Samount);
        a.setImageBitmap(ImageUtil.convert(image));


    }

    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.row, parent, false);
    }
}
