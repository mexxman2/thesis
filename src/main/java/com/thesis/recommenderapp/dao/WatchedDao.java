package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.Watched;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WatchedDao extends CrudRepository<Watched, Long> {

    List<Watched> findAllByUserId(Long userId);

}
