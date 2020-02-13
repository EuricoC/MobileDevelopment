package com.example.wholerecords;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

public class PlaylistCoverAdapter extends CursorAdapter
{

    // Default constructor
    public PlaylistCoverAdapter(Context context, Cursor cursor, int flags)
    {

        super(context, cursor, 0);

    }

    public void bindView(View view, Context context, Cursor cursor)
    {

        ImageView a = view.findViewById(R.id.a);

        String image = cursor.getString(cursor.getColumnIndexOrThrow("Image"));

        a.setImageBitmap(ImageUtil.convert(image));


    }

    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.coverp, parent, false);
    }
}
