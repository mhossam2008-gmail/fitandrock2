package com.mhossam.rocknfit.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PostComment implements Serializable, Parcelable
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
    @SerializedName("IsLocked")
    @Expose
    private String isLocked;
    @SerializedName("IsEnabled")
    @Expose
    private String isEnabled;
    @SerializedName("PostType")
    @Expose
    private String postType;
    @SerializedName("PostContent")
    @Expose
    private String postContent;
    @SerializedName("PostMedia")
    @Expose
    private String postMedia;
    @SerializedName("PostOwner")
    @Expose
    private String postOwner;
    @SerializedName("CommentID")
    @Expose
    private String commentID;
    @SerializedName("PostID")
    @Expose
    private String postID;
    @SerializedName("CommentType")
    @Expose
    private String commentType;
    @SerializedName("CommentContent")
    @Expose
    private String commentContent;
    @SerializedName("LikesCounter")
    @Expose
    private String likesCounter;
    @SerializedName("CreationDate")
    @Expose
    private String creationDate;
    @SerializedName("LikeID")
    @Expose
    private String likeID;
    @SerializedName("LikeDate")
    @Expose
    private Object likeDate;
    public final static Parcelable.Creator<PostComment> CREATOR = new Creator<PostComment>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PostComment createFromParcel(Parcel in) {
            return new PostComment(in);
        }

        public PostComment[] newArray(int size) {
            return (new PostComment[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6628810305536311342L;

    protected PostComment(Parcel in) {
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
        this.accountType = ((String) in.readValue((String.class.getClassLoader())));
        this.lastLoginDate = ((String) in.readValue((String.class.getClassLoader())));
        this.lastLoginIP = ((String) in.readValue((String.class.getClassLoader())));
        this.loginFailedTrials = ((String) in.readValue((String.class.getClassLoader())));
        this.lastPasswordChange = ((String) in.readValue((String.class.getClassLoader())));
        this.accountImage = ((String) in.readValue((String.class.getClassLoader())));
        this.accountContainer = ((String) in.readValue((String.class.getClassLoader())));
        this.isLocked = ((String) in.readValue((String.class.getClassLoader())));
        this.isEnabled = ((String) in.readValue((String.class.getClassLoader())));
        this.postType = ((String) in.readValue((String.class.getClassLoader())));
        this.postContent = ((String) in.readValue((String.class.getClassLoader())));
        this.postMedia = ((String) in.readValue((String.class.getClassLoader())));
        this.postOwner = ((String) in.readValue((String.class.getClassLoader())));
        this.commentID = ((String) in.readValue((String.class.getClassLoader())));
        this.postID = ((String) in.readValue((String.class.getClassLoader())));
        this.commentType = ((String) in.readValue((String.class.getClassLoader())));
        this.commentContent = ((String) in.readValue((String.class.getClassLoader())));
        this.likesCounter = ((String) in.readValue((String.class.getClassLoader())));
        this.creationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.likeID = ((String) in.readValue((String.class.getClassLoader())));
        this.likeDate = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public PostComment() {
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public PostComment withAccountID(String accountID) {
        this.accountID = accountID;
        return this;
    }

    public String getAccountFirstName() {
        return accountFirstName;
    }

    public void setAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
    }

    public PostComment withAccountFirstName(String accountFirstName) {
        this.accountFirstName = accountFirstName;
        return this;
    }

    public String getAccountLastName() {
        return accountLastName;
    }

    public void setAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
    }

    public PostComment withAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
        return this;
    }

    public String getAccountFullName() {
        return accountFullName;
    }

    public void setAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
    }

    public PostComment withAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
        return this;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public PostComment withAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
        return this;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public PostComment withAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
        return this;
    }

    public String getAccountGender() {
        return accountGender;
    }

    public void setAccountGender(String accountGender) {
        this.accountGender = accountGender;
    }

    public PostComment withAccountGender(String accountGender) {
        this.accountGender = accountGender;
        return this;
    }

    public String getAccountDOB() {
        return accountDOB;
    }

    public void setAccountDOB(String accountDOB) {
        this.accountDOB = accountDOB;
    }

    public PostComment withAccountDOB(String accountDOB) {
        this.accountDOB = accountDOB;
        return this;
    }

    public String getAccountURL() {
        return accountURL;
    }

    public void setAccountURL(String accountURL) {
        this.accountURL = accountURL;
    }

    public PostComment withAccountURL(String accountURL) {
        this.accountURL = accountURL;
        return this;
    }

    public String getAccountToken() {
        return accountToken;
    }

    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    public PostComment withAccountToken(String accountToken) {
        this.accountToken = accountToken;
        return this;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public PostComment withAccountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public PostComment withLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public PostComment withLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
        return this;
    }

    public String getLoginFailedTrials() {
        return loginFailedTrials;
    }

    public void setLoginFailedTrials(String loginFailedTrials) {
        this.loginFailedTrials = loginFailedTrials;
    }

    public PostComment withLoginFailedTrials(String loginFailedTrials) {
        this.loginFailedTrials = loginFailedTrials;
        return this;
    }

    public String getLastPasswordChange() {
        return lastPasswordChange;
    }

    public void setLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
    }

    public PostComment withLastPasswordChange(String lastPasswordChange) {
        this.lastPasswordChange = lastPasswordChange;
        return this;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }

    public PostComment withAccountImage(String accountImage) {
        this.accountImage = accountImage;
        return this;
    }

    public String getAccountContainer() {
        return accountContainer;
    }

    public void setAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
    }

    public PostComment withAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
        return this;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public PostComment withIsLocked(String isLocked) {
        this.isLocked = isLocked;
        return this;
    }

    public String getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
    }

    public PostComment withIsEnabled(String isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public PostComment withPostType(String postType) {
        this.postType = postType;
        return this;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public PostComment withPostContent(String postContent) {
        this.postContent = postContent;
        return this;
    }

    public String getPostMedia() {
        return postMedia;
    }

    public void setPostMedia(String postMedia) {
        this.postMedia = postMedia;
    }

    public PostComment withPostMedia(String postMedia) {
        this.postMedia = postMedia;
        return this;
    }

    public String getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(String postOwner) {
        this.postOwner = postOwner;
    }

    public PostComment withPostOwner(String postOwner) {
        this.postOwner = postOwner;
        return this;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public PostComment withCommentID(String commentID) {
        this.commentID = commentID;
        return this;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public PostComment withPostID(String postID) {
        this.postID = postID;
        return this;
    }

    public String getCommentType() {
        return commentType;
    }

    public void setCommentType(String commentType) {
        this.commentType = commentType;
    }

    public PostComment withCommentType(String commentType) {
        this.commentType = commentType;
        return this;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public PostComment withCommentContent(String commentContent) {
        this.commentContent = commentContent;
        return this;
    }

    public String getLikesCounter() {
        return likesCounter;
    }

    public void setLikesCounter(String likesCounter) {
        this.likesCounter = likesCounter;
    }

    public PostComment withLikesCounter(String likesCounter) {
        this.likesCounter = likesCounter;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public PostComment withCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getLikeID() {
        return likeID;
    }

    public void setLikeID(String likeID) {
        this.likeID = likeID;
    }

    public PostComment withLikeID(String likeID) {
        this.likeID = likeID;
        return this;
    }

    public Object getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Object likeDate) {
        this.likeDate = likeDate;
    }

    public PostComment withLikeDate(Object likeDate) {
        this.likeDate = likeDate;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("accountID", accountID).append("accountFirstName", accountFirstName).append("accountLastName", accountLastName).append("accountFullName", accountFullName).append("accountEmail", accountEmail).append("accountPassword", accountPassword).append("accountGender", accountGender).append("accountDOB", accountDOB).append("accountURL", accountURL).append("accountToken", accountToken).append("accountType", accountType).append("lastLoginDate", lastLoginDate).append("lastLoginIP", lastLoginIP).append("loginFailedTrials", loginFailedTrials).append("lastPasswordChange", lastPasswordChange).append("accountImage", accountImage).append("accountContainer", accountContainer).append("isLocked", isLocked).append("isEnabled", isEnabled).append("postType", postType).append("postContent", postContent).append("postMedia", postMedia).append("postOwner", postOwner).append("commentID", commentID).append("postID", postID).append("commentType", commentType).append("commentContent", commentContent).append("likesCounter", likesCounter).append("creationDate", creationDate).append("likeID", likeID).append("likeDate", likeDate).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(loginFailedTrials).append(accountLastName).append(likesCounter).append(lastLoginDate).append(postID).append(accountID).append(likeDate).append(accountDOB).append(isLocked).append(commentType).append(accountFullName).append(commentID).append(accountURL).append(accountFirstName).append(postType).append(postContent).append(accountType).append(accountGender).append(commentContent).append(creationDate).append(lastLoginIP).append(accountContainer).append(postOwner).append(accountEmail).append(isEnabled).append(likeID).append(accountImage).append(postMedia).append(lastPasswordChange).append(accountToken).append(accountPassword).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PostComment) == false) {
            return false;
        }
        PostComment rhs = ((PostComment) other);
        return new EqualsBuilder().append(loginFailedTrials, rhs.loginFailedTrials).append(accountLastName, rhs.accountLastName).append(likesCounter, rhs.likesCounter).append(lastLoginDate, rhs.lastLoginDate).append(postID, rhs.postID).append(accountID, rhs.accountID).append(likeDate, rhs.likeDate).append(accountDOB, rhs.accountDOB).append(isLocked, rhs.isLocked).append(commentType, rhs.commentType).append(accountFullName, rhs.accountFullName).append(commentID, rhs.commentID).append(accountURL, rhs.accountURL).append(accountFirstName, rhs.accountFirstName).append(postType, rhs.postType).append(postContent, rhs.postContent).append(accountType, rhs.accountType).append(accountGender, rhs.accountGender).append(commentContent, rhs.commentContent).append(creationDate, rhs.creationDate).append(lastLoginIP, rhs.lastLoginIP).append(accountContainer, rhs.accountContainer).append(postOwner, rhs.postOwner).append(accountEmail, rhs.accountEmail).append(isEnabled, rhs.isEnabled).append(likeID, rhs.likeID).append(accountImage, rhs.accountImage).append(postMedia, rhs.postMedia).append(lastPasswordChange, rhs.lastPasswordChange).append(accountToken, rhs.accountToken).append(accountPassword, rhs.accountPassword).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
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
        dest.writeValue(accountType);
        dest.writeValue(lastLoginDate);
        dest.writeValue(lastLoginIP);
        dest.writeValue(loginFailedTrials);
        dest.writeValue(lastPasswordChange);
        dest.writeValue(accountImage);
        dest.writeValue(accountContainer);
        dest.writeValue(isLocked);
        dest.writeValue(isEnabled);
        dest.writeValue(postType);
        dest.writeValue(postContent);
        dest.writeValue(postMedia);
        dest.writeValue(postOwner);
        dest.writeValue(commentID);
        dest.writeValue(postID);
        dest.writeValue(commentType);
        dest.writeValue(commentContent);
        dest.writeValue(likesCounter);
        dest.writeValue(creationDate);
        dest.writeValue(likeID);
        dest.writeValue(likeDate);
    }

    public int describeContents() {
        return 0;
    }

}
