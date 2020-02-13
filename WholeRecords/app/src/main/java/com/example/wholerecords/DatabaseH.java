package com.example.wholerecords;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import java.util.jar.Attributes;

public class DatabaseH extends SQLiteOpenHelper {

    //Database
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "WholeR";

    //User Table
    private static final String TABLE_NAME1 = "User";
    private static final String TABLE_ID1 = "_id";
    private static final String col1 = "Name";
    private static final String col2 = "Password";
    private static final String col3 = "Email";
    private static final String col4 = "Phone";
    private static final String col5 = "Address";

    //Playlist table
    private static final String TABLE_NAME2 = "Playlist";
    private static final String TABLE_ID2 = "_id";
    private static final String PN = "Playlist";
    private static final String UN = "UserName";
    private static final String Image = "Image";
    private static final String Nsong = "Numbersongs";

    //Songs table
    private static final String TABLE_NAME3 = "Songs";
    private static final String TABLE_ID3 = "_id";
    private static final String T = "Title";
    private static final String A = "Artist";
    private static final String U = "Url";

    //Songs in Playlist table
    private static final String TABLE_NAME4 = "SongsinPlaylist";
    private static final String TABLE_ID4 = "_id";
    private static final String PlaylistID = "PID";
    private static final String SongID = "SID";


    public DatabaseH(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    // Creating Tables
    public void onCreate(SQLiteDatabase db)
    {
        //Create User Table
        String CreateTable1 = "CREATE TABLE " + TABLE_NAME1 + " (" + TABLE_ID1+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col1+ " TEXT," +col2 + " TEXT," +col3 + " TEXT," +col4 + " TEXT," +col5 +" TEXT )";

        String CreateTable2 = "CREATE TABLE " + TABLE_NAME2 + " (" + TABLE_ID2+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PN+ " TEXT," +UN + " TEXT," +Image + " TEXT," +Nsong + " INTEGER )";

        String CreateTable3 = "CREATE TABLE " + TABLE_NAME3 + " (" + TABLE_ID3+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                T+ " TEXT," +A + " TEXT," +U +" TEXT )";

        String CreateTable4 = "CREATE TABLE " + TABLE_NAME4 + " (" + TABLE_ID4+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PlaylistID+ " INTEGER," +SongID + " INTEGER )";

        db.execSQL(CreateTable1);
        db.execSQL(CreateTable2);
        db.execSQL(CreateTable3);
        db.execSQL(CreateTable4);
    }

    // update database structure
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop User Table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);


        // Create tables again
        onCreate(db);
    }

    // insert a new User
    void adduser(String Name,String Password,String Email,String Phone,String Address )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col1, Name); // User name
        values.put(col2, Password); // User Password
        values.put(col3, Email); // User email
        values.put(col4, Phone); // User phone
        values.put(col5, Address); // User address

        // Inserting Row
        db.insert(TABLE_NAME1, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public Cursor getListUser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from "+TABLE_NAME1;
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public boolean finduser(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from "+TABLE_NAME1+" where Name ='"+name+"' ";

        Cursor cursor = db.rawQuery(query,null);
        boolean exist = (cursor.getCount() > 0);

        return exist;
    }

    public boolean UserMatch(String name, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from "+TABLE_NAME1+" where Name ='"+name+"' and Password ='"+pass+"'";

        Cursor cursor = db.rawQuery(query,null);
        boolean exist = (cursor.getCount() > 0);

        return exist;
    }

    void addPlaylist(String PName,String UName,String Img, int Nsongs)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PN, PName); // playlist name
        values.put(UN, UName); // User name
        values.put(Image, Img); // Image
        values.put(Nsong, Nsongs); // Number of songs


        // Inserting Row
        db.insert(TABLE_NAME2, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public Cursor getListPlaylist(String name)
    {
        String username = name;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from '"+TABLE_NAME2+"' where UserName = '"+username+"' ";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public void deletePlaylist(int x)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, TABLE_ID2 + "=" + x, null);
        db.delete(TABLE_NAME4,  PlaylistID + "=" + x, null);
        db.close();
    }

    public Cursor getimageP(int x)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from '"+TABLE_NAME2+"' where _id = '"+x+"' ";
        Cursor data = db.rawQuery(query,null);

        return data;
    }

    void addSong(String name,String artist,String url)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T, name); // title
        values.put(A, artist); // artist
        values.put(U, url); // video url



        // Inserting Row
        db.insert(TABLE_NAME3, null, values);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public Cursor getSong(String title)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from '"+TABLE_NAME3+"' where Title Like '"+title+"%' ";
        Cursor data = db.rawQuery(query,null);

        return data;
    }

    public Cursor getSongbID(int x)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from '"+TABLE_NAME3+"' where _id = '"+x+"' ";
        Cursor data = db.rawQuery(query,null);

        return data;
    }

    void addtoplay(int idp,int ids)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlaylistID, idp); // title
        values.put(SongID, ids); // artist

        // Inserting Row
        db.insert(TABLE_NAME4, null, values);

        values.put(Nsong, idp); // title

        //String query = "Update '"+TABLE_NAME2+"' Set Numbersongs = Numbersongs+1 where _id = '"+idp+"' ";

        db.execSQL("UPDATE " + TABLE_NAME2 +
                        " SET " + Nsong + " = " + Nsong + " + 1" +
                        " WHERE " + TABLE_ID2 + " = " + idp);

        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public boolean Songmatch(int x, int y)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from "+TABLE_NAME4+" where SID ='"+x+"' and PID ='"+y+"' ";

        Cursor cursor = db.rawQuery(query,null);
        boolean exist = (cursor.getCount() > 0);

        return exist;
    }

    public void deleteSP(int x, int y)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME4,  PlaylistID + " = " + y + " and " + SongID + " = " + x  , null);
        db.close();
    }



    public Cursor getPsong(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select Songs._id,Songs.Title,Songs.Artist from SongsinPlaylist join Songs on SongsinPlaylist.SID = Songs._id join Playlist on SongsinPlaylist.PID = Playlist._id  where Playlist._id = '"+id+"' ";
        Cursor data = db.rawQuery(query,null);

        return data;
    }

}