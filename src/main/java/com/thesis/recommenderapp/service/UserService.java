package com.thesis.recommenderapp.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thesis.recommenderapp.dao.UserDao;
import com.thesis.recommenderapp.domain.RegistrationRequest;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.service.exceptions.UsernameAlreadyExistsException;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(Long id) {
        return userDao.findById(id).get();
    }

    public User getUserByUserName(String name) {
        return userDao.findByUserName(name);
    }

    public Iterable<User> getUsersBySubstring(String substring) {
        return userDao.findAllByUserNameContainingIgnoreCase(substring);
    }

    public Set<User> getFriends(String name) {
        return userDao.findByUserName(name).getFriends();
    }

    public Long saveUser(User user) {
        user.setEnabled(true);
        return userDao.save(user).getId();
    }

    public void addFriend(Long friendId, Long currentId) {
        User friend = userDao.findById(friendId).get();
        User currentUser = userDao.findById(currentId).get();
        friend.addFriend(currentUser);
        currentUser.addFriend(friend);
        userDao.save(friend);
        userDao.save(currentUser);
    }

    public void deleteFriend(Long friendId, Long currentId) {
        User friend = userDao.findById(friendId).get();
        User currentUser = userDao.findById(currentId).get();
        friend.deleteFriend(currentUser);
        currentUser.deleteFriend(friend);
        userDao.save(friend);
        userDao.save(currentUser);
    }

    public Long registerUser(RegistrationRequest registrationRequest) {
        if (usernameExists(registrationRequest.getUserName())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        User user = new User();
        user.setUserName(registrationRequest.getUserName());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        return saveUser(user);
    }

    private boolean usernameExists(String userName) {
        User user = userDao.findByUserName(userName);
        return user != null;
    }
}
