package com.thesis.recommenderapp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("series")
@Data
@SuperBuilder
@NoArgsConstructor
public class Series extends Item {

    @JsonProperty("totalSeasons")
    private String totalSeasons;

}
