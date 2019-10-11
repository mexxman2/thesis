package com.thesis.recommenderapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesis.recommenderapp.dao.WatchedDao;
import com.thesis.recommenderapp.domain.AddToWatchListItem;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.domain.Watched;

@Service
public class WatchedService {

    @Autowired
    private WatchedDao watchedDao;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    public void saveWatched(AddToWatchListItem addToWatchListItem, String userName) {
        Watched watched = new Watched();
        watched.setRating(addToWatchListItem.getRating());
        watched.setItem(itemService.getItem(addToWatchListItem.getItemId()));
        User user = userService.getUserByUserName(userName);
        watched.setUser(user);
        List<Item> items = getWatchedItems(user.getId());
        int index = getIndex(items, watched.getItem());
        addToWatchListItem.getItemId();
        if(index == items.size() && !items.isEmpty()) {
            user.updateWatched(index, watched);
        } else {
            user.addToWatched(watched);
        }
        watchedDao.save(watched);
        userService.saveUser(user);
    }

    public List<Item> getWatchedItems(Long userId) {
        List<Item> result = new ArrayList<>();
        watchedDao.findAllByUserId(userId).forEach(watched -> result.add(watched.getItem()));
        return result;
    }

    /*public Optional<Watched> getWatchedIfPresent(Long itemId, Long userId) {
        Optional<Watched> myWatched = Optional.empty();
        watchedDao.findAllByUserId(userId).forEach((watched) -> {
            if(watched.getItem().getId().equals(itemId)) {
                myWatched = Optional.of(watched);
            }
        });
        return watched;
    }*/

    private int getIndex(List<Item> items, Item item) {
        int i = 0;
        boolean condition = false;
        while(!condition && i < items.size()){
            Long itemId = items.get(i).getId();
            condition = itemId.equals(item.getId());
            i++;
        }
        return i;
    }

}
