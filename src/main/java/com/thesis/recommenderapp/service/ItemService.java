package com.thesis.recommenderapp.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesis.recommenderapp.dao.ItemDao;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.Movie;
import com.thesis.recommenderapp.domain.Series;
import com.thesis.recommenderapp.domain.UploadItemRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ImdbAPIGetService imdbAPIGetService;
    @Autowired
    private JsonParserService jsonParserService;

    public Item getItem(Long id) {
        return itemDao.findById(id).get();
    }

    public Iterable<Item> getItemsBySubstring(String substring) {
        return itemDao.findAllByTitleContainingIgnoreCase(substring);
    }

    public void saveItem(UploadItemRequest uploadItemRequest) {
        try {
            String generalSearchResult = imdbAPIGetService.getGeneralSearchResults(uploadItemRequest);
            String imdbId = jsonParserService.getImdbId(generalSearchResult);
            if (!itemDao.existsByImdbId(imdbId)) {
                String specificSearchResult = imdbAPIGetService.getSpecificSearchResults(jsonParserService.getImdbId(generalSearchResult));
                if (uploadItemRequest.getType().equals("movie")) {
                    itemDao.save(jsonParserService.getMovie(specificSearchResult));
                } else {
                    itemDao.save(jsonParserService.getSeries(specificSearchResult));
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
