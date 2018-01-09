package edu.uwp.csci424.carefinder.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Java Object for storing hospital information contains factory getter, setter, equals, and to string methods
 *
 * Created by Jeremiah Van Offeren on 11/27/17.
 */
public class Hospital {

    @SerializedName("_id")
    @Expose
    private String mongoId;
    @SerializedName("provider_id")
    @Expose
    private String providerId;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zip_code")
    @Expose
    private Integer zipCode;
    @SerializedName("county_name")
    @Expose
    private String countyName;
    @SerializedName("hospital_type")
    @Expose
    private String hospitalType;
    @SerializedName("hospital_ownership")
    @Expose
    private String hospitalOwnership;
    @SerializedName("emergency_services")
    @Expose
    private Boolean emergencyServices;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("position")
    @Expose
    private Integer position;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("dateCreated")
    @Expose
    private String dateCreated;
    @SerializedName("dateModified")
    @Expose
    private String dateModified;
    @SerializedName("phone_number")
    @Expose
    private PhoneNumber phoneNumber;
    @SerializedName("location")
    @Expose
    private Location location;

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getProviderId() {
        return providerId == null ? "" : providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getHospitalType() {
        return hospitalType == null ? "" : hospitalType;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
    }

    public String getHospitalOwnership() {
        return hospitalOwnership;
    }

    public void setHospitalOwnership(String hospitalOwnership) {
        this.hospitalOwnership = hospitalOwnership;
    }

    public Boolean getEmergencyServices() {
        return emergencyServices;
    }

    public void setEmergencyServices(Boolean emergencyServices) {
        this.emergencyServices = emergencyServices;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;

        Hospital hospital = (Hospital) o;

        if (getMongoId() != null ? !getMongoId().equals(hospital.getMongoId()) : hospital.getMongoId() != null)
            return false;
        if (getProviderId() != null ? !getProviderId().equals(hospital.getProviderId()) : hospital.getProviderId() != null)
            return false;
        if (getHospitalName() != null ? !getHospitalName().equals(hospital.getHospitalName()) : hospital.getHospitalName() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(hospital.getAddress()) : hospital.getAddress() != null)
            return false;
        if (getCity() != null ? !getCity().equals(hospital.getCity()) : hospital.getCity() != null)
            return false;
        if (getState() != null ? !getState().equals(hospital.getState()) : hospital.getState() != null)
            return false;
        if (getZipCode() != null ? !getZipCode().equals(hospital.getZipCode()) : hospital.getZipCode() != null)
            return false;
        if (getCountyName() != null ? !getCountyName().equals(hospital.getCountyName()) : hospital.getCountyName() != null)
            return false;
        if (getHospitalType() != null ? !getHospitalType().equals(hospital.getHospitalType()) : hospital.getHospitalType() != null)
            return false;
        if (getHospitalOwnership() != null ? !getHospitalOwnership().equals(hospital.getHospitalOwnership()) : hospital.getHospitalOwnership() != null)
            return false;
        if (getEmergencyServices() != null ? !getEmergencyServices().equals(hospital.getEmergencyServices()) : hospital.getEmergencyServices() != null)
            return false;
        if (getId() != null ? !getId().equals(hospital.getId()) : hospital.getId() != null)
            return false;
        if (getUuid() != null ? !getUuid().equals(hospital.getUuid()) : hospital.getUuid() != null)
            return false;
        if (getPosition() != null ? !getPosition().equals(hospital.getPosition()) : hospital.getPosition() != null)
            return false;
        if (getUrl() != null ? !getUrl().equals(hospital.getUrl()) : hospital.getUrl() != null)
            return false;
        if (getDateCreated() != null ? !getDateCreated().equals(hospital.getDateCreated()) : hospital.getDateCreated() != null)
            return false;
        if (getDateModified() != null ? !getDateModified().equals(hospital.getDateModified()) : hospital.getDateModified() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(hospital.getPhoneNumber()) : hospital.getPhoneNumber() != null)
            return false;
        return getLocation() != null ? getLocation().equals(hospital.getLocation()) : hospital.getLocation() == null;
    }

    @Override
    public int hashCode() {
        int result = getMongoId() != null ? getMongoId().hashCode() : 0;
        result = 31 * result + (getProviderId() != null ? getProviderId().hashCode() : 0);
        result = 31 * result + (getHospitalName() != null ? getHospitalName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getZipCode() != null ? getZipCode().hashCode() : 0);
        result = 31 * result + (getCountyName() != null ? getCountyName().hashCode() : 0);
        result = 31 * result + (getHospitalType() != null ? getHospitalType().hashCode() : 0);
        result = 31 * result + (getHospitalOwnership() != null ? getHospitalOwnership().hashCode() : 0);
        result = 31 * result + (getEmergencyServices() != null ? getEmergencyServices().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getUuid() != null ? getUuid().hashCode() : 0);
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        result = 31 * result + (getDateCreated() != null ? getDateCreated().hashCode() : 0);
        result = 31 * result + (getDateModified() != null ? getDateModified().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getLocation() != null ? getLocation().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "mongoId='" + mongoId + '\'' +
                ", providerId='" + providerId + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode=" + zipCode +
                ", countyName='" + countyName + '\'' +
                ", hospitalType='" + hospitalType + '\'' +
                ", hospitalOwnership='" + hospitalOwnership + '\'' +
                ", emergencyServices=" + emergencyServices +
                ", id=" + id +
                ", uuid='" + uuid + '\'' +
                ", position=" + position +
                ", url='" + url + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", location=" + location +
                '}';
    }
}
