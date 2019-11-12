package com.thesis.recommenderapp.dao;

import com.thesis.recommenderapp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    Page<User> findAllByUserNameContainingIgnoreCase(String substring, Pageable pageable);

    User findByUserName(String userName);

}
