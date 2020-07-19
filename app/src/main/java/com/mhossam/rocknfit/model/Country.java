package com.mhossam.rocknfit.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

@Entity
public class Country implements Serializable, Parcelable
{

    @SerializedName("CountryID")
    @Expose
    @PrimaryKey
    @NotNull
    private String countryID;
    @SerializedName("CountryName")
    @Expose
    private String countryName;
    @SerializedName("CountryCode")
    @Expose
    private String countryCode;
    @SerializedName("IsDefault")
    @Expose
    private String isDefault;
    @SerializedName("IsEnabled")
    @Expose
    private String isEnabled;
    public final static Parcelable.Creator<Country> CREATOR = new Creator<Country>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        public Country[] newArray(int size) {
            return (new Country[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1056157318187334330L;

    protected Country(Parcel in) {
        this.countryID = ((String) in.readValue((String.class.getClassLoader())));
        this.countryName = ((String) in.readValue((String.class.getClassLoader())));
        this.countryCode = ((String) in.readValue((String.class.getClassLoader())));
        this.isDefault = ((String) in.readValue((String.class.getClassLoader())));
        this.isEnabled = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Country() {
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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
        return countryName;
        //new ToStringBuilder(this).append("countryID", countryID).append("countryName", countryName).append("countryCode", countryCode).append("isDefault", isDefault).append("isEnabled", isEnabled).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(isDefault).append(countryName).append(countryID).append(countryCode).append(isEnabled).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Country) == false) {
            return false;
        }
        Country rhs = ((Country) other);
        return new EqualsBuilder().append(isDefault, rhs.isDefault).append(countryName, rhs.countryName).append(countryID, rhs.countryID).append(countryCode, rhs.countryCode).append(isEnabled, rhs.isEnabled).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(countryID);
        dest.writeValue(countryName);
        dest.writeValue(countryCode);
        dest.writeValue(isDefault);
        dest.writeValue(isEnabled);
    }

    public int describeContents() {
        return 0;
    }

}