package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

import java.math.BigInteger;

public class AddSalaryAdvanceApplicationDto {
    private BigInteger amountRequired;
    private Boolean hasBeenApproved;
    private String accountNumber;
    private String purpose;

    public BigInteger getAmountRequired() {
        return amountRequired;
    }

    public void setAmountRequired(BigInteger amountRequired) {
        this.amountRequired = amountRequired;
    }

    public Boolean getHasBeenApproved() {
        return hasBeenApproved;
    }

    public void setHasBeenApproved(Boolean hasBeenApproved) {
        this.hasBeenApproved = hasBeenApproved;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
