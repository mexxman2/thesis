package com.thesis.recommenderapp.service;

import com.thesis.recommenderapp.domain.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

import com.thesis.recommenderapp.domain.Movie;
import com.thesis.recommenderapp.domain.Series;
import com.thesis.recommenderapp.service.exceptions.SearchReturnedErrorException;
import com.thesis.recommenderapp.service.exceptions.ShouldBeMoreSpecificException;

@Service
public class JsonParserService {

    public String getImdbId(String jsonString) {
        JSONObject jsonObject = getObject(jsonString);
        try {
            JSONArray results = jsonObject.getJSONArray("Search");
            String imdbId = results.getJSONObject(0).getString("imdbID");
            if (jsonObject.getInt("totalResults") > 10) {
                throw new ShouldBeMoreSpecificException(results.getJSONObject(0).getString("Title"), imdbId);
            }
            return imdbId;
        } catch (JSONException e) {
            throw new SearchReturnedErrorException();
        }
    }

    public Item getItem(String jsonString) {
        JSONObject jsonObject = getObject(jsonString);
        Item result;
        if (jsonObject.getString("Type").equalsIgnoreCase("movie")) {
            result = getMovie(jsonObject);
        } else {
            result = getSeries(jsonObject);
        }
        return result;
    }

    private Series getSeries(JSONObject jsonObject) {
        try {
            return Series.builder()
                    .title(jsonObject.getString("Title"))
                    .year(jsonObject.getString("Year"))
                    .runtime(jsonObject.getString("Runtime"))
                    .genre(jsonObject.getString("Genre"))
                    .description(jsonObject.getString("Plot"))
                    .imdbRating(jsonObject.getString("imdbRating"))
                    .totalSeasons(jsonObject.getString("totalSeasons"))
                    .imdbId(jsonObject.getString("imdbID"))
                    .posterPath(jsonObject.getString("Poster"))
                    .build();
        } catch (JSONException e) {
            throw new SearchReturnedErrorException();
        }
    }

    private Movie getMovie(JSONObject jsonObject) {
        try {
            return Movie.builder()
                    .title(jsonObject.getString("Title"))
                    .year(jsonObject.getString("Year"))
                    .runtime(jsonObject.getString("Runtime"))
                    .genre(jsonObject.getString("Genre"))
                    .description(jsonObject.getString("Plot"))
                    .imdbRating(jsonObject.getString("imdbRating"))
                    .imdbId(jsonObject.getString("imdbID"))
                    .posterPath(jsonObject.getString("Poster"))
                    .build();
        } catch (JSONException e) {
            throw new SearchReturnedErrorException();
        }
    }

    private JSONObject getObject(String jsonString) {
        JSONTokener tokener = new JSONTokener(jsonString);
        return new JSONObject(tokener);
    }

}
