package com.example.reworksample.model.model;

import com.example.reworksample.model.model.District;
import com.example.reworksample.model.model.Province;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpaceMeta {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("province")
    @Expose
    private Province province;
    @SerializedName("district")
    @Expose
    private District district;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("shorten_address")
    @Expose
    private String shortenAddress;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("website")
    @Expose
    private Object website;
    @SerializedName("is_show_policy")
    @Expose
    private Boolean isShowPolicy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShortenAddress() {
        return shortenAddress;
    }

    public void setShortenAddress(String shortenAddress) {
        this.shortenAddress = shortenAddress;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public Boolean getIsShowPolicy() {
        return isShowPolicy;
    }

    public void setIsShowPolicy(Boolean isShowPolicy) {
        this.isShowPolicy = isShowPolicy;
    }

}
