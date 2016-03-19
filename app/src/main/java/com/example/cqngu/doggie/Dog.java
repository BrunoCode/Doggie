package com.example.cqngu.doggie;

/**
 * Created by cqngu on 3/18/2016.
 */
public class Dog {
    String name;
    String type;
    String note;
    int id;

    public Dog(final String theName, final String theType, final int theId) {
        name = theName;
        type = theType;
        note = "";
        id = theId;
    }

    public void addNote(final String newNote) {
        note = newNote;
    }

    public void update(final String theName, final String theType) {
        name = theName;
        type = theType;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder bd = new StringBuilder();

        bd.append(name);
        bd.append("\n");
        bd.append(type);

        return bd.toString();
    }
}
