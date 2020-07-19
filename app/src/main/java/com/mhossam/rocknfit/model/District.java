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
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

@Entity
public class District implements Serializable, Parcelable
{
    @SerializedName("DistrictID")
    @Expose
    @PrimaryKey
    @NotNull
    private String districtID;
    @SerializedName("GovernorateID")
    @Expose
    private String governorateID;
    @SerializedName("DistrictName")
    @Expose
    private String districtName;
    @SerializedName("IsDefault")
    @Expose
    private String isDefault;
    @SerializedName("IsEnabled")
    @Expose
    private String isEnabled;
    public final static Parcelable.Creator<District> CREATOR = new Creator<District>() {


        @SuppressWarnings({
                "unchecked"
        })
        public District createFromParcel(Parcel in) {
            return new District(in);
        }

        public District[] newArray(int size) {
            return (new District[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6675047979059336830L;

    protected District(Parcel in) {
        this.districtID = ((String) in.readValue((String.class.getClassLoader())));
        this.governorateID = ((String) in.readValue((String.class.getClassLoader())));
        this.districtName = ((String) in.readValue((String.class.getClassLoader())));
        this.isDefault = ((String) in.readValue((String.class.getClassLoader())));
        this.isEnabled = ((String) in.readValue((String.class.getClassLoader())));
    }

    public District() {
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public String getGovernorateID() {
        return governorateID;
    }

    public void setGovernorateID(String governorateID) {
        this.governorateID = governorateID;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
        return districtName;
                //new ToStringBuilder(this).append("districtID", districtID).append("governorateID", governorateID).append("districtName", districtName).append("isDefault", isDefault).append("isEnabled", isEnabled).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(governorateID).append(isDefault).append(districtID).append(districtName).append(isEnabled).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof District) == false) {
            return false;
        }
        District rhs = ((District) other);
        return new EqualsBuilder().append(governorateID, rhs.governorateID).append(isDefault, rhs.isDefault).append(districtID, rhs.districtID).append(districtName, rhs.districtName).append(isEnabled, rhs.isEnabled).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(districtID);
        dest.writeValue(governorateID);
        dest.writeValue(districtName);
        dest.writeValue(isDefault);
        dest.writeValue(isEnabled);
    }

    public int describeContents() {
        return 0;
    }

}