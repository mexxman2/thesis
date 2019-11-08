package com.thesis.recommenderapp.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thesis.recommenderapp.dao.ItemDao;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.UploadItemRequest;
import com.thesis.recommenderapp.service.exceptions.ImdbException;

@Service
@Transactional
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

    public Long saveItem(UploadItemRequest uploadItemRequest) {
        try {
            return saveByTitleOrURL(uploadItemRequest);
        } catch (IOException e) {
            throw new ImdbException(e.getMessage());
        }
    }

    private Long saveByTitleOrURL(UploadItemRequest uploadItemRequest) throws IOException {
        Long id;
        if (!uploadItemRequest.getTitleOrURL().contains("title/tt")) {
            id = saveByTitle(uploadItemRequest);
        } else {
            id = saveByURL(uploadItemRequest);
        }
        return id;
    }

    private Long saveByTitle(UploadItemRequest uploadItemRequest) throws IOException {
        String generalSearchResult = imdbAPIGetService.getGeneralSearchResults(uploadItemRequest);
        String imdbId = jsonParserService.getImdbId(generalSearchResult);
        if (!itemDao.existsByImdbId(imdbId)) {
            String specificSearchResult = imdbAPIGetService.getSpecificSearchResults(jsonParserService.getImdbId(generalSearchResult));
            return saveMovieOrSeries(uploadItemRequest, specificSearchResult);
        } else {
            return itemDao.findByImdbId(imdbId).getId();
        }
    }

    private Long saveMovieOrSeries(UploadItemRequest uploadItemRequest, String specificSearchResult) {
        Long id;
        if (uploadItemRequest.getType().equals("movie")) {
            id = itemDao.save(jsonParserService.getMovie(specificSearchResult)).getId();
        } else {
            id = itemDao.save(jsonParserService.getSeries(specificSearchResult)).getId();
        }
        return id;
    }

    private Long saveByURL(UploadItemRequest uploadItemRequest) throws IOException {
        List<String> urlParts = Arrays.asList(uploadItemRequest.getTitleOrURL().split("/"));
        String imdbId = urlParts.get(urlParts.indexOf("title") + 1);
        if (!itemDao.existsByImdbId(imdbId)) {
            String specificSearchResult = imdbAPIGetService.getSpecificSearchResults(imdbId);
            return saveMovieOrSeries(uploadItemRequest, specificSearchResult);
        } else {
            return itemDao.findByImdbId(imdbId).getId();
        }
    }

}
