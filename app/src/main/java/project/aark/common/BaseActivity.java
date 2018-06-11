package project.aark.common;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;

import project.aark.Utils.NetworkUtils;

/**
 * Created by Keyur Tailor on 11-Jun-2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseScreen {
    Typeface starJediFont;

    @Override
    public boolean checkIfNetworkConnected() {
        return new NetworkUtils(this).getConnectivityStatus();
    }

    public Typeface getStarJediFont() {
        return starJediFont = Typeface.createFromAsset(getAssets(), "starjout.ttf");
    }

}
