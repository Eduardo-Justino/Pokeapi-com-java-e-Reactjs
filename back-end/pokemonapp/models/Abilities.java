package com.contare.pokemonapp.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Abilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column()
    private String name;

    @Column()
    private String url;

    @ManyToOne()
    @JoinColumn(name = "pokemon_id",foreignKey = @ForeignKey(name = "fk_abilities_pokemon"))
    private Pokemon pokemon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
