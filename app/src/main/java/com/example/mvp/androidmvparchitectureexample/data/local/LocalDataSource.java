package com.example.mvp.androidmvparchitectureexample.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.mvp.androidmvparchitectureexample.data.local.dao.ArticleDao;
import com.example.mvp.androidmvparchitectureexample.data.local.entities.ArticleEntity;

import javax.inject.Singleton;

/**
 * ALL RIGHTS RESERVED - ALEXANDROS KOURTIS
 */

@Singleton
@Database(entities = ArticleEntity.class, version = 1)
public abstract class LocalDataSource extends RoomDatabase {
    public abstract ArticleDao getArticleDao();
}

