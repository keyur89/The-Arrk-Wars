package project.aark.ServiceCall.SWDetails;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.eventbus.EventBus;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import project.aark.ApiManager.ApiManager;
import project.aark.Utils.Injector;
import project.aark.starWars.model.SWPeopleModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;


/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public class CallSWDetails {
    EventBus bus;

    public void callProductDetails(final ApiManager apiManager, int pageNumber) {
        bus = Injector.provideEventBus();

        Call<SWPeopleModel> swPeopleModelCall = apiManager.getService().getPeopleData(pageNumber);
        swPeopleModelCall.enqueue(new Callback<SWPeopleModel>() {
            @Override
            public void onResponse(Call<SWPeopleModel> call, Response<SWPeopleModel> response) {
                try {
                    if (response.isSuccessful()) {
                        SWPeopleModel swPeopleModel = response.body();
                        bus.post(new SWDetailsSuccessEvent(response.isSuccessful(), swPeopleModel));
                    } else {
                        Converter<ResponseBody, Error> errorConverter =
                                apiManager.retrofit.responseBodyConverter(Error.class, new Annotation[0]);
                        // Convert the error body into our Error type.
                        try {
                            Error error = errorConverter.convert(response.errorBody());
                            String errorMessageDisplay = error.message;
                            bus.post(new SWDetailsFailureEvent(false, errorMessageDisplay));

                        } catch (Exception e) {
                            e.printStackTrace();
                            bus.post(new SWDetailsFailureEvent(false, null));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    bus.post(new SWDetailsFailureEvent(false, null));
                }
            }

            @Override
            public void onFailure(Call<SWPeopleModel> call, Throwable t) {
                t.printStackTrace();
                bus.post(new SWDetailsFailureEvent(false, null));
            }
        });

    }

    static class Error {
        @SerializedName("detail")
        @Expose
        String message;
    }
}
