package project.aark.starWars.view;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.aark.ApiManager.ApiManager;
import project.aark.R;
import project.aark.ServiceCall.SWDetails.SWDetailsFailureEvent;
import project.aark.ServiceCall.SWDetails.SWDetailsSuccessEvent;
import project.aark.Utils.FontChangeCrawler;
import project.aark.Utils.Injector;
import project.aark.Utils.NetworkUtils;
import project.aark.Utils.UtilsHelper;
import project.aark.common.BaseActivity;
import project.aark.dagger.DaggerInjector;
import project.aark.starWars.contract.OnItemClickListener;
import project.aark.starWars.contract.SWPeopleProvider;
import project.aark.starWars.model.SWCharacterModel;
import project.aark.starWars.presenter.SWPeoplePresenter;

public class SWPeopleActivity extends BaseActivity implements SWPeopleProvider, OnItemClickListener {

    @Inject
    SWPeoplePresenter swPeoplePresenter;

    @BindView(R.id.sw_people_main)
    RelativeLayout sw_people_main;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.lblNoData)
    LinearLayout lblNoData;
    @BindView(R.id.btnReload)
    Button btnReload;

    private ApiManager apiManager;
    private EventBus bus;
    private ProgressDialog progressDialog;
    private SWPeopleAdapter swPeopleAdapter;
    private List<SWCharacterModel> swCharacterList = new ArrayList<>();
    private int pageNumber = 1;
    private int totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sw_people);

        ButterKnife.bind(this);
        DaggerInjector.get().inject(this);
        apiManager = new ApiManager();
        bus = Injector.provideEventBus();
        progressDialog = new ProgressDialog(this);

        UtilsHelper.reverseString("This is a line");

        setupActionbar();

        FontChangeCrawler.getStartWarsFont(this).replaceFonts((ViewGroup) this.findViewById(android.R.id.content));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swPeopleAdapter = new SWPeopleAdapter(this, swCharacterList, recyclerView, this);
        recyclerView.setAdapter(swPeopleAdapter);

        swPeopleAdapter.setOnLoadMoreListener(new SWPeopleAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (swPeopleAdapter.getItemCount() < totalItemCount) {
                    pageNumber++;
                    //add null , so the adapter will check view_type and show progress bar at bottom
                    swCharacterList.add(null);

                    recyclerView.post(new Runnable() {
                        public void run() {
                            swPeopleAdapter.notifyItemInserted(swCharacterList.size() - 1);
                        }
                    });

                    refreshLoading(false);
                }
            }
        });

        btnReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageNumber = 1;
                swCharacterList.clear();
                refreshLoading(true);
            }
        });

        refreshLoading(true);
    }

    public void refreshLoading(boolean showLoading) {
        if (checkIfNetworkConnected()) {
            showLoading(showLoading);
            swPeoplePresenter.callSWPeopleDetails(apiManager, pageNumber);
        } else {
            hideLoading();
            swPeoplePresenter.setNoData(this);
            showError(getResources().getString(R.string.network_error));
        }
    }

    public void setupActionbar() {
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText(getResources().getString(R.string.star_wars_characters));
        tv.setTextSize(18);
        tv.setTextColor(Color.WHITE);
        tv.setTypeface(getStarJediFont());
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);
    }

    @Override
    public boolean checkIfNetworkConnected() {
        return new NetworkUtils(this).getConnectivityStatus();
    }

    public void showLoading(boolean showLoading) {
        if (showLoading) {
            showLoading();
        }
    }

    @Override
    public void showLoading() {
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void showError(String message) {
        try {
            Snackbar snackbar = Snackbar.make(sw_people_main, message, Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (Exception e) {

        }
    }

    @Override
    public void setAdapterData(List<SWCharacterModel> swCharacterModelList) {
        recyclerView.setVisibility(View.VISIBLE);
        lblNoData.setVisibility(View.GONE);

        if (swCharacterList.size() > 0) {
            //   remove progress item
            swCharacterList.remove(swCharacterList.size() - 1);
            swPeopleAdapter.notifyItemRemoved(swCharacterList.size());
        }

        swCharacterList.addAll(swCharacterModelList);
        swPeopleAdapter.setLoaded();
        swPeopleAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(SWCharacterModel swCharacterModel, int position) {
        Intent intent = new Intent(this, SWPeopleDetailActivity.class);
        intent.putExtra("CharacterDetail", swCharacterModel);
        startActivity(intent);
    }

    @Override
    public void setNoData() {
        if (swPeopleAdapter.getItemCount() <= 0) {
            recyclerView.setVisibility(View.GONE);
            lblNoData.setVisibility(View.VISIBLE);
        }
    }

    @Subscribe()
    public void SWDetailsSuccessEvent(SWDetailsSuccessEvent event) {
        hideLoading();
        totalItemCount = event.getSwPeopleModel().getCount();
        swPeoplePresenter.fillCharacterList(this, event.getSwPeopleModel().getResults());
    }

    @Subscribe()
    public void SWDetailFailureEvent(SWDetailsFailureEvent event) {
        hideLoading();
        showError(getResources().getString(R.string.unexpected_error));
        swPeoplePresenter.setNoData(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (!EventBus.getDefault().isRegistered(this)) {
                bus.register(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }
}
