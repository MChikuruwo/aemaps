package zw.mchikuruwo.hotmail.com.AEMAPS.AEMAPS.timeTracker.enums;

public enum ActivityImportance {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH"),
    EXTREMELY_HIGH("EXTREMELY HIGH");

    private final String simpleName;

    ActivityImportance(String simpleName) {
        this.simpleName = simpleName;
    }
}
