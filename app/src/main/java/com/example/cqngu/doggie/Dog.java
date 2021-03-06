package com.example.cqngu.doggie;

/**
 * Created by cqngu on 3/18/2016.
 */
public class Dog {
    String name;
    String type;
    String note;
    String id;

    public Dog(final String theName, final String theType, final String theId) {
        name = theName;
        type = theType;
        note = "";
        id = theId;
    }

    public Dog(final String theName, final String theType) {
        name = theName;
        type = theType;
        note = "";
        id = null;
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

    public String getID() { return id; }

    @Override
    public String toString() {
        StringBuilder bd = new StringBuilder();

        bd.append(name);
        bd.append("\n");
        bd.append(type);

        return bd.toString();
    }
}
