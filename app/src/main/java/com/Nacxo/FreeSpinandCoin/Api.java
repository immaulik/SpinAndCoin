package com.Nacxo.FreeSpinandCoin;

import com.Nacxo.FreeSpinandCoin.POJO.LinkData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api
{

    @GET("/services/get_links.php?")
    Call<LinkData> getData(@Query("title") String title);
}
