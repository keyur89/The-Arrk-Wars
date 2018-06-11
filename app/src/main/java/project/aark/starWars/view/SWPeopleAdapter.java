package project.aark.starWars.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.aark.R;
import project.aark.Utils.FontChangeCrawler;
import project.aark.starWars.contract.OnItemClickListener;
import project.aark.starWars.model.SWCharacterModel;

public class SWPeopleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private RecyclerView recyclerView;
    private List<SWCharacterModel> characterList;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    private OnLoadMoreListener onLoadMoreListener;
    private OnItemClickListener onItemClickListener;

    public SWPeopleAdapter(Context mContext, List<SWCharacterModel> characterList, RecyclerView recyclerView, OnItemClickListener onItemClickListener) {
        this.mContext = mContext;
        this.characterList = characterList;
        this.recyclerView = recyclerView;
        this.onItemClickListener = onItemClickListener;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    loading = true;
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return characterList.get(position) != null ? 1 : 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        if (viewType == 1) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sw_people_item_view, parent, false);
            viewHolder = new ViewHolder(layoutView);
        } else {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_loading_view, parent, false);
            viewHolder = new ProgressViewHolder(layoutView);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int itemPosition = position;
        SWCharacterModel characterModel = characterList.get(itemPosition);
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).txtCharacterName.setText(characterModel.getName());
        } else {
            ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        loading = false;
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.txtCharacterName)
        TextView txtCharacterName;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, mView);
            FontChangeCrawler.getStartWarsFont(mContext).replaceFonts((ViewGroup) view);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() >= 0) {
                        onItemClickListener.onItemClick(characterList.get(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });
        }
    }

    public class ProgressViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar)
        public ProgressBar progressBar;

        public ProgressViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
