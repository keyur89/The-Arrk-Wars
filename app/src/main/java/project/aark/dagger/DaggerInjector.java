package project.aark.dagger;


import project.aark.dagger.components.AppComponent;
import project.aark.dagger.components.DaggerAppComponent;
import project.aark.dagger.modules.AppModule;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */


public class DaggerInjector {
    private static AppComponent appComponent =
            DaggerAppComponent.builder().appModule(new AppModule()).build();


    public static AppComponent get() {
        return appComponent;
    }

}