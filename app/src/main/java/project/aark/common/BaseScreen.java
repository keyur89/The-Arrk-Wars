package project.aark.common;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public interface BaseScreen {

    boolean checkIfNetworkConnected();

    void showLoading();

    void hideLoading();

    void showError(String message);

}
