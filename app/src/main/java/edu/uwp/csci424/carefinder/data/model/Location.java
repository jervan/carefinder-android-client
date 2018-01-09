package edu.uwp.csci424.carefinder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Java Object for storing location information contains factory getter, setter, equals, and to string methods
 *
 * Created by Jeremiah Van Offeren on 11/27/17.
 */
public class Location {

    @SerializedName("human_address")
    @Expose
    private String humanAddress;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("needs_recoding")
    @Expose
    private Boolean needsRecoding;

    public String getHumanAddress() {
        return humanAddress;
    }

    public void setHumanAddress(String humanAddress) {
        this.humanAddress = humanAddress;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getNeedsRecoding() {
        return needsRecoding;
    }

    public void setNeedsRecoding(Boolean needsRecoding) {
        this.needsRecoding = needsRecoding;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (getHumanAddress() != null ? !getHumanAddress().equals(location.getHumanAddress()) : location.getHumanAddress() != null)
            return false;
        if (getLatitude() != null ? !getLatitude().equals(location.getLatitude()) : location.getLatitude() != null)
            return false;
        if (getLongitude() != null ? !getLongitude().equals(location.getLongitude()) : location.getLongitude() != null)
            return false;
        return getNeedsRecoding() != null ? getNeedsRecoding().equals(location.getNeedsRecoding()) : location.getNeedsRecoding() == null;
    }

    @Override
    public int hashCode() {
        int result = getHumanAddress() != null ? getHumanAddress().hashCode() : 0;
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getNeedsRecoding() != null ? getNeedsRecoding().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "humanAddress='" + humanAddress + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", needsRecoding=" + needsRecoding +
                '}';
    }
}
