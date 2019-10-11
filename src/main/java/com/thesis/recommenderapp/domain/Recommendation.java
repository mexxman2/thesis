package com.thesis.recommenderapp.domain;

import java.util.List;

import lombok.Data;

@Data
public class Recommendation {

    private List<Item> recommendations;

    public void addToRecommendations(Item item) {
        recommendations.add(item);
    }

}
