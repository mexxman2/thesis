package com.thesis.recommenderapp.dao;

import org.springframework.data.repository.CrudRepository;

import com.thesis.recommenderapp.domain.Watched;

public interface WatchedDao extends CrudRepository<Watched, Long> {

    Iterable<Watched> findAllByUserId(Long userId);

}
