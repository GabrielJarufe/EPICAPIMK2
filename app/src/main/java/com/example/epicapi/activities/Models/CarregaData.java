package com.example.epicapi.activities.Models;
import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;



public class CarregaData extends AsyncTaskLoader<String> {
    private String mQueryString;
    public CarregaData(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.buscaInfosData(mQueryString);
    }
}

