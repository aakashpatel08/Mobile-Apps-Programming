package edu.uco.apatel19.p7aakashp;

import com.google.android.gms.maps.model.LatLng;

public class Department {
    private String name, website, phoneNumber;
    private double latitude, longitude;

    public Department (String name, String website, String phoneNumber, double latitude, double longitude) {
        this.name = name;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LatLng getCoordinates() {
        return new LatLng(latitude, longitude);
    }
}
