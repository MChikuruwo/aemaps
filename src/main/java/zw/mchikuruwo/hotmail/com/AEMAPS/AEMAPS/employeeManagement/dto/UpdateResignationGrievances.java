package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

public class UpdateResignationGrievances {
    private Long id;
    private String reasonS;
    private Boolean hasBeenApproved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReasonS() {
        return reasonS;
    }

    public void setReasonS(String reasonS) {
        this.reasonS = reasonS;
    }

    public Boolean getHasBeenApproved() {
        return hasBeenApproved;
    }

    public void setHasBeenApproved(Boolean hasBeenApproved) {
        this.hasBeenApproved = hasBeenApproved;
    }
}
