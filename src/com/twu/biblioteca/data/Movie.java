package com.twu.biblioteca.data;

import com.twu.biblioteca.util.MessagesUtil;

import java.util.ArrayList;
import java.util.List;

public class Movie extends Item{

    private String director;
    private String rate;

    public Movie(int id, String name, String director, String year, String rate){
        this.id = id;
        this.name = name;
        this.director = director;
        this.year = year;
        this.rate = rate;
        this.isAvailable = true;
    }

    public String getDirector() {
        return director;
    }

    public String getRate() {
        if(rate == null)
            return "unrated";
        else
            return rate;
    }

    public String getReturnMessage(){
        return MessagesUtil.RETURN_MOVIE_MESSAGE;
    }

    public String getCheckoutMessage(){
        return MessagesUtil.CHECKOUT_MOVIE_MESSAGE;
    }

    public Class getType(){
        return this.getClass();
    }

    public String getPropertiesNames(){
        return SEPARATOR_ITEM + "Code" + SEPARATOR_ITEM + "Name" + SEPARATOR_ITEM + "Director" + SEPARATOR_ITEM + "Year" + SEPARATOR_ITEM + "Rate" + SEPARATOR_ITEM;
    }

    public String getPropertiesValues(){
        return SEPARATOR_ITEM + getId() + SEPARATOR_ITEM + getName() + SEPARATOR_ITEM + getDirector() + SEPARATOR_ITEM + getYear() + SEPARATOR_ITEM + getRate() + SEPARATOR_ITEM;
    }
}
