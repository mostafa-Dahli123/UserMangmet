package com.example.usermangmet;

public class StudentUser {

    String firstName , lastName ,Email,Birthday,PhoneNum;


public StudentUser(){

}

    public StudentUser(String firsName, String lastName, String email, String birthday, String phoneNum) {
        this.firstName = firsName;
        this.lastName = lastName;
        Email = email;
        Birthday = birthday;
        PhoneNum = phoneNum;
    }

    public String getFirsName() {
        return firstName;
    }

    public void setFirsName(String firsName) {
        this.firstName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }
}
