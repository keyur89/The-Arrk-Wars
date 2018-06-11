package project.aark.ServiceCall.SWDetails;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public class SWDetailsFailureEvent {
    boolean successful;
    String errorMessageDisplay;

    public SWDetailsFailureEvent(boolean successful, String errorMessageDisplay) {
        this.successful = successful;
        this.errorMessageDisplay = errorMessageDisplay;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getErrorMessageDisplay() {
        return errorMessageDisplay;
    }
}
