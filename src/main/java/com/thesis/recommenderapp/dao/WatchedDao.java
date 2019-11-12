package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.Watched;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface WatchedDao extends CrudRepository<Watched, Long> {

    List<Watched> findAllByUserId(Long userId, Pageable pageable);

}
