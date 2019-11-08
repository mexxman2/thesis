package com.thesis.recommenderapp.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Recommendation {

    private List<Item> recommendations = new ArrayList<>();

    public void addToRecommendations(Item item) {
        recommendations.add(item);
    }

}
