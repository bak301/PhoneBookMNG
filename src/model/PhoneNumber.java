package model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bak on 31/08/2015.
 */
public class PhoneNumber implements Serializable{
    private long number;
    private Date registrationDay;
    private boolean isPrePaid;
    private String PSP;

    public PhoneNumber() {
    }

    public PhoneNumber(long number, Date registrationDay, boolean isPrePaid, String PSP) {
        this.number = number;
        this.registrationDay = registrationDay;
        this.isPrePaid = isPrePaid;
        this.PSP = PSP;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Date getRegistrationDay() {
        return registrationDay;
    }

    public void setRegistrationDay(Date registrationDay) {
        this.registrationDay = registrationDay;
    }

    public boolean isPrePaid() {
        return isPrePaid;
    }

    public void setIsPrePaid(boolean isPrePaid) {
        this.isPrePaid = isPrePaid;
    }

    public String getPSP() {
        return PSP;
    }

    public void setPSP(String PSP) {
        this.PSP = PSP;
    }
}
