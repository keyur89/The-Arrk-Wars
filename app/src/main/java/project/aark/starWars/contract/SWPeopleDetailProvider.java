package project.aark.starWars.contract;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public interface SWPeopleDetailProvider {
    void showError(String message);

    void setNoData();

    void setCharacterName(String name);

    void setCharacterMass(String mass);

    void setCharacterHeight(String height);

    void setCharacterCreatedDate(String created);
}
