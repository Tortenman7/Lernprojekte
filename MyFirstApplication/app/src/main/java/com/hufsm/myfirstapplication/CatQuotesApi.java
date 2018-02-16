package com.hufsm.myfirstapplication;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tmiskowiak on 09.02.2018.
 */

public interface CatQuotesApi {
    @GET("/facts")
    Call<String> getCatFact();
}
