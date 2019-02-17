package com.leroy.smsf.model;

public class Member {
    private String name;
    private double proportion;
    boolean isPension;

    public Member(String name, double proportion, boolean isPension) {
        this.name = name;
        this.proportion = proportion;
        this.isPension = isPension;
    }

    public String getName() {
        return name;
    }

    public double getProportion() {
        return proportion;
    }

    public boolean isPension() {
        return isPension;
    }
}
