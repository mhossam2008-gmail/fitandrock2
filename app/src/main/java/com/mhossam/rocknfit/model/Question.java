package com.mhossam.rocknfit.model;


import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Question implements Serializable, Parcelable
{

    @SerializedName("QuestionID")
    @Expose
    private String questionID;
    @SerializedName("QuestionText")
    @Expose
    private String questionText;
    @SerializedName("QuestionStatus")
    @Expose
    private String questionStatus;
    @SerializedName("QuestionDate")
    @Expose
    private String questionDate;
    @SerializedName("AnswersCount")
    @Expose
    private String answersCount;
    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("AccountFirstName")
    @Expose
    private String accountFirstName;
    @SerializedName("AccountLastName")
    @Expose
    private String accountLastName;
    @SerializedName("AccountFullName")
    @Expose
    private String accountFullName;
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
    public final static Parcelable.Creator<Question> CREATOR = new Creator<Question>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        public Question[] newArray(int size) {
            return (new Question[size]);
        }

    }
            ;
    private final static long serialVersionUID = 8611782745137710797L;

    protected Question(Parcel in) {
        this.questionID = ((String) in.readValue((String.class.getClassLoader())));
        this.questionText = ((String) in.readValue((String.class.getClassLoader())));
        this.questionStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.questionDate = ((String) in.readValue((String.class.getClassLoader())));
        this.answersCount = ((String) in.readValue((String.class.getClassLoader())));
        this.accountID = ((String) in.readValue((String.class.getClassLoader())));
        this.accountFirstName = ((String) in.readValue((String.class.getClassLoader())));
        this.accountLastName = ((String) in.readValue((String.class.getClassLoader())));
        this.accountFullName = ((String) in.readValue((String.class.getClassLoader())));
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
        this.accountCover = ((String) in.readValue((String.class.getClassLoader())));
        this.accountPoints = ((String) in.readValue((String.class.getClassLoader())));
        this.creationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.isLocked = ((String) in.readValue((String.class.getClassLoader())));
        this.isEnabled = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Question() {
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public Question withQuestionID(String questionID) {
        this.questionID = questionID;
        return this;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Question withQuestionText(String questionText) {
        this.questionText = questionText;
        return this;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Question withQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
        return this;
    }

    public String getQuestionDate() {
        return questionDate;
    }

    public void setQuestionDate(String questionDate) {
        this.questionDate = questionDate;
    }

    public Question withQuestionDate(String questionDate) {
        this.questionDate = questionDate;
        return this;
    }

    public String getAnswersCount() {
        return answersCount;
    }

    public void setAnswersCount(String answersCount) {
        this.answersCount = answersCount;
    }

    public Question withAnswersCount(String answersCount) {
        this.answersCount = answersCount;
        return this;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Question withAccountID(String accountID) {
        this.accountID = accountID;
        return this;
    }

    public String getAccountFirstName() {
        return accountFirstName;
    }

    public void setAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
    }

    public Question withAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
        return this;
    }

    public String getAccountLastName() {
        return accountLastName;
    }

    public void setAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
    }

    public Question withAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
        return this;
    }

    public String getAccountFullName() {
        return accountFullName;
    }

    public void setAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
    }

    public Question withAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
        return this;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public Question withAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
        return this;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Question withAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
        return this;
    }

    public String getAccountGender() {
        return accountGender;
    }

    public void setAccountGender(String accountGender) {
        this.accountGender = accountGender;
    }

    public Question withAccountGender(String accountGender) {
        this.accountGender = accountGender;
        return this;
    }

    public String getAccountDOB() {
        return accountDOB;
    }

    public void setAccountDOB(String accountDOB) {
        this.accountDOB = accountDOB;
    }

    public Question withAccountDOB(String accountDOB) {
        this.accountDOB = accountDOB;
        return this;
    }

    public String getAccountURL() {
        return accountURL;
    }

    public void setAccountURL(String accountURL) {
        this.accountURL = accountURL;
    }

    public Question withAccountURL(String accountURL) {
        this.accountURL = accountURL;
        return this;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public Question withAccountToken(String accountToken) {
        this.accountToken = accountToken;
        return this;
    }

    public String getAccountTypeShort() {
        return accountTypeShort;
    }

    public void setAccountTypeShort(String accountTypeShort) {
        this.accountTypeShort = accountTypeShort;
    }

    public Question withAccountTypeShort(String accountTypeShort) {
        this.accountTypeShort = accountTypeShort;
        return this;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Question withAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Question withLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public Question withLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
        return this;
    }

    public String getLoginFailedTrials() {
        return loginFailedTrials;
    }

    public void setLoginFailedTrials(String loginFailedTrials) {
        this.loginFailedTrials = loginFailedTrials;
    }

    public Question withLoginFailedTrials(String loginFailedTrials) {
        this.loginFailedTrials = loginFailedTrials;
        return this;
    }

    public String getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public Question withLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
        return this;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }

    public Question withAccountImage(String accountImage) {
        this.accountImage = accountImage;
        return this;
    }

    public String getAccountContainer() {
        return accountContainer;
    }

    public void setAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
    }

    public Question withAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
        return this;
    }

    public String getAccountCover() {
        return accountCover;
    }

    public void setAccountCover(String accountCover) {
        this.accountCover = accountCover;
    }

    public Question withAccountCover(String accountCover) {
        this.accountCover = accountCover;
        return this;
    }

    public String getAccountPoints() {
        return accountPoints;
    }

    public void setAccountPoints(String accountPoints) {
        this.accountPoints = accountPoints;
    }

    public Question withAccountPoints(String accountPoints) {
        this.accountPoints = accountPoints;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Question withCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public Question withIsLocked(String isLocked) {
        this.isLocked = isLocked;
        return this;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Question withIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("questionID", questionID).append("questionText", questionText).append("questionStatus", questionStatus).append("questionDate", questionDate).append("answersCount", answersCount).append("accountID", accountID).append("accountFirstName", accountFirstName).append("accountLastName", accountLastName).append("accountFullName", accountFullName).append("accountEmail", accountEmail).append("accountPassword", accountPassword).append("accountGender", accountGender).append("accountDOB", accountDOB).append("accountURL", accountURL).append("accountToken", accountToken).append("accountTypeShort", accountTypeShort).append("accountType", accountType).append("lastLoginDate", lastLoginDate).append("lastLoginIP", lastLoginIP).append("loginFailedTrials", loginFailedTrials).append("lastPasswordChange", lastPasswordChange).append("accountImage", accountImage).append("accountContainer", accountContainer).append("accountCover", accountCover).append("accountPoints", accountPoints).append("creationDate", creationDate).append("isLocked", isLocked).append("isEnabled", isEnabled).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(loginFailedTrials).append(questionDate).append(accountLastName).append(lastLoginDate).append(accountCover).append(questionStatus).append(questionText).append(accountID).append(accountDOB).append(isLocked).append(accountFullName).append(accountTypeShort).append(accountURL).append(questionID).append(accountFirstName).append(accountType).append(accountGender).append(creationDate).append(lastLoginIP).append(accountContainer).append(accountEmail).append(isEnabled).append(answersCount).append(accountImage).append(lastPasswordChange).append(accountToken).append(accountPassword).append(accountPoints).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Question) == false) {
            return false;
        }
        Question rhs = ((Question) other);
        return new EqualsBuilder().append(loginFailedTrials, rhs.loginFailedTrials).append(questionDate, rhs.questionDate).append(accountLastName, rhs.accountLastName).append(lastLoginDate, rhs.lastLoginDate).append(accountCover, rhs.accountCover).append(questionStatus, rhs.questionStatus).append(questionText, rhs.questionText).append(accountID, rhs.accountID).append(accountDOB, rhs.accountDOB).append(isLocked, rhs.isLocked).append(accountFullName, rhs.accountFullName).append(accountTypeShort, rhs.accountTypeShort).append(accountURL, rhs.accountURL).append(questionID, rhs.questionID).append(accountFirstName, rhs.accountFirstName).append(accountType, rhs.accountType).append(accountGender, rhs.accountGender).append(creationDate, rhs.creationDate).append(lastLoginIP, rhs.lastLoginIP).append(accountContainer, rhs.accountContainer).append(accountEmail, rhs.accountEmail).append(isEnabled, rhs.isEnabled).append(answersCount, rhs.answersCount).append(accountImage, rhs.accountImage).append(lastPasswordChange, rhs.lastPasswordChange).append(accountToken, rhs.accountToken).append(accountPassword, rhs.accountPassword).append(accountPoints, rhs.accountPoints).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(questionID);
        dest.writeValue(questionText);
        dest.writeValue(questionStatus);
        dest.writeValue(questionDate);
        dest.writeValue(answersCount);
        dest.writeValue(accountID);
        dest.writeValue(accountFirstName);
        dest.writeValue(accountLastName);
        dest.writeValue(accountFullName);
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
        dest.writeValue(accountCover);
        dest.writeValue(accountPoints);
        dest.writeValue(creationDate);
        dest.writeValue(isLocked);
        dest.writeValue(isEnabled);
    }

    public int describeContents() {
        return 0;
    }

}