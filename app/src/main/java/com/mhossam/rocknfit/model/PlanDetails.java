package com.mhossam.rocknfit.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PlanDetails implements Serializable, Parcelable
{

    @SerializedName("DetailID")
    @Expose
    private String detailID;
    @SerializedName("PlanDayID")
    @Expose
    private String planDayID;
    @SerializedName("DetailType")
    @Expose
    private String detailType;
    @SerializedName("WorkoutID")
    @Expose
    private String workoutID;
    @SerializedName("ExerciseID")
    @Expose
    private String exerciseID;
    @SerializedName("SetsNo")
    @Expose
    private String setsNo;
    @SerializedName("RepsNo")
    @Expose
    private String repsNo;
    @SerializedName("KG")
    @Expose
    private String kG;
    @SerializedName("VideoLink")
    @Expose
    private String videoLink;
    @SerializedName("Notes")
    @Expose
    private String notes;
    @SerializedName("DayNumber")
    @Expose
    private String dayNumber;
    @SerializedName("PlanID")
    @Expose
    private String planID;
    @SerializedName("PlanName")
    @Expose
    private String planName;
    @SerializedName("WorkoutName")
    @Expose
    private String workoutName;
    @SerializedName("ExerciseName")
    @Expose
    private String exerciseName;
    @SerializedName("StartImage")
    @Expose
    private String startImage;
    @SerializedName("EndImage")
    @Expose
    private String endImage;
    @SerializedName("VideoURL")
    @Expose
    private String videoURL;
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
    @SerializedName("CreationDate")
    @Expose
    private String creationDate;
    @SerializedName("IsLocked")
    @Expose
    private String isLocked;
    @SerializedName("IsEnabled")
    @Expose
    private String isEnabled;
    public final static Parcelable.Creator<PlanDetails> CREATOR = new Creator<PlanDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PlanDetails createFromParcel(Parcel in) {
            return new PlanDetails(in);
        }

        public PlanDetails[] newArray(int size) {
            return (new PlanDetails[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5164954621318559186L;

    protected PlanDetails(Parcel in) {
        this.detailID = ((String) in.readValue((String.class.getClassLoader())));
        this.planDayID = ((String) in.readValue((String.class.getClassLoader())));
        this.detailType = ((String) in.readValue((String.class.getClassLoader())));
        this.workoutID = ((String) in.readValue((String.class.getClassLoader())));
        this.exerciseID = ((String) in.readValue((String.class.getClassLoader())));
        this.setsNo = ((String) in.readValue((String.class.getClassLoader())));
        this.repsNo = ((String) in.readValue((String.class.getClassLoader())));
        this.kG = ((String) in.readValue((String.class.getClassLoader())));
        this.videoLink = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
        this.dayNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.planID = ((String) in.readValue((String.class.getClassLoader())));
        this.planName = ((String) in.readValue((String.class.getClassLoader())));
        this.workoutName = ((String) in.readValue((String.class.getClassLoader())));
        this.exerciseName = ((String) in.readValue((String.class.getClassLoader())));
        this.startImage = ((String) in.readValue((String.class.getClassLoader())));
        this.endImage = ((String) in.readValue((String.class.getClassLoader())));
        this.videoURL = ((String) in.readValue((String.class.getClassLoader())));
        this.accountID = ((String) in.readValue((String.class.getClassLoader())));
        this.accountFirstName = ((String) in.readValue((String.class.getClassLoader())));
        this.accountLastName = ((String) in.readValue((String.class.getClassLoader())));
        this.accountEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.accountPassword = ((String) in.readValue((String.class.getClassLoader())));
        this.accountGender = ((String) in.readValue((String.class.getClassLoader())));
        this.accountDOB = ((String) in.readValue((String.class.getClassLoader())));
        this.accountURL = ((String) in.readValue((String.class.getClassLoader())));
        this.accountToken = ((String) in.readValue((String.class.getClassLoader())));
        this.accountTypeShort = ((String) in.readValue((String.class.getClassLoader())));
        this.accountType = ((String) in.readValue((String.class.getClassLoader())));
        this.lastLoginDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastLoginIP = ((String) in.readValue((String.class.getClassLoader())));
        this.loginFailedTrials = ((String) in.readValue((String.class.getClassLoader())));
        this.lastPasswordChange = ((String) in.readValue((String.class.getClassLoader())));
        this.accountImage = ((String) in.readValue((String.class.getClassLoader())));
        this.accountContainer = ((String) in.readValue((String.class.getClassLoader())));
        this.creationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.isLocked = ((String) in.readValue((String.class.getClassLoader())));
        this.isEnabled = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PlanDetails() {
    }

    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public String getPlanDayID() {
        return planDayID;
    }

    public void setPlanDayID(String planDayID) {
        this.planDayID = planDayID;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getWorkoutID() {
        return workoutID;
    }

    public void setWorkoutID(String workoutID) {
        this.workoutID = workoutID;
    }

    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    public String getSetsNo() {
        return setsNo;
    }

    public void setSetsNo(String setsNo) {
        this.setsNo = setsNo;
    }

    public String getRepsNo() {
        return repsNo;
    }

    public void setRepsNo(String repsNo) {
        this.repsNo = repsNo;
    }

    public String getKG() {
        return kG;
    }

    public void setKG(String kG) {
        this.kG = kG;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(String dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getStartImage() {
        return startImage;
    }

    public void setStartImage(String startImage) {
        this.startImage = startImage;
    }

    public String getEndImage() {
        return endImage;
    }

    public void setEndImage(String endImage) {
        this.endImage = endImage;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
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

    public String getAccountTypeShort() {
        return accountTypeShort;
    }

    public void setAccountTypeShort(String accountTypeShort) {
        this.accountTypeShort = accountTypeShort;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getLoginFailedTrials() {
        return loginFailedTrials;
    }

    public void setLoginFailedTrials(String loginFailedTrials) {
        this.loginFailedTrials = loginFailedTrials;
    }

    public String getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(String lastPasswordChange) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("detailID", detailID).append("planDayID", planDayID).append("detailType", detailType).append("workoutID", workoutID).append("exerciseID", exerciseID).append("setsNo", setsNo).append("repsNo", repsNo).append("kG", kG).append("videoLink", videoLink).append("notes", notes).append("dayNumber", dayNumber).append("planID", planID).append("planName", planName).append("workoutName", workoutName).append("exerciseName", exerciseName).append("startImage", startImage).append("endImage", endImage).append("videoURL", videoURL).append("accountID", accountID).append("accountFirstName", accountFirstName).append("accountLastName", accountLastName).append("accountEmail", accountEmail).append("accountPassword", accountPassword).append("accountGender", accountGender).append("accountDOB", accountDOB).append("accountURL", accountURL).append("accountToken", accountToken).append("accountTypeShort", accountTypeShort).append("accountType", accountType).append("lastLoginDate", lastLoginDate).append("lastLoginIP", lastLoginIP).append("loginFailedTrials", loginFailedTrials).append("lastPasswordChange", lastPasswordChange).append("accountImage", accountImage).append("accountContainer", accountContainer).append("creationDate", creationDate).append("isLocked", isLocked).append("isEnabled", isEnabled).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(loginFailedTrials).append(planDayID).append(notes).append(accountLastName).append(workoutName).append(planName).append(lastLoginDate).append(detailType).append(accountID).append(videoURL).append(accountDOB).append(isLocked).append(dayNumber).append(workoutID).append(repsNo).append(startImage).append(accountTypeShort).append(accountURL).append(endImage).append(accountFirstName).append(exerciseID).append(accountType).append(detailID).append(accountGender).append(creationDate).append(lastLoginIP).append(accountContainer).append(setsNo).append(exerciseName).append(accountEmail).append(isEnabled).append(videoLink).append(planID).append(accountImage).append(lastPasswordChange).append(kG).append(accountToken).append(accountPassword).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PlanDetails) == false) {
            return false;
        }
        PlanDetails rhs = ((PlanDetails) other);
        return new EqualsBuilder().append(loginFailedTrials, rhs.loginFailedTrials).append(planDayID, rhs.planDayID).append(notes, rhs.notes).append(accountLastName, rhs.accountLastName).append(workoutName, rhs.workoutName).append(planName, rhs.planName).append(lastLoginDate, rhs.lastLoginDate).append(detailType, rhs.detailType).append(accountID, rhs.accountID).append(videoURL, rhs.videoURL).append(accountDOB, rhs.accountDOB).append(isLocked, rhs.isLocked).append(dayNumber, rhs.dayNumber).append(workoutID, rhs.workoutID).append(repsNo, rhs.repsNo).append(startImage, rhs.startImage).append(accountTypeShort, rhs.accountTypeShort).append(accountURL, rhs.accountURL).append(endImage, rhs.endImage).append(accountFirstName, rhs.accountFirstName).append(exerciseID, rhs.exerciseID).append(accountType, rhs.accountType).append(detailID, rhs.detailID).append(accountGender, rhs.accountGender).append(creationDate, rhs.creationDate).append(lastLoginIP, rhs.lastLoginIP).append(accountContainer, rhs.accountContainer).append(setsNo, rhs.setsNo).append(exerciseName, rhs.exerciseName).append(accountEmail, rhs.accountEmail).append(isEnabled, rhs.isEnabled).append(videoLink, rhs.videoLink).append(planID, rhs.planID).append(accountImage, rhs.accountImage).append(lastPasswordChange, rhs.lastPasswordChange).append(kG, rhs.kG).append(accountToken, rhs.accountToken).append(accountPassword, rhs.accountPassword).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(detailID);
        dest.writeValue(planDayID);
        dest.writeValue(detailType);
        dest.writeValue(workoutID);
        dest.writeValue(exerciseID);
        dest.writeValue(setsNo);
        dest.writeValue(repsNo);
        dest.writeValue(kG);
        dest.writeValue(videoLink);
        dest.writeValue(notes);
        dest.writeValue(dayNumber);
        dest.writeValue(planID);
        dest.writeValue(planName);
        dest.writeValue(workoutName);
        dest.writeValue(exerciseName);
        dest.writeValue(startImage);
        dest.writeValue(endImage);
        dest.writeValue(videoURL);
        dest.writeValue(accountID);
        dest.writeValue(accountFirstName);
        dest.writeValue(accountLastName);
        dest.writeValue(accountEmail);
        dest.writeValue(accountPassword);
        dest.writeValue(accountGender);
        dest.writeValue(accountDOB);
        dest.writeValue(accountURL);
        dest.writeValue(accountToken);
        dest.writeValue(accountTypeShort);
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
    }

    public int describeContents() {
        return 0;
    }

}
