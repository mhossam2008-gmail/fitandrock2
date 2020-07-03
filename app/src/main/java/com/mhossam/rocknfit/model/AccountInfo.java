package com.mhossam.rocknfit.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
public class AccountInfo implements Serializable {

    private final static long serialVersionUID = -1566614624387266039L;
    @SerializedName("AccountID")
    @Expose
    @PrimaryKey
    @NotNull
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
    @SerializedName("AccountTypeShort")
    @Expose
    private String accountTypeShort;
    @SerializedName("AccountType")
    @Expose
    private String accountType;
    @SerializedName("LastLoginDate")
    @Expose
    private String lastLoginDate;
    @SerializedName("LastLoginIP")
    @Expose
    private String lastLoginIP;
    @SerializedName("LoginFailedTrials")
    @Expose
    private String loginFailedTrials;
    @SerializedName("LastPasswordChange")
    @Expose
    private String lastPasswordChange;
    @SerializedName("AccountImage")
    @Expose
    private String accountImage;
    @SerializedName("AccountContainer")
    @Expose
    private String accountContainer;
    @SerializedName("AccountCover")
    @Expose
    private String accountCover;
    @SerializedName("AccountPoints")
    @Expose
    private String accountPoints;
    @SerializedName("CreationDate")
    @Expose
    private String creationDate;
    @SerializedName("IsLocked")
    @Expose
    private String isLocked;
    @SerializedName("IsEnabled")
    @Expose
    private String isEnabled;
    @SerializedName("AccountFullName")
    @Expose
    private String accountFullName;
    @SerializedName("CountryID")
    @Expose
    private String countryID;
    @SerializedName("GovernorateID")
    @Expose
    private String governorateID;
    @SerializedName("DistrictID")
    @Expose
    private String districtID;
    @SerializedName("ContactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("LittleDescription")
    @Expose
    private String littleDescription;
    @SerializedName("Hobbies")
    @Expose
    private String hobbies;
    @SerializedName("MaritalStatus")
    @Expose
    private String maritalStatus;
    @SerializedName("FacebookUrl")
    @Expose
    private String facebookUrl;
    @SerializedName("TwitterUrl")
    @Expose
    private String twitterUrl;
    @SerializedName("WhoCanFollow")
    @Expose
    private String whoCanFollow;
    @SerializedName("WhoCanViewProfile")
    @Expose
    private String whoCanViewProfile;
    @SerializedName("EmailNotification")
    @Expose
    private String emailNotification;
    @SerializedName("FriendBirthdayNotify")
    @Expose
    private String friendBirthdayNotify;
    @SerializedName("ReadyForQuestions")
    @Expose
    private String readyForQuestions;
    @SerializedName("GenderWeTrain")
    @Expose
    private String genderWeTrain;
    @SerializedName("AgeWeTrain")
    @Expose
    private String ageWeTrain;
    @SerializedName("NeedOnlineTrainer")
    @Expose
    private String needOnlineTrainer;
    @SerializedName("PreferredTrainerGender")
    @Expose
    private String preferredTrainerGender;
    @SerializedName("WhereToTrain")
    @Expose
    private String whereToTrain;
    @SerializedName("WhenToTrainStart")
    @Expose
    private String whenToTrainStart;
    @SerializedName("WhenToTrainEnd")
    @Expose
    private String whenToTrainEnd;
    @SerializedName("ActivityID")
    @Expose
    private String activityID;
    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("GovernorateName")
    @Expose
    private String governorateName;
    @SerializedName("DistrictName")
    @Expose
    private String districtName;
    @SerializedName("ProfilePicturePath")
    @Expose
    private String profilePicturePath;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public AccountInfo withAccountID(String accountID) {
        this.accountID = accountID;
        return this;
    }

    public String getAccountFirstName() {
        return accountFirstName;
    }

    public void setAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
    }

