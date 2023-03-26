package com.example.mvp.androidmvparchitectureexample.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mvp.androidmvparchitectureexample.data.local.entities.ArticleEntity;

import java.util.List;

import io.reactivex.Flowable;


/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

@Dao
public interface ArticleDao {

    @Query("SELECT * FROM article")
    Flowable<List<ArticleEntity>> getArticlesFromDb();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveArticles(List<ArticleEntity> items);

    @Query("DELETE FROM article")
    void deleteArticles();
}
