package com.contare.pokemonapp.models;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Moves  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column()
    private Long id;

    private String name;

    @Column()
    private String url;

    @ManyToOne()
    @JoinColumn(name = "pokemon_id", foreignKey = @ForeignKey(name = "fk_move_pokemon"))
    private Pokemon pokemon;


    public Long getId() {
        return id;
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
