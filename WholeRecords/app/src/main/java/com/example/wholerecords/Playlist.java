package com.example.wholerecords;

public class Playlist {
    static int id;

    public Playlist(){}

    public Playlist(int id)
    {
        this.id = id;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Playlist.id = id;
    }
}
