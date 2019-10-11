package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.Watched;
import org.springframework.data.repository.CrudRepository;

public interface WatchedDao extends CrudRepository<Watched, Long> {

    Iterable<Watched> findAllByUserId(Long userId);

}
