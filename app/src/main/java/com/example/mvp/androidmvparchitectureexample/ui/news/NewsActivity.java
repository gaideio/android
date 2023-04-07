/*
 * ALL RIGHTS RESERVED
 */

package com.example.mvp.androidmvparchitectureexample.ui.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.androidmvparchitectureexample.GaideioApp;
import com.example.mvp.androidmvparchitectureexample.R;
import com.example.mvp.androidmvparchitectureexample.data.local.entities.ArticleEntity;
import com.example.mvp.androidmvparchitectureexample.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity implements ContractNews.ContractView, NewsAdapter.OnItemClickListener {

    private static final String TAG = NewsActivity.class.getSimpleName();

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @BindView(R.id.content_error)
    RelativeLayout mErrorContainer;
    @Inject
    NewsPresenter mPresenter;
    private NewsAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ButterKnife.bind(this);

        GaideioApp.getNewsComponent().inject(this);

        mPresenter.attachView(this);

        mPresenter.getArticles(this);

        setUp();
    }

    @Override
    protected void setUp() {
        mAdapter = new NewsAdapter(this, new ArrayList<>(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public void onArtilesReady(List<ArticleEntity> items) {
        if (items != null && !items.isEmpty()) {

            hideErrorContainer();
            mAdapter.setItems(items);

        } else {
            showErrorContainer();
        }
    }

    @Override
    public void onItemClick(ArticleEntity item) {
        if (item != null && item.getUrl() != null && !item.getUrl().isEmpty()) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(item.getUrl())));
        }
    }

    private void showErrorContainer() {
        mErrorContainer.setVisibility(View.VISIBLE);
    }

    private void hideErrorContainer() {
        mErrorContainer.setVisibility(View.GONE);

    }

    @OnClick(R.id.repeat_btn)
    public void onViewClicked() {
        mPresenter.getArticles(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
