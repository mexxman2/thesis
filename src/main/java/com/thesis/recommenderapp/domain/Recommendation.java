package com.thesis.recommenderapp.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Recommendation {

    private List<Item> recommendations = new ArrayList<>();

    public void addToRecommendations(Item item) {
        recommendations.add(item);
    }

}
