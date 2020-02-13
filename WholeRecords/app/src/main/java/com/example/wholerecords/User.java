package com.example.wholerecords;

public class User {

    static String Name;

    public User(){}

    public User(String name)
    {
        this.Name = name;
    }

    public static String getName() {
        return Name;
    }

    public static void setName(String name) {
        Name = name;
    }

}
