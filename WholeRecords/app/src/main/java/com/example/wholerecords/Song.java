package com.example.wholerecords;

public class Song {

    static int id;

    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        Song.state = state;
    }

    static int state;

    public Song(){}

    public Song(int ID)
    {
        this.id = ID;
    }

    public static int getID() {
        return id;
    }

    public static void setID(int ID) {
        id = ID;
    }

}