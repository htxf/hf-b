package htxf.htb.VO;

import java.util.List;

/**
 *  User VO
 *  htxf 20200302
 * */
public class UserVO {
    private String UserId;
    private String nickName;
    private String avatar;
    private String phoneNumber;
    private String password;
    private String registrationTime;
    private String lastLoginTime;
    private String lastLoginLocation;
    private List<String> historicalLoginLocationList;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginLocation() {
        return lastLoginLocation;
    }

    public void setLastLoginLocation(String lastLoginLocation) {
        this.lastLoginLocation = lastLoginLocation;
    }

    public List<String> getHistoricalLoginLocationList() {
        return historicalLoginLocationList;
    }

    public void setHistoricalLoginLocationList(List<String> historicalLoginLocationList) {
        this.historicalLoginLocationList = historicalLoginLocationList;
    }
}
