package com.example.hanaiabeauty.notifecatoin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServes {
    @Headers({
            "Content-Type:application/json",
            "Authorization:key=AAAAxl2vRcg:APA91bHtW-CKl8onRvSpuWm4P5K9NsiZa-4imdC64O_hG4PpLdnAxEdH7wy0hTCHRbQwMhbeeW_lzZ4M9h_WnepzmwZNBjQohQkAzlWzvajws6xBPzVNcJRmc_MV06PGFxLiP-339z1C"
    })
    @POST("fcm/send")
    Call<MyResponse>sendNotifectoin(@Body Sender body);
}
