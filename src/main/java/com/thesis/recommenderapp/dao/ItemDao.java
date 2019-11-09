package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemDao extends CrudRepository<Item, Long> {

    List<Item> findAllByTitleContainingIgnoreCase(String substring);

    boolean existsByImdbId(String imdbId);

    Item findByImdbId(String imdbId);

}
