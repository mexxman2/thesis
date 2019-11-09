package com.thesis.recommenderapp.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Data
public class Watched {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Item item;
    private Integer rating;

}
