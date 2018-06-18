package project.aark.starWars.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.aark.R;
import project.aark.starWars.model.DiseaseCategoryModel;

public class DiseaseCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<DiseaseCategoryModel> diseaseCategoryModelList;

    public DiseaseCategoryAdapter(Context mContext, List<DiseaseCategoryModel> diseaseCategoryModelList) {
        this.mContext = mContext;
        this.diseaseCategoryModelList = diseaseCategoryModelList;
    }

    @Override
    public int getItemViewType(int position) {
        return diseaseCategoryModelList.get(position) != null ? 1 : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.disease_category_item_view, parent, false);
        viewHolder = new ViewHolder(layoutView);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int itemPosition = position;
        DiseaseCategoryModel diseaseCategoryModel = diseaseCategoryModelList.get(itemPosition);
        if (holder instanceof ViewHolder) {
            try {
                ((ViewHolder) holder).btnCategory.setText(Html.fromHtml(diseaseCategoryModel.getAsciiValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        return diseaseCategoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.btnCategory)
        Button btnCategory;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, mView);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() >= 0) {
                        Toast.makeText(mContext, diseaseCategoryModelList.get(getAdapterPosition()).getCategoryTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}
