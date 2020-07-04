package com.mhossam.rocknfit.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Post implements Serializable, Parcelable
{

    @SerializedName("PostID")
    @Expose
    private String postID;
    @SerializedName("AccountID")
    @Expose
    private String accountID;
    @SerializedName("PostType")
    @Expose
    private String postType;
    @SerializedName("PostContent")
    @Expose
    private String postContent;
    @SerializedName("PostMedia")
    @Expose
    private Object postMedia;
    @SerializedName("ShareText")
    @Expose
    private Object shareText;
    @SerializedName("CommentsCounter")
    @Expose
    private String commentsCounter;
    @SerializedName("LikesCounter")
    @Expose
    private Integer likesCounter;
    @SerializedName("ShareCounter")
    @Expose
    private String shareCounter;
    @SerializedName("OriginalPostID")
    @Expose
    private String originalPostID;
    @SerializedName("CreationDate")
    @Expose
    private String creationDate;
    @SerializedName("AccountImage")
    @Expose
    private String accountImage;
    @SerializedName("AccountContainer")
    @Expose
    private String accountContainer;
    @SerializedName("AccountURL")
    @Expose
    private String accountURL;
    @SerializedName("AccountFullName")
    @Expose
    private String accountFullName;
    @SerializedName("LikeID")
    @Expose
    private Object likeID;
    @SerializedName("LikeDate")
    @Expose
    private Object likeDate;
    @SerializedName("FullImagePath")
    @Expose
    private String fullImagePath;
    @SerializedName("ProfilePicturePath")
    @Expose
    private String profilePicturePath;
    @SerializedName("PostComments")
    @Expose
    private Object postComments;
    @SerializedName("PostImageWidth")
    @Expose
    private Integer postImageWidth;
    @SerializedName("PostImageHeight")
    @Expose
    private Integer postImageHeight;
    @SerializedName("OrgName")
    @Expose
    private String orgName;
    @SerializedName("OrgPicturePath")
    @Expose
    private String orgPicturePath;
    public final static Parcelable.Creator<Post> CREATOR = new Creator<Post>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        public Post[] newArray(int size) {
            return (new Post[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8832138057659525171L;

    protected Post(Parcel in) {
        this.postID = ((String) in.readValue((String.class.getClassLoader())));
        this.accountID = ((String) in.readValue((String.class.getClassLoader())));
        this.postType = ((String) in.readValue((String.class.getClassLoader())));
        this.postContent = ((String) in.readValue((String.class.getClassLoader())));
        this.postMedia = ((Object) in.readValue((Object.class.getClassLoader())));
        this.shareText = ((Object) in.readValue((Object.class.getClassLoader())));
        this.commentsCounter = ((String) in.readValue((String.class.getClassLoader())));
        this.likesCounter = ((Integer) in.readValue((String.class.getClassLoader())));
        this.shareCounter = ((String) in.readValue((String.class.getClassLoader())));
        this.originalPostID = ((String) in.readValue((String.class.getClassLoader())));
        this.creationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.accountImage = ((String) in.readValue((String.class.getClassLoader())));
        this.accountContainer = ((String) in.readValue((String.class.getClassLoader())));
        this.accountURL = ((String) in.readValue((String.class.getClassLoader())));
        this.accountFullName = ((String) in.readValue((String.class.getClassLoader())));
        this.likeID = ((Object) in.readValue((Object.class.getClassLoader())));
        this.likeDate = ((Object) in.readValue((Object.class.getClassLoader())));
        this.fullImagePath = ((String) in.readValue((String.class.getClassLoader())));
        this.profilePicturePath = ((String) in.readValue((String.class.getClassLoader())));
        this.postComments = ((Object) in.readValue((Object.class.getClassLoader())));
        this.postImageWidth = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.postImageHeight = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.orgName = ((String) in.readValue((String.class.getClassLoader())));
        this.orgPicturePath = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Post() {
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public Post withPostID(String postID) {
        this.postID = postID;
        return this;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Post withAccountID(String accountID) {
        this.accountID = accountID;
        return this;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Post withPostType(String postType) {
        this.postType = postType;
        return this;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Post withPostContent(String postContent) {
        this.postContent = postContent;
        return this;
    }

    public Object getPostMedia() {
        return postMedia;
    }

    public void setPostMedia(Object postMedia) {
        this.postMedia = postMedia;
    }

    public Post withPostMedia(Object postMedia) {
        this.postMedia = postMedia;
        return this;
    }

    public Object getShareText() {
        return shareText;
    }

    public void setShareText(Object shareText) {
        this.shareText = shareText;
    }

    public Post withShareText(Object shareText) {
        this.shareText = shareText;
        return this;
    }

    public String getCommentsCounter() {
        return commentsCounter;
    }

    public void setCommentsCounter(String commentsCounter) {
        this.commentsCounter = commentsCounter;
    }

    public Post withCommentsCounter(String commentsCounter) {
        this.commentsCounter = commentsCounter;
        return this;
    }

    public Integer getLikesCounter() {
        return likesCounter;
    }

    public void setLikesCounter(Integer likesCounter) {
        this.likesCounter = likesCounter;
    }

    public Post withLikesCounter(Integer likesCounter) {
        this.likesCounter = likesCounter;
        return this;
    }

    public String getShareCounter() {
        return shareCounter;
    }

    public void setShareCounter(String shareCounter) {
        this.shareCounter = shareCounter;
    }

    public Post withShareCounter(String shareCounter) {
        this.shareCounter = shareCounter;
        return this;
    }

    public String getOriginalPostID() {
        return originalPostID;
    }

    public void setOriginalPostID(String originalPostID) {
        this.originalPostID = originalPostID;
    }

    public Post withOriginalPostID(String originalPostID) {
        this.originalPostID = originalPostID;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Post withCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getAccountImage() {
        return accountImage;
    }

    public void setAccountImage(String accountImage) {
        this.accountImage = accountImage;
    }

    public Post withAccountImage(String accountImage) {
        this.accountImage = accountImage;
        return this;
    }

    public String getAccountContainer() {
        return accountContainer;
    }

    public void setAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
    }

    public Post withAccountContainer(String accountContainer) {
        this.accountContainer = accountContainer;
        return this;
    }

    public String getAccountURL() {
        return accountURL;
    }

    public void setAccountURL(String accountURL) {
        this.accountURL = accountURL;
    }

    public Post withAccountURL(String accountURL) {
        this.accountURL = accountURL;
        return this;
    }

    public String getAccountFullName() {
        return accountFullName;
    }

    public void setAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
    }

    public Post withAccountFullName(String accountFullName) {
        this.accountFullName = accountFullName;
        return this;
    }

    public Object getLikeID() {
        return likeID;
    }

    public void setLikeID(Object likeID) {
        this.likeID = likeID;
    }

    public Post withLikeID(Object likeID) {
        this.likeID = likeID;
        return this;
    }

    public Object getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Object likeDate) {
        this.likeDate = likeDate;
    }

    public Post withLikeDate(Object likeDate) {
        this.likeDate = likeDate;
        return this;
    }

    public String getFullImagePath() {
        return fullImagePath;
    }

    public void setFullImagePath(String fullImagePath) {
        this.fullImagePath = fullImagePath;
    }

    public Post withFullImagePath(String fullImagePath) {
        this.fullImagePath = fullImagePath;
        return this;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    public Post withProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
        return this;
    }

    public Object getPostComments() {
        return postComments;
    }

    public void setPostComments(Object postComments) {
        this.postComments = postComments;
    }

    public Post withPostComments(Object postComments) {
        this.postComments = postComments;
        return this;
    }

    public Integer getPostImageWidth() {
        return postImageWidth;
    }

    public void setPostImageWidth(Integer postImageWidth) {
        this.postImageWidth = postImageWidth;
    }

    public Post withPostImageWidth(Integer postImageWidth) {
        this.postImageWidth = postImageWidth;
        return this;
    }

    public Integer getPostImageHeight() {
        return postImageHeight;
    }

    public void setPostImageHeight(Integer postImageHeight) {
        this.postImageHeight = postImageHeight;
    }

    public Post withPostImageHeight(Integer postImageHeight) {
        this.postImageHeight = postImageHeight;
        return this;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Post withOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    public String getOrgPicturePath() {
        return orgPicturePath;
    }

    public void setOrgPicturePath(String orgPicturePath) {
        this.orgPicturePath = orgPicturePath;
    }

    public Post withOrgPicturePath(String orgPicturePath) {
        this.orgPicturePath = orgPicturePath;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("postID", postID).append("accountID", accountID).append("postType", postType).append("postContent", postContent).append("postMedia", postMedia).append("shareText", shareText).append("commentsCounter", commentsCounter).append("likesCounter", likesCounter).append("shareCounter", shareCounter).append("originalPostID", originalPostID).append("creationDate", creationDate).append("accountImage", accountImage).append("accountContainer", accountContainer).append("accountURL", accountURL).append("accountFullName", accountFullName).append("likeID", likeID).append("likeDate", likeDate).append("fullImagePath", fullImagePath).append("profilePicturePath", profilePicturePath).append("postComments", postComments).append("postImageWidth", postImageWidth).append("postImageHeight", postImageHeight).append("orgName", orgName).append("orgPicturePath", orgPicturePath).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(likesCounter).append(postImageWidth).append(postID).append(accountID).append(likeDate).append(shareText).append(originalPostID).append(accountFullName).append(postImageHeight).append(commentsCounter).append(accountURL).append(profilePicturePath).append(postComments).append(orgName).append(postType).append(postContent).append(fullImagePath).append(creationDate).append(accountContainer).append(shareCounter).append(orgPicturePath).append(likeID).append(postMedia).append(accountImage).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Post) == false) {
            return false;
        }
        Post rhs = ((Post) other);
        return new EqualsBuilder().append(likesCounter, rhs.likesCounter).append(postImageWidth, rhs.postImageWidth).append(postID, rhs.postID).append(accountID, rhs.accountID).append(likeDate, rhs.likeDate).append(shareText, rhs.shareText).append(originalPostID, rhs.originalPostID).append(accountFullName, rhs.accountFullName).append(postImageHeight, rhs.postImageHeight).append(commentsCounter, rhs.commentsCounter).append(accountURL, rhs.accountURL).append(profilePicturePath, rhs.profilePicturePath).append(postComments, rhs.postComments).append(orgName, rhs.orgName).append(postType, rhs.postType).append(postContent, rhs.postContent).append(fullImagePath, rhs.fullImagePath).append(creationDate, rhs.creationDate).append(accountContainer, rhs.accountContainer).append(shareCounter, rhs.shareCounter).append(orgPicturePath, rhs.orgPicturePath).append(likeID, rhs.likeID).append(postMedia, rhs.postMedia).append(accountImage, rhs.accountImage).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(postID);
        dest.writeValue(accountID);
        dest.writeValue(postType);
        dest.writeValue(postContent);
        dest.writeValue(postMedia);
        dest.writeValue(shareText);
        dest.writeValue(commentsCounter);
        dest.writeValue(likesCounter);
        dest.writeValue(shareCounter);
        dest.writeValue(originalPostID);
        dest.writeValue(creationDate);
        dest.writeValue(accountImage);
        dest.writeValue(accountContainer);
        dest.writeValue(accountURL);
        dest.writeValue(accountFullName);
        dest.writeValue(likeID);
        dest.writeValue(likeDate);
        dest.writeValue(fullImagePath);
        dest.writeValue(profilePicturePath);
        dest.writeValue(postComments);
        dest.writeValue(postImageWidth);
        dest.writeValue(postImageHeight);
        dest.writeValue(orgName);
        dest.writeValue(orgPicturePath);
    }

    public int describeContents() {
        return 0;
    }

}