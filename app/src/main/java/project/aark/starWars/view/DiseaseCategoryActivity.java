package project.aark.starWars.view;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.aark.R;
import project.aark.Utils.UtilsHelper;
import project.aark.common.BaseActivity;
import project.aark.starWars.model.DiseaseCategoryModel;

public class DiseaseCategoryActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_category);
        ButterKnife.bind(this);
        setupActionbar();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setupData();

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

    public void setupData() {
        Log.d("HERE", "setupData");
        ArrayList<DiseaseCategoryModel> diseaseCategoryModelArrayList = new ArrayList<>();
        JSONObject categoryMainObj = UtilsHelper.getDiseaseCategoryJson();
        Log.d("HERE", "categoryMainObj "+categoryMainObj);
        ArrayList<String> keysList = new ArrayList<>();
        Iterator<?> keys = null;
        try {
            keys = categoryMainObj.keys();
            while(keys.hasNext() ) {
                String key = (String)keys.next();
                    Log.d("KEY", key);
                    keysList.add(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < keysList.size(); i++) {
            try {
                JSONArray subArrayObj = categoryMainObj.getJSONArray(keysList.get(i));
                DiseaseCategoryModel diseaseCategoryModel = new DiseaseCategoryModel(keysList.get(i), subArrayObj.getString(0), subArrayObj.getString(1));
                Log.d("DISEASE_CATEGORY_MODEL",diseaseCategoryModel.toString());
                diseaseCategoryModelArrayList.add(diseaseCategoryModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        DiseaseCategoryAdapter adapter = new DiseaseCategoryAdapter(this, diseaseCategoryModelArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String message) {

    }
}
