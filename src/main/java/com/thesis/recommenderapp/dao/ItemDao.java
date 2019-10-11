package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemDao extends CrudRepository<Item, Long> {

    Iterable<Item> findAllByTitleContainingIgnoreCase(String substring);

}
