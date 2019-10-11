package com.thesis.recommenderapp.service;

import com.thesis.recommenderapp.dao.ItemDao;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.Movie;
import com.thesis.recommenderapp.domain.Series;
import com.thesis.recommenderapp.domain.UploadItemRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemDao itemDao;

    public Item getItem(Long id) {
        return itemDao.findById(id).get();
    }

    public Iterable<Item> getItemsBySubstring(String substring) {
        return itemDao.findAllByTitleContainingIgnoreCase(substring);
    }

    public void uploadItem(UploadItemRequest uploadItemRequest) {
        if(uploadItemRequest.getType().equals("movie")) {
            uploadMovie(uploadItemRequest);
        } else {
            uploadSeries(uploadItemRequest);
        }
    }

    private void uploadSeries(UploadItemRequest uploadItemRequest) {
        Series item = new Series();
        item.setTitle(uploadItemRequest.getTitle());
        item.setDescription(uploadItemRequest.getDescription());
        item.setNumberOfSeasons(uploadItemRequest.getNumberOfSeasons());
        item.setNumberOfEpisodes(uploadItemRequest.getNumberOfEpisodes());
        itemDao.save(item);
    }

    private void uploadMovie(UploadItemRequest uploadItemRequest) {
        Movie item = new Movie();
        item.setTitle(uploadItemRequest.getTitle());
        item.setDescription(uploadItemRequest.getDescription());
        itemDao.save(item);
    }

}
