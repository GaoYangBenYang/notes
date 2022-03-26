package com.gy.bean;

public class Pet {
    private String pname;

    public Pet() {
    }

    public Pet(String pname) {
        this.pname = pname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "pname='" + pname + '\'' +
                '}';
    }
}
