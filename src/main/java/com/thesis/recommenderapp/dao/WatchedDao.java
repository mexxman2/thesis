package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.Watched;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WatchedDao extends CrudRepository<Watched, Long> {

    Page<Watched> findAllByUserId(Long userId, Pageable pageable);

    List<Watched> findAllByUserId(Long userId);

}
