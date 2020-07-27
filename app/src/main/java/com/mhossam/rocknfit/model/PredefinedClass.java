package com.mhossam.rocknfit.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.jetbrains.annotations.NotNull;

@Entity
public class PredefinedClass implements Serializable {
    @PrimaryKey
    @NotNull
    @SerializedName("ClassID")
    @Expose
    private String classID;
    @SerializedName("ClassName")
    @Expose
    private String className;
    private final static long serialVersionUID = 2140216139808581288L;

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        //return new ToStringBuilder(this).append("classID", classID).append("className", className).toString();
        return className;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(classID).append(className).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PredefinedClass) == false) {
            return false;
        }
        PredefinedClass rhs = ((PredefinedClass) other);
        return new EqualsBuilder().append(classID, rhs.classID).append(className, rhs.className).isEquals();
    }

}