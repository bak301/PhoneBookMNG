package model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;

/**
 * Created by bak on 31/08/2015.
 */
public class Client implements Serializable{
    private static final int maxNum = 3;
    private int ID;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private long cmnd;
    private String address;
    private String gender;
    private LinkedHashSet<PhoneNumber> numList;

    public Client(String firstName, String lastName, Date birthDay, String gender, long cmnd, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.cmnd = cmnd;
        this.address = address;
        this.gender = gender;
        this.numList = new LinkedHashSet<PhoneNumber>(maxNum);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public long getCmnd() {
        return cmnd;
    }

    public void setCmnd(long cmnd) {
        this.cmnd = cmnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LinkedHashSet<PhoneNumber> getNumList() {
        return numList;
    }

    public void setNumList(LinkedHashSet<PhoneNumber> numList) {
        this.numList = numList;
    }

    public boolean addNumber(PhoneNumber p){
        if (numList.size() == maxNum){
            return false;
        } else {
            numList.add(p);
            return true;
        }
    }
}
