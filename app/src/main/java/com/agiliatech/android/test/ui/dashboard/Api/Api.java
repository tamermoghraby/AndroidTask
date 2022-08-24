package com.agiliatech.android.test.ui.dashboard.Api;



import com.agiliatech.android.test.ModelResponse.FetchSessionResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("mydata.api")
    Call<FetchSessionResponse> fetchAllSessions();

}
