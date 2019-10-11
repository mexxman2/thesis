package com.thesis.recommenderapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("series")
@Data
public class Series extends Item {

    Integer numberOfSeasons;
    Integer numberOfEpisodes;

}
