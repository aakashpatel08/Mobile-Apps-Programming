package edu.uco.apatel19.p5aakashp;

public class Department{
    private String departmentName;
    private String departmentWebsite;

    public Department(String department, String website) {
        this.departmentName = department;
        this.departmentWebsite = website;
    }

    @Override
    public String toString() {
        return departmentName;
    }

    public String getDepartment() {
        return departmentName;
    }

    public String getWebsite() {
        return departmentWebsite;
    }

}
