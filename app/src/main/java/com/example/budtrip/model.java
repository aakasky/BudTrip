package com.example.budtrip;

public class model
{
    String name,place,state,purl,about;

    model()
    {

    }

    public model(String name, String place, String state, String purl, String about) {
        this.name = name;
        this.place = place;
        this.state = state;
        this.purl = purl;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}