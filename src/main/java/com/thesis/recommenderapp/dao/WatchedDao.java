package com.thesis.recommenderapp.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.domain.Watched;

public interface WatchedDao extends CrudRepository<Watched, Long> {

    Page<Watched> findAllByUserId(Long userId, Pageable pageable);

    List<Watched> findAllByUserId(Long userId);

    Watched findByUserAndItem(User user, Item item);

}
