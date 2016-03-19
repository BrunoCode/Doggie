package com.example.cqngu.doggie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cqngu on 3/19/2016.
 */
public class UserProfile {
    String id;
    ArrayList<Dog> myDogList;
    Set<Integer> keys;

    public UserProfile(String theId) {
        id = theId;
        myDogList = new ArrayList<Dog>();
        keys  = new HashSet<Integer>();
    }

    public String getId() {
        return id;
    }

    public void add(Dog dog) {
        myDogList.add(dog);
    }

    public void addKey(Integer key) {
        keys.add(key);
    }
}
