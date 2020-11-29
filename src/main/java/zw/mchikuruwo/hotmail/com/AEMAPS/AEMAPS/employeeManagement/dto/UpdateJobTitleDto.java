package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

public class UpdateJobTitleDto {
    private Long id;
    private String titleName;
    private Long reportingTo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Long getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(Long reportingTo) {
        this.reportingTo = reportingTo;
    }
}
