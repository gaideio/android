package com.example.mvp.androidmvparchitectureexample.ui.news;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.local.entities.ArticleEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ArticleHolder> {

    private static final String TAG = NewsAdapter.class.getSimpleName();
    private final OnItemClickListener mListener;
    private final Context mContext;
    private List<ArticleEntity> mItems;

    public NewsAdapter(Context context, List<ArticleEntity> items, OnItemClickListener listener) {
        mContext = context;
        mItems = items;
        mListener = listener;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);

        return new ArticleHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(ArticleHolder holder, int position) {

        ArticleEntity mItem = mItems.get(position);

        if (mItem != null) {

            if (mItem.getTitle() != null) {
                holder.mTitle.setText(mItem.getTitle());
            }

            if (mItem.getDescription() != null) {
                holder.mDescription.setText(mItem.getDescription());
            }

            if (mItem.getUrlToImage() != null) {

                Glide
                        .with(mContext)
                        .load(mItem.getUrlToImage())
                        .listener(new RequestListener<>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                if (holder.mProgress != null) {
                                    holder.mProgress.setVisibility(View.GONE);
                                }

                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                if (holder.mProgress != null) {
                                    holder.mProgress.setVisibility(View.GONE);
                                }

                                return false;
                            }
                        })
//                        .diskCacheStrategy(DiskCacheStrategy.ALL)
//                        .centerCrop()
//                        .crossFade()
                        .into(holder.mThumbnail);
            } else {

                holder.mProgress.setVisibility(View.GONE);

                Glide
                        .with(mContext)
                        .load(R.drawable.news_placeholder)
                        .into(holder.mThumbnail);


            }

        }

        holder.mContainer.setOnClickListener(view -> mListener.onItemClick(mItem));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<ArticleEntity> mItems) {

        if (mItems != null && !mItems.isEmpty()) {

            this.mItems = mItems;
            notifyDataSetChanged();
        }

    }

    public interface OnItemClickListener {
        void onItemClick(ArticleEntity item);
    }

    public class ArticleHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.container)
        RelativeLayout mContainer;
        @BindView(R.id.thumbnail)
        ImageView mThumbnail;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.description)
        TextView mDescription;
        @BindView(R.id.progress)
        ProgressBar mProgress;

        public ArticleHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

