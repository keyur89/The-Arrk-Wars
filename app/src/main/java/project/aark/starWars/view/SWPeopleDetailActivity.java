package project.aark.starWars.view;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.aark.R;
import project.aark.Utils.FontChangeCrawler;
import project.aark.common.BaseActivity;
import project.aark.dagger.DaggerInjector;
import project.aark.starWars.contract.SWPeopleDetailProvider;
import project.aark.starWars.model.SWCharacterModel;
import project.aark.starWars.presenter.SWPeoplePresenter;

public class SWPeopleDetailActivity extends BaseActivity implements SWPeopleDetailProvider {
    @Inject
    SWPeoplePresenter swPeoplePresenter;

    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.txtName)
    TextView txtName;
    @BindView(R.id.txtHeight)
    TextView txtHeight;
    @BindView(R.id.txtMass)
    TextView txtMass;
    @BindView(R.id.txtCreatedDate)
    TextView txtCreatedDate;
    @BindView(R.id.txtNoData)
    TextView txtNoData;

    private SWCharacterModel swCharacterModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sw_people_detail);
        ButterKnife.bind(this);
        DaggerInjector.get().inject(this);
        FontChangeCrawler.getStartWarsFont(this).replaceFonts((ViewGroup) this.findViewById(android.R.id.content));

        swCharacterModel = (SWCharacterModel) getIntent().getSerializableExtra("CharacterDetail");

        setupActionbar(swCharacterModel.getName());

        swPeoplePresenter.setCharacterDetail(this, swCharacterModel);
    }

    public void setupActionbar(String name) {

        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(name != null ? name : "");
        tv.setTextSize(18);
        tv.setTextColor(Color.WHITE);
        tv.setTypeface(getStarJediFont());
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setCustomView(tv);
    }

    @Override
    public void showError(String message) {
        try {
            Snackbar snackbar = Snackbar.make(cardView, message, Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (Exception e) {

        }
    }

    @Override
    public void setNoData() {
        txtNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public void setCharacterName(String name) {
        txtName.setText(name);
    }

    @Override
    public void setCharacterMass(String mass) {
        txtMass.setText(mass);
    }

    @Override
    public void setCharacterHeight(String height) {
        txtHeight.setText(height);
    }

    @Override
    public void setCharacterCreatedDate(String created) {
        txtCreatedDate.setText(created);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

}
