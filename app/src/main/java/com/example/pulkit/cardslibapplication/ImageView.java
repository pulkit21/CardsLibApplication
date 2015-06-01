package com.example.pulkit.cardslibapplication;

import java.util.List;

import retrofit.http.Headers;
import retrofit.http.POST;


public interface ImageView {
    @Headers({
            "X-Mashape-Key: 6xVaQV3xZmPiMUbbxVJ2kyGujfaVqsar",
            "Content-Type: application/x-www-form-urlencoded",
            "Accept: application/json"
    })
    @POST("/dl")
    List<ViralImage> getViralImages();
}
