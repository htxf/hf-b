package htxf.htb.Domain;


import javax.persistence.*;
import java.util.List;
/**
 *  User Domain
 *  htxf 20200302
 * */
@Entity
@Table(name = "user")
public class UserDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String nickName;
    private String avatar;
    private String phoneNumber;
    private String password;
    private String registrationTime;
    private String lastLoginTime;
    private String lastLoginLocation;
    @ElementCollection
    private List<String> historicalLoginLocationList;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        userId = userId;
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
