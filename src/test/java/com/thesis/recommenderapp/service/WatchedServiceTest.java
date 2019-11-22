package com.thesis.recommenderapp.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.thesis.recommenderapp.dao.WatchedDao;
import com.thesis.recommenderapp.domain.AddToWatchListItem;
import com.thesis.recommenderapp.domain.Item;
import com.thesis.recommenderapp.domain.User;
import com.thesis.recommenderapp.domain.Watched;

public class WatchedServiceTest {

    private static final Long TEST_ITEM_ID = 1L;
    private static final Integer TEST_NEW_RATING = 10;
    private static final Integer TEST_OLD_RATING = 9;
    public static final String TEST_USER_NAME = "";

    @InjectMocks
    private WatchedService underTest;

    @Mock
    private WatchedDao watchedDao;

    @Mock
    private ItemService itemService;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveWatchedShouldUpdateRatingIfAlreadyExists() {
        //GIVEN
        AddToWatchListItem addToWatchListItem = createAddToWatchListItem();
        User user = new User();
        Item item = createItem();
        Watched watched = createWatched(item, TEST_OLD_RATING);
        user.addToWatched(watched);
        given(itemService.getItem(TEST_ITEM_ID)).willReturn(item);
        given(userService.getUserByUserName(TEST_USER_NAME)).willReturn(user);
        //WHEN
        underTest.saveWatched(addToWatchListItem, TEST_USER_NAME);
        //THEN
        Assert.assertEquals(TEST_NEW_RATING, user.getWatched().get(0).getRating());
        then(itemService).should().getItem(TEST_ITEM_ID);
        then(userService).should().getUserByUserName(TEST_USER_NAME);
        then(userService).should().saveUser(user);
    }

    private AddToWatchListItem createAddToWatchListItem() {
        AddToWatchListItem addToWatchListItem = new AddToWatchListItem();
        addToWatchListItem.setItemId(TEST_ITEM_ID);
        addToWatchListItem.setRating(TEST_NEW_RATING);
        return addToWatchListItem;
    }

    private Item createItem() {
        return Item.builder()
                .id(TEST_ITEM_ID)
                .build();
    }

    private Watched createWatched(Item item, Integer rating) {
        Watched watched = new Watched();
        watched.setItem(item);
        watched.setRating(rating);
        return watched;
    }

}
