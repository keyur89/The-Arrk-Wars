package project.aark.dagger.components;


import javax.inject.Singleton;

import dagger.Component;
import project.aark.dagger.modules.AppModule;
import project.aark.starWars.view.SWPeopleActivity;
import project.aark.starWars.view.SWPeopleDetailActivity;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */


@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(SWPeopleActivity dashboardActivity);

    void inject(SWPeopleDetailActivity swPeopleDetailActivity);
}
