package com.thesis.recommenderapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.thesis.recommenderapp.domain.Item;

public interface ItemDao extends CrudRepository<Item, Long> {

    Iterable<Item> findAllByTitleContainingIgnoreCase(String substring);

    boolean existsByImdbId(String imdbId);

    Item findByImdbId(String imdbId);

}
