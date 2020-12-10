package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums;

public enum ActivityRequestStatus {
    PENDING("PENDING"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED");

    private final String simpleName;

    ActivityRequestStatus(String simpleName) {
        this.simpleName = simpleName;
    }
}
