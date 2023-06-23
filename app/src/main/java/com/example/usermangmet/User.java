package com.example.usermangmet;

public class User {
   private String Fname;
   private String Lname;
   private String BirthDay;
   private String PhoneNum;
   private String Email;
   private String photo;
   private boolean isTeacher;

   public boolean isTeacher() {
      return isTeacher;
   }

   public void setTeacher(boolean teacher) {
      isTeacher = teacher;
   }



   public User() {
   }

   public User(String fname, String lname, String birthDay, String phoneNum, String email, String photo) {
      Fname = fname;
      Lname = lname;
      BirthDay = birthDay;
      PhoneNum = phoneNum;
      Email = email;
      this.photo = photo;
   }


   public User(String fname, String lname, String phoneNum1, String birthd, String email2) {
      Fname = fname;
      Lname = lname;
      BirthDay = birthd;
      PhoneNum = phoneNum1;
      Email = email2;
      this.photo = "";
      this.isTeacher=false;
   }

   public String getFname() {
      return Fname;
   }

   public void setFname(String fname) {
      Fname = fname;
   }

   public String getLname() {
      return Lname;
   }

   public void setLname(String lname) {
      Lname = lname;
   }

   public String getBirthDay() {
      return BirthDay;
   }

   public void setBirthDay(String birthDay) {
      BirthDay = birthDay;
   }

   public String getPhoneNum() {
      return PhoneNum;
   }

   public void setPhoneNum(String phoneNum) {
      PhoneNum = phoneNum;
   }

   public String getEmail() {
      return Email;
   }

   public void setEmail(String email) {
      Email = email;
   }

   public String getPhoto() {
      return photo;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }

   @Override
   public String toString() {
      return "User{" +
              "Fname='" + Fname + '\'' +
              ", Lname='" + Lname + '\'' +
              ", BirthDay='" + BirthDay + '\'' +
              ", PhoneNum='" + PhoneNum + '\'' +
              ", Email='" + Email + '\'' +
              ", photo='" + photo + '\'' +
              '}';
   }
}
