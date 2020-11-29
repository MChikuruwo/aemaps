package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

public class AddJobTitleDto {
    private String titleName;
    private Integer reportingTo;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Integer getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(Integer reportingTo) {
        this.reportingTo = reportingTo;
    }
}
