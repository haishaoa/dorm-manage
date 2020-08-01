package pojo;

/**
 * @author haishao
 * @create 2020-05-21 22:21
 * @discript :
 */
public class Record {

    private String recordId;
    private String studentNumber;
    private String studentName;
    private String date;
    private String detail;
    private double dormBuildId;
    private String dormBuildName;
    private String dormName;
    private String startDate;
    private String endDate;

    public Record() {
    }

    public Record(String studentNumber, String date, String detail) {
        this.studentNumber = studentNumber;
        this.date = date;
        this.detail = detail;
    }

    public Record(String recordId, String studentNumber, String studentName, String date, String detail, double dormBuildId, String dormBuildName, String dormName) {
        this.recordId = recordId;
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.date = date;
        this.detail = detail;
        this.dormBuildId = dormBuildId;
        this.dormBuildName = dormBuildName;
        this.dormName = dormName;
    }

    public String getRecordId() {
        return recordId;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    public String getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDetail() {
        return detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
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

    public double getDormBuildId() {
        return dormBuildId;
    }

    public void setDormBuildId(double dormBuildId) {
        this.dormBuildId = dormBuildId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }



}
