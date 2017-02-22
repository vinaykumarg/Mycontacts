package com.example.vinayg.mycontacts.helper;

/**
 * Created by vinay.g.
 */

public class Contact {
    private long id;
    private String name;
    private String phnumber;
    public String getPhnumber() {
        return phnumber;
    }

    public void setPhnumber(String phnumber) {
        this.phnumber = phnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phnumber='" + phnumber + '\'' +
                '}';
    }
}
