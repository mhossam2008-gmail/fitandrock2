package com.mhossam.rocknfit.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TrainingPlan implements Serializable
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
    private final static long serialVersionUID = -2535509673099634886L;

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
        if ((other instanceof TrainingPlan) == false) {
            return false;
        }
        TrainingPlan rhs = ((TrainingPlan) other);
        return new EqualsBuilder().append(accountID, rhs.accountID).append(isEnabled, rhs.isEnabled).append(planName, rhs.planName).append(planID, rhs.planID).append(creationDate, rhs.creationDate).append(daysCounter, rhs.daysCounter).isEquals();
    }

}