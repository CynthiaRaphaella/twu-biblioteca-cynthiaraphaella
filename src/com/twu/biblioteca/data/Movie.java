package com.twu.biblioteca.data;

import com.twu.biblioteca.util.MessagesUtil;

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
}
