package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums;

public enum ActivityStatus {
    PENDING("PENDING"),
    ACTIVE("ACTIVE"),
    COMPLETED("COMPLETED");

    public final String label;

    private ActivityStatus(String label){
        this.label = label;
    }

}


