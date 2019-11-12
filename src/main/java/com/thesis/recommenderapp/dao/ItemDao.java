package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<Item, Long> {

    Page<Item> findAllByTitleContainingIgnoreCase(String substring, Pageable pageable);

    boolean existsByImdbId(String imdbId);

    Item findByImdbId(String imdbId);

}
