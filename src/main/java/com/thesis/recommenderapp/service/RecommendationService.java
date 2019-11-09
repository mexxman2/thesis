package com.thesis.recommenderapp.service;

import com.thesis.recommenderapp.dao.WatchedDao;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.Watched;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service
public class RecommendationService {

    @Autowired
    private WatchedDao watchedDao;

    public List<Item> getTopTenPopularItems() {
        Map<Item, Integer> weightedItems = getWeightedItems();
        Map<Item, Integer> sorted = sortMap(weightedItems);
        List<Item> items = new ArrayList<>(sorted.keySet());
        return (items.size() > 10) ? items.subList(0, 10) : items;
    }

    private Map<Item, Integer> getWeightedItems() {
        Map<Item, Integer> itemsWithRating = new HashMap<>();
        for (Watched watched : watchedDao.findAll()) {
            assignValuesToItems(itemsWithRating, watched);
        }
        return itemsWithRating;
    }

    private void assignValuesToItems(Map<Item, Integer> itemsWithRating, Watched watched) {
        Item item = watched.getItem();
        if (itemsWithRating.containsKey(item)) {
            Integer value = itemsWithRating.get(item) + watched.getRating();
            itemsWithRating.replace(item, value);
        } else {
            itemsWithRating.put(item, watched.getRating());
        }
    }

    private Map<Item, Integer> sortMap(Map<Item, Integer> weightedItems) {
        return weightedItems.entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
            .collect(
                toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                    LinkedHashMap::new));
    }

}
