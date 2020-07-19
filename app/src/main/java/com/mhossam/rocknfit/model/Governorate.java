package com.mhossam.rocknfit.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;

@Entity
public class Governorate implements Serializable, Parcelable
{

    @SerializedName("GovernorateID")
    @Expose
    @PrimaryKey
    @NotNull
    private String governorateID;
    @SerializedName("CountryID")
    @Expose
    private String countryID;
    @SerializedName("GovernorateName")
    @Expose
    private String governorateName;
    @SerializedName("IsDefault")
    @Expose
    private String isDefault;
    @SerializedName("IsEnabled")
    @Expose
    private String isEnabled;
    public final static Parcelable.Creator<Governorate> CREATOR = new Creator<Governorate>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Governorate createFromParcel(Parcel in) {
            return new Governorate(in);
        }

        public Governorate[] newArray(int size) {
            return (new Governorate[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5387759782477293025L;

    protected Governorate(Parcel in) {
        this.governorateID = ((String) in.readValue((String.class.getClassLoader())));
        this.countryID = ((String) in.readValue((String.class.getClassLoader())));
        this.governorateName = ((String) in.readValue((String.class.getClassLoader())));
        this.isDefault = ((String) in.readValue((String.class.getClassLoader())));
        this.isEnabled = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Governorate() {
    }

    public String getGovernorateID() {
        return governorateID;
    }

    public void setGovernorateID(String governorateID) {
        this.governorateID = governorateID;
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getGovernorateName() {
        return governorateName;
    }

    public void setGovernorateName(String governorateName) {
        this.governorateName = governorateName;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    public String toString() {
        return governorateName;
//                new ToStringBuilder(this).append("governorateID", governorateID).append("countryID", countryID).append("governorateName", governorateName).append("isDefault", isDefault).append("isEnabled", isEnabled).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(governorateID).append(isDefault).append(countryID).append(governorateName).append(isEnabled).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Governorate) == false) {
            return false;
        }
        Governorate rhs = ((Governorate) other);
        return new EqualsBuilder().append(governorateID, rhs.governorateID).append(isDefault, rhs.isDefault).append(countryID, rhs.countryID).append(governorateName, rhs.governorateName).append(isEnabled, rhs.isEnabled).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(governorateID);
        dest.writeValue(countryID);
        dest.writeValue(governorateName);
        dest.writeValue(isDefault);
        dest.writeValue(isEnabled);
    }

    public int describeContents() {
        return 0;
    }
}