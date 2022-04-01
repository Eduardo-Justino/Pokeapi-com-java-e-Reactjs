package com.contare.pokemonapp.service;
import com.contare.pokemonapp.dtos.MovesVO;
import com.contare.pokemonapp.dtos.PokemonVO;
import com.contare.pokemonapp.models.Abilities;
import com.contare.pokemonapp.models.Moves;
import com.contare.pokemonapp.models.Pokemon;
import com.contare.pokemonapp.repository.MovesRepository;
import com.contare.pokemonapp.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public  class PokemonService  {



    final MovesService movesService;
    final PokemonRepository pokemonRepository;
    final MovesRepository movesRepository;
    WebClient webClient;


    public PokemonService(MovesService movesService, PokemonRepository pokemonRepository, MovesRepository movesRepository, WebClient.Builder builder) {
        this.movesService = movesService;
        this.pokemonRepository = pokemonRepository;
        this.movesRepository = movesRepository;
        webClient = builder.baseUrl("https://pokeapi.co/api/v2").build();
    }

    public ResponseEntity<Object> getOnePokemonInDb(Long id){
        Optional<Pokemon> pokemonOptional = pokemonRepository.findById(id);
        if (!pokemonOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ops...Pokemon nao encontrado");
        }
        Pokemon pokemon = createPokemon(pokemonOptional);
        return ResponseEntity.status(HttpStatus.OK).body(pokemon);
    }


    public Mono<PokemonVO> findAndPokemonByIdNet(String id) {
        return webClient
                .get()
                .uri("/pokemon/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        error -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "id no formato inavalido,ou inesxistente")))
                .bodyToMono(PokemonVO.class);
    }


    public Pokemon save(Pokemon pokemon) {return pokemonRepository.save(pokemon);}

    public Pokemon findByIdPokemon(String id) {
        return pokemonRepository.findByIdPokemon(id);
    }

    public Stream<String> findAllPokemon(){return pokemonRepository.findAll().stream().map(Pokemon::getName);};

    public Stream<String> searchPokemon(String name) {return pokemonRepository.findByNameContaining(name).stream().map(Pokemon::getName);}


    public ResponseEntity<String> delete(Long id) {
        Optional<Pokemon> pokemonOptional = pokemonRepository.findById(id);
        if (!pokemonOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ops...,Pokemon nao encontrado para deletar");
        }
        pokemonRepository.deleteById(pokemonOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body("pokemon deletado com sucesso!!!");

    }


    public Pokemon createPokemon(Optional<Pokemon> pokemon) {
        Pokemon pokemonObj = new Pokemon();
        pokemonObj.setName(pokemon.get().getName());
        pokemonObj.setIdPokemon(pokemon.get().getIdPokemon());
        pokemonObj.setWeight(pokemon.get().getWeight());
        pokemonObj.setBaseExperience(pokemon.get().getBaseExperience());
        pokemonObj.setId(pokemon.get().getId());
        pokemonObj.setAbilities(pokemon.get().getAbilities());
        return pokemonObj;
    }


    public ResponseEntity<Object> findAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        if(pokemons.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ops...Nenhum pokemon encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pokemons);
    }

    public ResponseEntity<Object> getAllMovesForId(Long pokemonId) {
        Optional validation = pokemonRepository.findById(pokemonId);
        if(!validation.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ops...Nenhum movimento encontrado para este pokemon");
        }
        Pokemon pokemon = pokemonRepository.findById(pokemonId).get();
        Stream<String> result = pokemon.getMoves().stream().map(Moves::getName);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    public ResponseEntity<Object> getAllAbilitiesForId(Long pokemonId) {
        Optional validation = pokemonRepository.findById(pokemonId);
        if(!validation.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ops...Nenhuma habilidade encontrada para este pokemon");
        }
        Pokemon pokemon = pokemonRepository.findById(pokemonId).get();
        Stream<String> result = pokemon.getAbilities().stream().map(Abilities::getName);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }








}





