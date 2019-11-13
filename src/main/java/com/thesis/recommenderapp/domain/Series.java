package com.thesis.recommenderapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

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
