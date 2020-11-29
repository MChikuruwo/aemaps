package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.employeeManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class AddLoanApplicationDto {

    private BigDecimal amountRequired;
    private String repaymentPeriod;
    private String loanPurpose;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date contractExpiryDate;

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

    public Date getContractExpiryDate() {
        return contractExpiryDate;
    }

    public void setContractExpiryDate(Date contractExpiryDate) {
        this.contractExpiryDate = contractExpiryDate;
    }
}
