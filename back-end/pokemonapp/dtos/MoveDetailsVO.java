package com.contare.pokemonapp.dtos;

import com.contare.pokemonapp.models.Moves;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MoveDetailsVO {

    private String name;
    private String url;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
