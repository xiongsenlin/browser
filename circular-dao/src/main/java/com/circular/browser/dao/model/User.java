package com.circular.browser.dao.model;

import com.circular.browser.dao.base.BaseObject;
import java.util.Date;

public class User extends BaseObject {
    private Integer userId;

    private String loginId;

    private String userPhoneNumber;

    private String userNickname;

    private Date userBirthday;

    private Integer userGender;

    private String userTinyImg;

    private Integer userStatus;

    private Integer userLevel;

    private String userAddress;

    private String userLogo;

    private String userEmail;

    private Date createTime;

    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? null : loginId.trim();
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber == null ? null : userPhoneNumber.trim();
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Integer getUserGender() {
        return userGender;
    }

    public void setUserGender(Integer userGender) {
        this.userGender = userGender;
    }

    public String getUserTinyImg() {
        return userTinyImg;
    }

    public void setUserTinyImg(String userTinyImg) {
        this.userTinyImg = userTinyImg == null ? null : userTinyImg.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo == null ? null : userLogo.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getLoginId() == null ? other.getLoginId() == null : this.getLoginId().equals(other.getLoginId()))
            && (this.getUserPhoneNumber() == null ? other.getUserPhoneNumber() == null : this.getUserPhoneNumber().equals(other.getUserPhoneNumber()))
            && (this.getUserNickname() == null ? other.getUserNickname() == null : this.getUserNickname().equals(other.getUserNickname()))
            && (this.getUserBirthday() == null ? other.getUserBirthday() == null : this.getUserBirthday().equals(other.getUserBirthday()))
            && (this.getUserGender() == null ? other.getUserGender() == null : this.getUserGender().equals(other.getUserGender()))
            && (this.getUserTinyImg() == null ? other.getUserTinyImg() == null : this.getUserTinyImg().equals(other.getUserTinyImg()))
            && (this.getUserStatus() == null ? other.getUserStatus() == null : this.getUserStatus().equals(other.getUserStatus()))
            && (this.getUserLevel() == null ? other.getUserLevel() == null : this.getUserLevel().equals(other.getUserLevel()))
            && (this.getUserAddress() == null ? other.getUserAddress() == null : this.getUserAddress().equals(other.getUserAddress()))
            && (this.getUserLogo() == null ? other.getUserLogo() == null : this.getUserLogo().equals(other.getUserLogo()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getLoginId() == null) ? 0 : getLoginId().hashCode());
        result = prime * result + ((getUserPhoneNumber() == null) ? 0 : getUserPhoneNumber().hashCode());
        result = prime * result + ((getUserNickname() == null) ? 0 : getUserNickname().hashCode());
        result = prime * result + ((getUserBirthday() == null) ? 0 : getUserBirthday().hashCode());
        result = prime * result + ((getUserGender() == null) ? 0 : getUserGender().hashCode());
        result = prime * result + ((getUserTinyImg() == null) ? 0 : getUserTinyImg().hashCode());
        result = prime * result + ((getUserStatus() == null) ? 0 : getUserStatus().hashCode());
        result = prime * result + ((getUserLevel() == null) ? 0 : getUserLevel().hashCode());
        result = prime * result + ((getUserAddress() == null) ? 0 : getUserAddress().hashCode());
        result = prime * result + ((getUserLogo() == null) ? 0 : getUserLogo().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", loginId=").append(loginId);
        sb.append(", userPhoneNumber=").append(userPhoneNumber);
        sb.append(", userNickname=").append(userNickname);
        sb.append(", userBirthday=").append(userBirthday);
        sb.append(", userGender=").append(userGender);
        sb.append(", userTinyImg=").append(userTinyImg);
        sb.append(", userStatus=").append(userStatus);
        sb.append(", userLevel=").append(userLevel);
        sb.append(", userAddress=").append(userAddress);
        sb.append(", userLogo=").append(userLogo);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}