package com.contare.pokemonapp.dtos;
import com.contare.pokemonapp.models.Moves;
import com.contare.pokemonapp.models.Pokemon;
import com.contare.pokemonapp.service.AbilitiesService;
import com.contare.pokemonapp.service.MovesService;
import com.contare.pokemonapp.service.PokemonService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)

public class PokemonVO {
    private String name;
    private String id;
    @JsonProperty("base_experience")
    private int baseExperience;
    private int weight;
    private List<MovesVO> moves = new ArrayList<>();
    private List<AbilitiesVO> abilities = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(int baseExperience) {
        this.baseExperience = baseExperience;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public List<MovesVO> getMoves() {
        return moves;
    }

    public void setMoves(List<MovesVO> moves) {
        this.moves = moves;
    }

    public List<AbilitiesVO> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<AbilitiesVO> abilities) {
        this.abilities = abilities;
    }

    public Pokemon converter(PokemonService pokemonService, AbilitiesService abilitiesService, MovesService movesService) {
        Pokemon pokemon = new Pokemon();
        pokemon.setIdPokemon(this.getId());
        pokemon.setName(this.getName());
        pokemon.setBaseExperience(this.getBaseExperience());
        pokemon.setWeight(this.getWeight());
        final Pokemon pokemonSave = pokemonService.save(pokemon);

        List<com.contare.pokemonapp.models.Abilities> abilitiesModels = this.getAbilities().stream()
                .map(ability -> {
                    AbilityDetailsVO abilityDetail = ability.getAbility();
                    com.contare.pokemonapp.models.Abilities abilities = new com.contare.pokemonapp.models.Abilities();
                    abilities.setName(abilityDetail.getName());
                    abilities.setUrl(abilityDetail.getUrl());
                    abilities.setPokemon(pokemonSave);
                    return abilitiesService.save(abilities);
                }).collect(Collectors.toList());
        pokemonSave.setAbilities(abilitiesModels);

        List<com.contare.pokemonapp.models.Moves> movesModels = this.getMoves().stream()
                .map(move -> {
                    MoveDetailsVO moveDetailsVO = move.getMove();
                    Moves moves = new Moves();
                    moves.setName(moveDetailsVO.getName());
                    moves.setUrl(moveDetailsVO.getUrl());
                    moves.setPokemon(pokemonSave);
                    return movesService.save(moves);
                }).collect(Collectors.toList());
        pokemonSave.setMoves(movesModels);
        return pokemonSave;
    }



}


