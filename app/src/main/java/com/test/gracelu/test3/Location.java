package com.test.gracelu.test3;

/**
 * Created by grace on 12/22/2016.
 */

public class Location {
    private int id;
    private int zipcode;
    private String name;
    private String street;
    private String city;

    public Location(){

    }
    public Location(int id, String name, String street, String city, int zipcode){
        this.id=id;
        this.name=name;
        this.street=street;
        this.city=city;
        this.zipcode=zipcode;
    }
    public Location(String name, String street, String city, int zipcode){
        //this.id=id;
        this.name=name;
        this.street=street;
        this.city=city;
        this.zipcode=zipcode;
    }
    public void setId(int newId){
        this.id=newId;
    }
    public void setZipcode(int newZip){
        this.zipcode=newZip;
    }
    public void setName(String newName){
        this.name=newName;
    }
    public void setStreet(String newStreet){
        this.street=newStreet;
    }
    public void setCity(String newCity){
        this.city=newCity;
    }
    public int getId(){
        return id;
    }
    public int getZipcode(){
        return zipcode;
    }
    public String getName(){
        return name;
    }
    public String getStreet(){
        return street;
    }
    public String getCity(){
        return city;
    }

}
