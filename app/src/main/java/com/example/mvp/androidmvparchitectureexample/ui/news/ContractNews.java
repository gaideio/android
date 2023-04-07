package com.example.mvp.androidmvparchitectureexample.ui.news;

import android.content.Context;

import com.example.mvp.androidmvparchitectureexample.data.local.entities.ArticleEntity;
import com.example.mvp.androidmvparchitectureexample.ui.base.IBaseView;

import java.util.List;

/**
 * ALL RIGHTS RESERVED
 */

public interface ContractNews {

    interface ContractPresenter {
        void getArticles(Context context);

        void getArticlesFromApi();

        void getArticleFromDb();

        void saveArticles(List<ArticleEntity> items);
    }

    interface ContractView extends IBaseView {
        void onArtilesReady(List<ArticleEntity> items);
    }
}