    public AccountInfo withAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
        return this;
    }

    public String getAccountLastName() {
        return accountLastName;
    }

    public void setAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
    }

    public AccountInfo withAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
        return this;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public AccountInfo withAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
        return this;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public AccountInfo withAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
        return this;
    }

    public String getAccountGender() {
        return accountGender;
    }

    public void setAccountGender(String accountGender) {
        this.accountGender = accountGender;
    }

    public AccountInfo withAccountGender(String accountGender) {
        this.accountGender = accountGender;
        return this;
    }

    public String getAccountDOB() {
        return accountDOB;
    }

    public void setAccountDOB(String accountDOB) {
        this.accountDOB = accountDOB;
    }

    public AccountInfo withAccountDOB(String accountDOB) {
        this.accountDOB = accountDOB;
        return this;
    }

    public String getAccountURL() {
        return accountURL;
    }

    public void setAccountURL(String accountURL) {
        this.accountURL = accountURL;
    }

    public AccountInfo withAccountURL(String accountURL) {
        this.accountURL = accountURL;
        return this;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public AccountInfo withAccountToken(String accountToken) {
        this.accountToken = accountToken;
        return this;
    }

    public String getAccountTypeShort() {
        return accountTypeShort;
    }

    public void setAccountTypeShort(String accountTypeShort) {
        this.accountTypeShort = accountTypeShort;
    }

    public AccountInfo withAccountTypeShort(String accountTypeShort) {
        this.accountTypeShort = accountTypeShort;
        return this;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public AccountInfo withAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public AccountInfo withLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public AccountInfo withLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
        return this;
    }

    public String getLoginFailedTrials() {
        return loginFailedTrials;
    }

    public void setLoginFailedTrials(String loginFailedTrials) {
        this.loginFailedTrials = loginFailedTrials;
    }

    public AccountInfo withLoginFailedTrials(String loginFailedTrials) {
        this.loginFailedTrials = loginFailedTrials;
        return this;
    }

    public String getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public AccountInfo withLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
        return this;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }

    public AccountInfo withAccountImage(String accountImage) {
        this.accountImage = accountImage;
        return this;
    }

    public String getAccountContainer() {
        return accountContainer;
    }

    public void setAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
    }

    public AccountInfo withAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
        return this;
    }

    public String getAccountCover() {
        return accountCover;
    }

    public void setAccountCover(String accountCover) {
        this.accountCover = accountCover;
    }

    public AccountInfo withAccountCover(String accountCover) {
        this.accountCover = accountCover;
        return this;
    }

    public String getAccountPoints() {
        return accountPoints;
    }

    public void setAccountPoints(String accountPoints) {
        this.accountPoints = accountPoints;
    }

    public AccountInfo withAccountPoints(String accountPoints) {
        this.accountPoints = accountPoints;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public AccountInfo withCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public AccountInfo withIsLocked(String isLocked) {
        this.isLocked = isLocked;
        return this;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public AccountInfo withIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public String getAccountFullName() {
        return accountFullName;
    }

    public void setAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
    }

    public AccountInfo withAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
        return this;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public AccountInfo withCountryID(String countryID) {
        this.countryID = countryID;
        return this;
    }

    public String getGovernorateID() {
        return governorateID;
    }

    public void setGovernorateID(String governorateID) {
        this.governorateID = governorateID;
    }

    public AccountInfo withGovernorateID(String governorateID) {
        this.governorateID = governorateID;
        return this;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public AccountInfo withDistrictID(String districtID) {
        this.districtID = districtID;
        return this;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public AccountInfo withContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
        return this;
    }

    public String getLittleDescription() {
        return littleDescription;
    }

    public void setLittleDescription(String littleDescription) {
        this.littleDescription = littleDescription;
    }

    public AccountInfo withLittleDescription(String littleDescription) {
        this.littleDescription = littleDescription;
        return this;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public AccountInfo withHobbies(String hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public AccountInfo withMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public AccountInfo withFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
        return this;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public AccountInfo withTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
        return this;
    }

    public String getWhoCanFollow() {
        return whoCanFollow;
    }

    public void setWhoCanFollow(String whoCanFollow) {
        this.whoCanFollow = whoCanFollow;
    }

    public AccountInfo withWhoCanFollow(String whoCanFollow) {
        this.whoCanFollow = whoCanFollow;
        return this;
    }

    public String getWhoCanViewProfile() {
        return whoCanViewProfile;
    }

    public void setWhoCanViewProfile(String whoCanViewProfile) {
        this.whoCanViewProfile = whoCanViewProfile;
    }

    public AccountInfo withWhoCanViewProfile(String whoCanViewProfile) {
        this.whoCanViewProfile = whoCanViewProfile;
        return this;
    }

    public String getEmailNotification() {
        return emailNotification;
    }

    public void setEmailNotification(String emailNotification) {
        this.emailNotification = emailNotification;
    }

    public AccountInfo withEmailNotification(String emailNotification) {
        this.emailNotification = emailNotification;
        return this;
    }

    public String getFriendBirthdayNotify() {
        return friendBirthdayNotify;
    }

    public void setFriendBirthdayNotify(String friendBirthdayNotify) {
        this.friendBirthdayNotify = friendBirthdayNotify;
    }

    public AccountInfo withFriendBirthdayNotify(String friendBirthdayNotify) {
        this.friendBirthdayNotify = friendBirthdayNotify;
        return this;
    }

    public String getReadyForQuestions() {
        return readyForQuestions;
    }

    public void setReadyForQuestions(String readyForQuestions) {
        this.readyForQuestions = readyForQuestions;
    }

    public AccountInfo withReadyForQuestions(String readyForQuestions) {
        this.readyForQuestions = readyForQuestions;
        return this;
    }

    public String getGenderWeTrain() {
        return genderWeTrain;
    }

    public void setGenderWeTrain(String genderWeTrain) {
        this.genderWeTrain = genderWeTrain;
    }

    public AccountInfo withGenderWeTrain(String genderWeTrain) {
        this.genderWeTrain = genderWeTrain;
        return this;
    }

    public String getAgeWeTrain() {
        return ageWeTrain;
    }

    public void setAgeWeTrain(String ageWeTrain) {
        this.ageWeTrain = ageWeTrain;
    }

    public AccountInfo withAgeWeTrain(String ageWeTrain) {
        this.ageWeTrain = ageWeTrain;
        return this;
    }

    public String getNeedOnlineTrainer() {
        return needOnlineTrainer;
    }

    public void setNeedOnlineTrainer(String needOnlineTrainer) {
        this.needOnlineTrainer = needOnlineTrainer;
    }

    public AccountInfo withNeedOnlineTrainer(String needOnlineTrainer) {
        this.needOnlineTrainer = needOnlineTrainer;
        return this;
    }

    public String getPreferredTrainerGender() {
        return preferredTrainerGender;
    }

    public void setPreferredTrainerGender(String preferredTrainerGender) {
        this.preferredTrainerGender = preferredTrainerGender;
    }

    public AccountInfo withPreferredTrainerGender(String preferredTrainerGender) {
        this.preferredTrainerGender = preferredTrainerGender;
        return this;
    }

    public String getWhereToTrain() {
        return whereToTrain;
    }

    public void setWhereToTrain(String whereToTrain) {
        this.whereToTrain = whereToTrain;
    }

    public AccountInfo withWhereToTrain(String whereToTrain) {
        this.whereToTrain = whereToTrain;
        return this;
    }

    public String getWhenToTrainStart() {
        return whenToTrainStart;
    }

    public void setWhenToTrainStart(String whenToTrainStart) {
        this.whenToTrainStart = whenToTrainStart;
    }

    public AccountInfo withWhenToTrainStart(String whenToTrainStart) {
        this.whenToTrainStart = whenToTrainStart;
        return this;
    }

    public String getWhenToTrainEnd() {
        return whenToTrainEnd;
    }

    public void setWhenToTrainEnd(String whenToTrainEnd) {
        this.whenToTrainEnd = whenToTrainEnd;
    }

    public AccountInfo withWhenToTrainEnd(String whenToTrainEnd) {
        this.whenToTrainEnd = whenToTrainEnd;
        return this;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public AccountInfo withActivityID(String activityID) {
        this.activityID = activityID;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public AccountInfo withCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public String getGovernorateName() {
        return governorateName;
    }

    public void setGovernorateName(String governorateName) {
        this.governorateName = governorateName;
    }

    public AccountInfo withGovernorateName(String governorateName) {
        this.governorateName = governorateName;
        return this;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public AccountInfo withDistrictName(String districtName) {
        this.districtName = districtName;
        return this;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public AccountInfo withProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("accountID", accountID).append("accountFirstName", accountFirstName).append("accountLastName", accountLastName).append("accountEmail", accountEmail).append("accountPassword", accountPassword).append("accountGender", accountGender).append("accountDOB", accountDOB).append("accountURL", accountURL).append("accountToken", accountToken).append("accountTypeShort", accountTypeShort).append("accountType", accountType).append("lastLoginDate", lastLoginDate).append("lastLoginIP", lastLoginIP).append("loginFailedTrials", loginFailedTrials).append("lastPasswordChange", lastPasswordChange).append("accountImage", accountImage).append("accountContainer", accountContainer).append("accountCover", accountCover).append("accountPoints", accountPoints).append("creationDate", creationDate).append("isLocked", isLocked).append("isEnabled", isEnabled).append("accountFullName", accountFullName).append("countryID", countryID).append("governorateID", governorateID).append("districtID", districtID).append("contactNumber", contactNumber).append("littleDescription", littleDescription).append("hobbies", hobbies).append("maritalStatus", maritalStatus).append("facebookUrl", facebookUrl).append("twitterUrl", twitterUrl).append("whoCanFollow", whoCanFollow).append("whoCanViewProfile", whoCanViewProfile).append("emailNotification", emailNotification).append("friendBirthdayNotify", friendBirthdayNotify).append("readyForQuestions", readyForQuestions).append("genderWeTrain", genderWeTrain).append("ageWeTrain", ageWeTrain).append("needOnlineTrainer", needOnlineTrainer).append("preferredTrainerGender", preferredTrainerGender).append("whereToTrain", whereToTrain).append("whenToTrainStart", whenToTrainStart).append("whenToTrainEnd", whenToTrainEnd).append("activityID", activityID).append("countryName", countryName).append("governorateName", governorateName).append("districtName", districtName).append("profilePicturePath", profilePicturePath).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(loginFailedTrials).append(accountLastName).append(friendBirthdayNotify).append(twitterUrl).append(accountCover).append(accountID).append(preferredTrainerGender).append(districtID).append(isLocked).append(needOnlineTrainer).append(accountTypeShort).append(accountURL).append(accountType).append(accountGender).append(creationDate).append(genderWeTrain).append(countryID).append(accountContainer).append(isEnabled).append(countryName).append(accountToken).append(whoCanFollow).append(accountPoints).append(maritalStatus).append(littleDescription).append(ageWeTrain).append(readyForQuestions).append(lastLoginDate).append(facebookUrl).append(accountDOB).append(contactNumber).append(accountFullName).append(profilePicturePath).append(emailNotification).append(accountFirstName).append(districtName).append(whenToTrainStart).append(lastLoginIP).append(governorateName).append(governorateID).append(activityID).append(accountEmail).append(hobbies).append(whoCanViewProfile).append(whenToTrainEnd).append(whereToTrain).append(accountImage).append(lastPasswordChange).append(accountPassword).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AccountInfo) == false) {
            return false;
        }
        AccountInfo rhs = ((AccountInfo) other);
        return new EqualsBuilder().append(loginFailedTrials, rhs.loginFailedTrials).append(accountLastName, rhs.accountLastName).append(friendBirthdayNotify, rhs.friendBirthdayNotify).append(twitterUrl, rhs.twitterUrl).append(accountCover, rhs.accountCover).append(accountID, rhs.accountID).append(preferredTrainerGender, rhs.preferredTrainerGender).append(districtID, rhs.districtID).append(isLocked, rhs.isLocked).append(needOnlineTrainer, rhs.needOnlineTrainer).append(accountTypeShort, rhs.accountTypeShort).append(accountURL, rhs.accountURL).append(accountType, rhs.accountType).append(accountGender, rhs.accountGender).append(creationDate, rhs.creationDate).append(genderWeTrain, rhs.genderWeTrain).append(countryID, rhs.countryID).append(accountContainer, rhs.accountContainer).append(isEnabled, rhs.isEnabled).append(countryName, rhs.countryName).append(accountToken, rhs.accountToken).append(whoCanFollow, rhs.whoCanFollow).append(accountPoints, rhs.accountPoints).append(maritalStatus, rhs.maritalStatus).append(littleDescription, rhs.littleDescription).append(ageWeTrain, rhs.ageWeTrain).append(readyForQuestions, rhs.readyForQuestions).append(lastLoginDate, rhs.lastLoginDate).append(facebookUrl, rhs.facebookUrl).append(accountDOB, rhs.accountDOB).append(contactNumber, rhs.contactNumber).append(accountFullName, rhs.accountFullName).append(profilePicturePath, rhs.profilePicturePath).append(emailNotification, rhs.emailNotification).append(accountFirstName, rhs.accountFirstName).append(districtName, rhs.districtName).append(whenToTrainStart, rhs.whenToTrainStart).append(lastLoginIP, rhs.lastLoginIP).append(governorateName, rhs.governorateName).append(governorateID, rhs.governorateID).append(activityID, rhs.activityID).append(accountEmail, rhs.accountEmail).append(hobbies, rhs.hobbies).append(whoCanViewProfile, rhs.whoCanViewProfile).append(whenToTrainEnd, rhs.whenToTrainEnd).append(whereToTrain, rhs.whereToTrain).append(accountImage, rhs.accountImage).append(lastPasswordChange, rhs.lastPasswordChange).append(accountPassword, rhs.accountPassword).isEquals();
    }

}
