package com.thesis.recommenderapp.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.thesis.recommenderapp.domain.UploadItemRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImdbAPIGetService {

    private OkHttpClient okHttpClient = new OkHttpClient();

    public String getGeneralSearchResults(UploadItemRequest uploadItemRequest) throws IOException {
        Request request = new Request.Builder()
                .url(createGeneralSearchURL(uploadItemRequest))
                .get()
                .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "2dc4d4daedmshd5b3479ae310a95p1efc8fjsn62e3e5e0ad7e")
                .build();

        log.info(createGeneralSearchURL(uploadItemRequest));
        return okHttpClient.newCall(request).execute().body().string();
    }

    public String getSpecificSearchResults(String imdbId) throws IOException {
        Request request = new Request.Builder()
                .url(createSpecificSearchURL(imdbId))
                .get()
                .addHeader("x-rapidapi-host", "movie-database-imdb-alternative.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "2dc4d4daedmshd5b3479ae310a95p1efc8fjsn62e3e5e0ad7e")
                .build();

        log.info(createSpecificSearchURL(imdbId));
        return okHttpClient.newCall(request).execute().body().string();
    }

    private String createGeneralSearchURL(UploadItemRequest uploadItemRequest) {
        StringBuilder urlBuilder = new StringBuilder("https://movie-database-imdb-alternative.p.rapidapi.com/?page=1&r=json&type=");
        urlBuilder.append(uploadItemRequest.getType());
        urlBuilder.append("&s=");
        urlBuilder.append(transform(uploadItemRequest.getTitle()));
        return urlBuilder.toString();
    }

    private String createSpecificSearchURL(String imdbId) {
        StringBuilder urlBuilder = new StringBuilder("https://movie-database-imdb-alternative.p.rapidapi.com/?r=json&i=");
        urlBuilder.append(imdbId);
        return urlBuilder.toString();
    }

    private String transform(String title) {
        return String.join("%20", title.split(" "));
    }

}
