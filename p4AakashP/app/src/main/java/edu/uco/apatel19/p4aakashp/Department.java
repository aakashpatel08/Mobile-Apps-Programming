package edu.uco.apatel19.p4aakashp;

/**
 * Created by Aakash on 9/23/2017.
 */

public class Department {

    private String name, phoneNumber, website;

    public Department (String name, String phoneNumber, String website){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }
    public String getName(){
        return this.name;
    }
    public String getPhonenumber(){
        return  this.phoneNumber;
    }
    public String getWebsite(){
        return this.website;
    }
}
