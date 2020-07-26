package com.mhossam.rocknfit.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Plan implements Serializable, Parcelable
{

    @SerializedName("PlanID")
    @Expose
    private String planID;
    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("PlanName")
    @Expose
    private String planName;
    @SerializedName("CreationDate")
    @Expose
    private String creationDate;
    @SerializedName("IsEnabled")
    @Expose
    private String isEnabled;
    @SerializedName("DaysCounter")
    @Expose
    private String daysCounter;
    public final static Parcelable.Creator<Plan> CREATOR = new Creator<Plan>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        public Plan[] newArray(int size) {
            return (new Plan[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7767741890869629165L;

    protected Plan(Parcel in) {
        this.planID = ((String) in.readValue((String.class.getClassLoader())));
        this.accountID = ((String) in.readValue((String.class.getClassLoader())));
        this.planName = ((String) in.readValue((String.class.getClassLoader())));
        this.creationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.isEnabled = ((String) in.readValue((String.class.getClassLoader())));
        this.daysCounter = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Plan() {
    }

    public String getPlanID() {
        return planID;
    }

    public void setPlanID(String planID) {
        this.planID = planID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public String getDaysCounter() {
        return daysCounter;
    }

    public void setDaysCounter(String daysCounter) {
        this.daysCounter = daysCounter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("planID", planID).append("accountID", accountID).append("planName", planName).append("creationDate", creationDate).append("isEnabled", isEnabled).append("daysCounter", daysCounter).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(accountID).append(isEnabled).append(planName).append(planID).append(creationDate).append(daysCounter).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Plan) == false) {
            return false;
        }
        Plan rhs = ((Plan) other);
        return new EqualsBuilder().append(accountID, rhs.accountID).append(isEnabled, rhs.isEnabled).append(planName, rhs.planName).append(planID, rhs.planID).append(creationDate, rhs.creationDate).append(daysCounter, rhs.daysCounter).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(planID);
        dest.writeValue(accountID);
        dest.writeValue(planName);
        dest.writeValue(creationDate);
        dest.writeValue(isEnabled);
        dest.writeValue(daysCounter);
    }

    public int describeContents() {
        return 0;
    }

}