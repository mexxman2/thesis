package com.thesis.recommenderapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thesis.recommenderapp.dao.WatchedDao;
import com.thesis.recommenderapp.domain.AddToWatchListItem;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.domain.Watched;

@Service
@Transactional
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
        if (items.contains(watched.getItem())) {
            user.updateWatched(watched);
        } else {
            user.addToWatched(watched);
            watchedDao.save(watched);
        }
        userService.saveUser(user);
    }

    public List<Watched> getWatchedList(Long userId) {
        return watchedDao.findAllByUserId(userId);
    }

    private List<Item> getWatchedItems(Long userId) {
        return watchedDao.findAllByUserId(userId).stream().map(Watched::getItem).collect(Collectors.toList());
    }

    public void deleteWatched(User user, Long watchedId) {
        Watched watched = watchedDao.findById(watchedId).get();
        user.deleteWatched(watched);
        watchedDao.delete(watched);
        userService.saveUser(user);
    }

}
