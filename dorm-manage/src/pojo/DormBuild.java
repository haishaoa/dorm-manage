package pojo;

/**
 * @author haishao
 * @create 2020-05-21 22:08
 * @discript :
 */
public class DormBuild {
    private Double dormBuildId;
    private String dormBuildName;
    private String detail;


    public DormBuild() {
    }

    public DormBuild(String dormBuildName, String detail) {
        this.dormBuildName = dormBuildName;
        this.detail = detail;
    }

    public DormBuild(Double dormBuildId, String dormBuildName, String detail) {
        this.dormBuildId = dormBuildId;
        this.dormBuildName = dormBuildName;
        this.detail = detail;
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
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "DormBuild{" +
                "dormBuildId=" + dormBuildId +
                ", dormBuildName='" + dormBuildName + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}

