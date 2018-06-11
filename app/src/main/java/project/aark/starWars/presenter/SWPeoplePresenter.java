package project.aark.starWars.presenter;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import project.aark.ApiManager.ApiManager;
import project.aark.ServiceCall.SWDetails.CallSWDetails;
import project.aark.starWars.contract.SWPeopleDetailProvider;
import project.aark.starWars.contract.SWPeopleProvider;
import project.aark.starWars.model.SWCharacterModel;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public class SWPeoplePresenter {

    @Inject
    public SWPeoplePresenter() {

    }

    public void callSWPeopleDetails(ApiManager apiManager, int pageNumber) {
        CallSWDetails sampleDataDetails = new CallSWDetails();
        sampleDataDetails.callProductDetails(apiManager, pageNumber);
    }

    public void setNoData(SWPeopleProvider provider) {
        provider.setNoData();
    }

    public void fillCharacterList(SWPeopleProvider provider, List<SWCharacterModel> characterModels) {
        if (characterModels.size() > 0) {
            provider.setAdapterData(characterModels);
        } else {
            provider.setNoData();
        }
    }

    public void setCharacterDetail(SWPeopleDetailProvider provider, SWCharacterModel swCharacterModel) {
        if (swCharacterModel != null) {
            provider.setCharacterName(swCharacterModel.getName() != null ? swCharacterModel.getName() : "N/A");
            provider.setCharacterMass(swCharacterModel.getMass() != null ? swCharacterModel.getMass() : "N/A");
            provider.setCharacterHeight(swCharacterModel.getHeight() != null ? swCharacterModel.getHeight() : "N/A");

            Date date = null;
            DateFormat localFormat = null;
            try {
                if (swCharacterModel.getCreated() != null) {
                    DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

                    date = utcFormat.parse(swCharacterModel.getCreated());

                    localFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a", Locale.getDefault());
                    provider.setCharacterCreatedDate(localFormat.format(date));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            provider.setNoData();
        }
    }
}
