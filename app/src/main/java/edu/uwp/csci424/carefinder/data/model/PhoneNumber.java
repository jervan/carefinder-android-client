package edu.uwp.csci424.carefinder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Java Object for storing phone number information contains factory getter, setter, equals, and to string methods
 *
 * Created by Jeremiah Van Offeren on 11/27/17.
 */
public class PhoneNumber {

    @SerializedName("phone_number")
    @Expose
    private Long phoneNumber;

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneNumber)) return false;

        PhoneNumber that = (PhoneNumber) o;

        return getPhoneNumber() != null ? getPhoneNumber().equals(that.getPhoneNumber()) : that.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        return getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumber=" + phoneNumber +
                '}';
    }
}