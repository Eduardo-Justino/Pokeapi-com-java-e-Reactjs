package com.contare.pokemonapp.dtos;
import com.fasterxml.jackson.annotation.JsonAutoDetect;



@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AbilityDetailsVO {
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
