package com.theironyard.clt;

import java.util.ArrayList;

/**
 * Created by mac on 4/14/16.
 */
public class User {

    public String name;

    public ArrayList<String> messages;


    public User(String name) {
        messages = new ArrayList<>();
        this.name = name;

    }

}

