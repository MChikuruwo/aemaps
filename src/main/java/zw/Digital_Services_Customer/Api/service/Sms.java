package zw.Digital_Services_Customer.Api.service;

public class Sms {
    private String message;

    public Sms() {
    }

    public Sms(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
