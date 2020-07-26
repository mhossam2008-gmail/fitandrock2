package com.mhossam.rocknfit.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TrainingClass implements Serializable, Parcelable
{

    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("AccountFirstName")
    @Expose
    private String accountFirstName;
    @SerializedName("AccountLastName")
    @Expose
    private String accountLastName;
    @SerializedName("AccountEmail")
    @Expose
    private String accountEmail;
    @SerializedName("AccountPassword")
    @Expose
    private String accountPassword;
    @SerializedName("AccountGender")
    @Expose
    private String accountGender;
    @SerializedName("AccountDOB")
    @Expose
    private String accountDOB;
    @SerializedName("AccountURL")
    @Expose
    private String accountURL;
    @SerializedName("AccountToken")
    @Expose
    private String accountToken;
    @SerializedName("AccountType")
    @Expose
    private String accountType;
    @SerializedName("LastLoginDate")
    @Expose
    private Object lastLoginDate;
    @SerializedName("LastLoginIP")
    @Expose
    private Object lastLoginIP;
    @SerializedName("LoginFailedTrials")
    @Expose
    private String loginFailedTrials;
    @SerializedName("LastPasswordChange")
    @Expose
    private Object lastPasswordChange;
    @SerializedName("AccountImage")
    @Expose
    private String accountImage;
    @SerializedName("AccountContainer")
    @Expose
    private String accountContainer;
    @SerializedName("CreationDate")
    @Expose
    private String creationDate;
    @SerializedName("IsLocked")
    @Expose
    private String isLocked;
    @SerializedName("IsEnabled")
    @Expose
    private String isEnabled;
    @SerializedName("BranchID")
    @Expose
    private String branchID;
    @SerializedName("BranchType")
    @Expose
    private String branchType;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("BranchAddress")
    @Expose
    private Object branchAddress;
    @SerializedName("BranchGender")
    @Expose
    private String branchGender;
    @SerializedName("WorkingHours")
    @Expose
    private Object workingHours;
    @SerializedName("ClassID")
    @Expose
    private String classID;
    @SerializedName("ClassStart")
    @Expose
    private String classStart;
    @SerializedName("ClassEnd")
    @Expose
    private String classEnd;
    @SerializedName("AdditionalInfo")
    @Expose
    private String additionalInfo;
    @SerializedName("CountryName")
    @Expose
    private Object countryName;
    @SerializedName("GovernorateName")
    @Expose
    private Object governorateName;
    @SerializedName("DistrictName")
    @Expose
    private Object districtName;
    @SerializedName("ClassName")
    @Expose
    private String className;
    @SerializedName("MediaType")
    @Expose
    private Object mediaType;
    @SerializedName("MediaDescription")
    @Expose
    private Object mediaDescription;
    public final static Parcelable.Creator<TrainingClass> CREATOR = new Creator<TrainingClass>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TrainingClass createFromParcel(Parcel in) {
            return new TrainingClass(in);
        }

        public TrainingClass[] newArray(int size) {
            return (new TrainingClass[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5845392933175154779L;

    protected TrainingClass(Parcel in) {
        this.accountID = ((String) in.readValue((String.class.getClassLoader())));
        this.accountFirstName = ((String) in.readValue((String.class.getClassLoader())));
        this.accountLastName = ((String) in.readValue((String.class.getClassLoader())));
        this.accountEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.accountPassword = ((String) in.readValue((String.class.getClassLoader())));
        this.accountGender = ((String) in.readValue((String.class.getClassLoader())));
        this.accountDOB = ((String) in.readValue((String.class.getClassLoader())));
        this.accountURL = ((String) in.readValue((String.class.getClassLoader())));
        this.accountToken = ((String) in.readValue((String.class.getClassLoader())));
        this.accountType = ((String) in.readValue((String.class.getClassLoader())));
        this.lastLoginDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.lastLoginIP = ((Object) in.readValue((Object.class.getClassLoader())));
        this.loginFailedTrials = ((String) in.readValue((String.class.getClassLoader())));
        this.lastPasswordChange = ((Object) in.readValue((Object.class.getClassLoader())));
        this.accountImage = ((String) in.readValue((String.class.getClassLoader())));
        this.accountContainer = ((String) in.readValue((String.class.getClassLoader())));
        this.creationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.isLocked = ((String) in.readValue((String.class.getClassLoader())));
        this.isEnabled = ((String) in.readValue((String.class.getClassLoader())));
        this.branchID = ((String) in.readValue((String.class.getClassLoader())));
        this.branchType = ((String) in.readValue((String.class.getClassLoader())));
        this.branchName = ((String) in.readValue((String.class.getClassLoader())));
        this.branchAddress = ((Object) in.readValue((Object.class.getClassLoader())));
        this.branchGender = ((String) in.readValue((String.class.getClassLoader())));
        this.workingHours = ((Object) in.readValue((Object.class.getClassLoader())));
        this.classID = ((String) in.readValue((String.class.getClassLoader())));
        this.classStart = ((String) in.readValue((String.class.getClassLoader())));
        this.classEnd = ((String) in.readValue((String.class.getClassLoader())));
        this.additionalInfo = ((String) in.readValue((String.class.getClassLoader())));
        this.countryName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.governorateName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.districtName = ((Object) in.readValue((Object.class.getClassLoader())));
        this.className = ((String) in.readValue((String.class.getClassLoader())));
        this.mediaType = ((Object) in.readValue((Object.class.getClassLoader())));
        this.mediaDescription = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public TrainingClass() {
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountFirstName() {
        return accountFirstName;
    }

    public void setAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
    }

    public String getAccountLastName() {
        return accountLastName;
    }

    public void setAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getAccountGender() {
        return accountGender;
    }

    public void setAccountGender(String accountGender) {
        this.accountGender = accountGender;
    }

    public String getAccountDOB() {
        return accountDOB;
    }

    public void setAccountDOB(String accountDOB) {
        this.accountDOB = accountDOB;
    }

    public String getAccountURL() {
        return accountURL;
    }

    public void setAccountURL(String accountURL) {
        this.accountURL = accountURL;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Object getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Object lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Object getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(Object lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getLoginFailedTrials() {
        return loginFailedTrials;
    }

    public void setLoginFailedTrials(String loginFailedTrials) {
        this.loginFailedTrials = loginFailedTrials;
    }

    public Object getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(Object lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }

    public String getAccountContainer() {
        return accountContainer;
    }

    public void setAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Object getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(Object branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchGender() {
        return branchGender;
    }

    public void setBranchGender(String branchGender) {
        this.branchGender = branchGender;
    }

    public Object getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Object workingHours) {
        this.workingHours = workingHours;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassStart() {
        return classStart;
    }

    public void setClassStart(String classStart) {
        this.classStart = classStart;
    }

    public String getClassEnd() {
        return classEnd;
    }

    public void setClassEnd(String classEnd) {
        this.classEnd = classEnd;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Object getCountryName() {
        return countryName;
    }

    public void setCountryName(Object countryName) {
        this.countryName = countryName;
    }

    public Object getGovernorateName() {
        return governorateName;
    }

    public void setGovernorateName(Object governorateName) {
        this.governorateName = governorateName;
    }

    public Object getDistrictName() {
        return districtName;
    }

    public void setDistrictName(Object districtName) {
        this.districtName = districtName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getMediaType() {
        return mediaType;
    }

    public void setMediaType(Object mediaType) {
        this.mediaType = mediaType;
    }

    public Object getMediaDescription() {
        return mediaDescription;
    }

    public void setMediaDescription(Object mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("accountID", accountID).append("accountFirstName", accountFirstName).append("accountLastName", accountLastName).append("accountEmail", accountEmail).append("accountPassword", accountPassword).append("accountGender", accountGender).append("accountDOB", accountDOB).append("accountURL", accountURL).append("accountToken", accountToken).append("accountType", accountType).append("lastLoginDate", lastLoginDate).append("lastLoginIP", lastLoginIP).append("loginFailedTrials", loginFailedTrials).append("lastPasswordChange", lastPasswordChange).append("accountImage", accountImage).append("accountContainer", accountContainer).append("creationDate", creationDate).append("isLocked", isLocked).append("isEnabled", isEnabled).append("branchID", branchID).append("branchType", branchType).append("branchName", branchName).append("branchAddress", branchAddress).append("branchGender", branchGender).append("workingHours", workingHours).append("classID", classID).append("classStart", classStart).append("classEnd", classEnd).append("additionalInfo", additionalInfo).append("countryName", countryName).append("governorateName", governorateName).append("districtName", districtName).append("className", className).append("mediaType", mediaType).append("mediaDescription", mediaDescription).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(loginFailedTrials).append(branchID).append(classStart).append(mediaDescription).append(accountLastName).append(classEnd).append(branchAddress).append(className).append(lastLoginDate).append(accountID).append(accountDOB).append(isLocked).append(branchGender).append(additionalInfo).append(branchType).append(workingHours).append(accountURL).append(accountFirstName).append(districtName).append(accountType).append(accountGender).append(branchName).append(mediaType).append(creationDate).append(lastLoginIP).append(governorateName).append(accountContainer).append(classID).append(accountEmail).append(isEnabled).append(accountImage).append(lastPasswordChange).append(countryName).append(accountToken).append(accountPassword).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TrainingClass) == false) {
            return false;
        }
        TrainingClass rhs = ((TrainingClass) other);
        return new EqualsBuilder().append(loginFailedTrials, rhs.loginFailedTrials).append(branchID, rhs.branchID).append(classStart, rhs.classStart).append(mediaDescription, rhs.mediaDescription).append(accountLastName, rhs.accountLastName).append(classEnd, rhs.classEnd).append(branchAddress, rhs.branchAddress).append(className, rhs.className).append(lastLoginDate, rhs.lastLoginDate).append(accountID, rhs.accountID).append(accountDOB, rhs.accountDOB).append(isLocked, rhs.isLocked).append(branchGender, rhs.branchGender).append(additionalInfo, rhs.additionalInfo).append(branchType, rhs.branchType).append(workingHours, rhs.workingHours).append(accountURL, rhs.accountURL).append(accountFirstName, rhs.accountFirstName).append(districtName, rhs.districtName).append(accountType, rhs.accountType).append(accountGender, rhs.accountGender).append(branchName, rhs.branchName).append(mediaType, rhs.mediaType).append(creationDate, rhs.creationDate).append(lastLoginIP, rhs.lastLoginIP).append(governorateName, rhs.governorateName).append(accountContainer, rhs.accountContainer).append(classID, rhs.classID).append(accountEmail, rhs.accountEmail).append(isEnabled, rhs.isEnabled).append(accountImage, rhs.accountImage).append(lastPasswordChange, rhs.lastPasswordChange).append(countryName, rhs.countryName).append(accountToken, rhs.accountToken).append(accountPassword, rhs.accountPassword).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accountID);
        dest.writeValue(accountFirstName);
        dest.writeValue(accountLastName);
        dest.writeValue(accountEmail);
        dest.writeValue(accountPassword);
        dest.writeValue(accountGender);
        dest.writeValue(accountDOB);
        dest.writeValue(accountURL);
        dest.writeValue(accountToken);
        dest.writeValue(accountType);
        dest.writeValue(lastLoginDate);
        dest.writeValue(lastLoginIP);
        dest.writeValue(loginFailedTrials);
        dest.writeValue(lastPasswordChange);
        dest.writeValue(accountImage);
        dest.writeValue(accountContainer);
        dest.writeValue(creationDate);
        dest.writeValue(isLocked);
        dest.writeValue(isEnabled);
        dest.writeValue(branchID);
        dest.writeValue(branchType);
        dest.writeValue(branchName);
        dest.writeValue(branchAddress);
        dest.writeValue(branchGender);
        dest.writeValue(workingHours);
        dest.writeValue(classID);
        dest.writeValue(classStart);
        dest.writeValue(classEnd);
        dest.writeValue(additionalInfo);
        dest.writeValue(countryName);
        dest.writeValue(governorateName);
        dest.writeValue(districtName);
        dest.writeValue(className);
        dest.writeValue(mediaType);
        dest.writeValue(mediaDescription);
    }

    public int describeContents() {
        return 0;
    }

}