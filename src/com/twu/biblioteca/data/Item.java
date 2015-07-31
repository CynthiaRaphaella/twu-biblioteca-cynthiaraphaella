package com.twu.biblioteca.data;

import java.util.List;

public abstract class Item extends ItemList{

    protected int id;
    protected String name;
    protected String year;
    protected boolean isAvailable;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void returnItem(){
        this.isAvailable = true;
    }

    public void checkoutItem(){
        this.isAvailable = false;
    }

    public abstract String getReturnMessage();

    public abstract String getCheckoutMessage();

    public abstract Class getType();

}
