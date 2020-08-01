package pojo;

/**
 * @author haishao
 * @create 2020-05-22 15:41
 * @discript :
 */
public class DormManager {
    private String dormManagerId;
    private String userName;
    private String password;
    private double dormBuildId;
    private String dormBuildName;
    private String name;
    private String sex;
    private String tel;

    public String getDormManagerId() {
        return dormManagerId;
    }

    public void setDormManagerId(String dormManagerId) {
        this.dormManagerId = dormManagerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(double dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormBuildName() {
        return dormBuildName;
    }

    public void setDormBuildName(String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "DormManager{" +
                "dormManagerId='" + dormManagerId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", dormBuildName='" + dormBuildName + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
