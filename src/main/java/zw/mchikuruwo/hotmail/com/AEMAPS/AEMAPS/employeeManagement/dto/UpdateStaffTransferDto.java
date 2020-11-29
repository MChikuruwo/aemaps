package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

import java.sql.Date;

public class UpdateStaffTransferDto {
    private Long id;
    private Date proposedStartDate;
    private String reason;
    private Boolean hasBeenApproved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Boolean getHasBeenApproved() {
        return hasBeenApproved;
    }

    public void setHasBeenApproved(Boolean hasBeenApproved) {
        this.hasBeenApproved = hasBeenApproved;
    }
}
