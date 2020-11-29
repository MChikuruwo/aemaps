package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

import java.sql.Date;

public class AddStaffTransferDto {
    private Date proposedStartDate;
    private String reason;

    public Date getProposedStartDate() {
        return proposedStartDate;
    }

    public void setProposedStartDate(Date proposedStartDate) {
        this.proposedStartDate = proposedStartDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
