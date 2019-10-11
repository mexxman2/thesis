package com.thesis.recommenderapp.domain;

import lombok.Data;

import java.util.List;

@Data
public class Recommendation {

    private List<Item> recommendations;

    public void addToRecommendations(Item item) {
        recommendations.add(item);
    }

}
