package project.aark.starWars.contract;

import java.util.List;

import project.aark.starWars.model.SWCharacterModel;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public interface SWPeopleProvider {
    void showError(String message);
    void setNoData();

    void setAdapterData(List<SWCharacterModel> characterModels);
}
