package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

import java.math.BigDecimal;

public class UpdateLoanApplicationDto {
    private Long id;
    private BigDecimal amountRequired;
    private String repaymentPeriod;
    private String loanPurpose;
    private Boolean hasBeenApproved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(BigDecimal amountRequired) {
        this.amountRequired = amountRequired;
    }

    public String getRepaymentPeriod() {
        return repaymentPeriod;
    }

    public void setRepaymentPeriod(String repaymentPeriod) {
        this.repaymentPeriod = repaymentPeriod;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Boolean getHasBeenApproved() {
        return hasBeenApproved;
    }

    public void setHasBeenApproved(Boolean hasBeenApproved) {
        this.hasBeenApproved = hasBeenApproved;
    }
}
