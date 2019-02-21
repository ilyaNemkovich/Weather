
package com.vironit.android.weather.dto.belrosstrah;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("worktime")
    @Expose
    private String worktime;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("ismain")
    @Expose
    private Integer ismain;
    @SerializedName("claimoffice")
    @Expose
    private Integer claimoffice;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("is_sell_office")
    @Expose
    private Integer isSellOffice;
    @SerializedName("is_claim_auto_office")
    @Expose
    private Integer isClaimAutoOffice;
    @SerializedName("is_claim_prop_office")
    @Expose
    private Integer isClaimPropOffice;
    @SerializedName("is_claim_med_office")
    @Expose
    private Integer isClaimMedOffice;
    @SerializedName("worktime_arr")
    @Expose
    private List<WorktimeArr> worktimeArr = null;
    @SerializedName("schemaurl")
    @Expose
    private String schemaurl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getIsmain() {
        return ismain;
    }

    public void setIsmain(Integer ismain) {
        this.ismain = ismain;
    }

    public Integer getClaimoffice() {
        return claimoffice;
    }

    public void setClaimoffice(Integer claimoffice) {
        this.claimoffice = claimoffice;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getIsSellOffice() {
        return isSellOffice;
    }

    public void setIsSellOffice(Integer isSellOffice) {
        this.isSellOffice = isSellOffice;
    }

    public Integer getIsClaimAutoOffice() {
        return isClaimAutoOffice;
    }

    public void setIsClaimAutoOffice(Integer isClaimAutoOffice) {
        this.isClaimAutoOffice = isClaimAutoOffice;
    }

    public Integer getIsClaimPropOffice() {
        return isClaimPropOffice;
    }

    public void setIsClaimPropOffice(Integer isClaimPropOffice) {
        this.isClaimPropOffice = isClaimPropOffice;
    }

    public Integer getIsClaimMedOffice() {
        return isClaimMedOffice;
    }

    public void setIsClaimMedOffice(Integer isClaimMedOffice) {
        this.isClaimMedOffice = isClaimMedOffice;
    }

    public List<WorktimeArr> getWorktimeArr() {
        return worktimeArr;
    }

    public void setWorktimeArr(List<WorktimeArr> worktimeArr) {
        this.worktimeArr = worktimeArr;
    }

    public String getSchemaurl() {
        return schemaurl;
    }

    public void setSchemaurl(String schemaurl) {
        this.schemaurl = schemaurl;
    }

}
