package pojo;

/**
 * @author haishao
 * @create 2020-05-21 22:22
 * @discript :
 */
public class Student {
    private String studentId;
    private String stuNumber;
    private String userName;
    private String password;
    private Double dormBuildId = 0.0;
    private String dormBuildName;
    private String dormName;
    private String name;
    private String sex;
    private String tel;



    public Student(String studentId, String password, Double dormBuildId, String dormName, String name, String sex, String tel) {
        this.studentId = studentId;
        this.password = password;
        this.dormBuildId = dormBuildId;
        this.dormName = dormName;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public Student() {
    }

    public Student(String studentId, String stuNumber, String userName, String password, Double dormBuildId, String dormBuildName, String dormName, String name, String sex, String tel) {
        this.studentId = studentId;
        this.stuNumber = stuNumber;
        this.userName = userName;
        this.password = password;
        this.dormBuildId = dormBuildId;
        this.dormBuildName = dormBuildName;
        this.dormName = dormName;
        this.name = name;
        this.sex = sex;
        this.tel = tel;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStuNumber() {
        return stuNumber;
    }

    public void setStuNumber(String stuNumber) {
        this.stuNumber = stuNumber;
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

    public Double getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(Double dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getDormBuildName() {
        return dormBuildName;
    }

    public void setDormBuildName(String dormBuildName) {
        this.dormBuildName = dormBuildName;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
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
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", stuNumber='" + stuNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", dormBuildId=" + dormBuildId +
                ", dormBuildName='" + dormBuildName + '\'' +
                ", dormName='" + dormName + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}
