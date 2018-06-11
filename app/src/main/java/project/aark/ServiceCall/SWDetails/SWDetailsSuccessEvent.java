package project.aark.ServiceCall.SWDetails;

import project.aark.starWars.model.SWPeopleModel;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public class SWDetailsSuccessEvent {
    boolean successful;
    SWPeopleModel swPeopleModel;

    public SWDetailsSuccessEvent(boolean successful, SWPeopleModel swPeopleModel) {
        this.successful = successful;
        this.swPeopleModel = swPeopleModel;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public SWPeopleModel getSwPeopleModel() {
        return swPeopleModel;
    }
}
