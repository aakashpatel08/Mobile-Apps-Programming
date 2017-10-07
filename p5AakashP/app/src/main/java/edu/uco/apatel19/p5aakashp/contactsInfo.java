package edu.uco.apatel19.p5aakashp;

class contactsInfo implements Comparable<contactsInfo>{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public contactsInfo(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEmail(){
        return email;
    }

    @Override
    public int compareTo(contactsInfo o) {
        return o.getLastName().compareTo(getLastName());
    }
}
