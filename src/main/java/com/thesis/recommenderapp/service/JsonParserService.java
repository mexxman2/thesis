package com.thesis.recommenderapp.service;

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
            if (jsonObject.getInt("totalResults") > 10) {
                throw new ShouldBeMoreSpecificException();
            }
            return results.getJSONObject(0).getString("imdbID");
        } catch (JSONException e) {
            throw new SearchReturnedErrorException();
        }
    }

    public Series getSeries(String jsonString) {
        JSONObject jsonObject = getObject(jsonString);
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

    public Movie getMovie(String jsonString) {
        JSONObject jsonObject = getObject(jsonString);
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
