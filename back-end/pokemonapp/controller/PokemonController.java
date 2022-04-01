package com.contare.pokemonapp.controller;
import com.contare.pokemonapp.dtos.PokemonVO;
import com.contare.pokemonapp.models.Moves;
import com.contare.pokemonapp.models.Pokemon;
import com.contare.pokemonapp.service.AbilitiesService;
import com.contare.pokemonapp.service.MovesService;
import com.contare.pokemonapp.service.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600L, allowedHeaders = "*")
@Slf4j
@RequestMapping(value = "/pokemon",produces = MediaType.APPLICATION_JSON_VALUE)
public class PokemonController {

    @Autowired
    final PokemonService pokemonService;
    @Autowired
    final AbilitiesService abilitiesService;
    @Autowired
    final MovesService movesService;

    public PokemonController(PokemonService pokemonService, AbilitiesService abilitiesService, MovesService movesService) {
        this.pokemonService = pokemonService;
        this.abilitiesService = abilitiesService;
        this.movesService = movesService;
    }

    // METODO PARA SALVAR OS POKEMONS
    @PostMapping("/save-in-database/{id}")
    public ResponseEntity<Object>getPokemonById( @PathVariable String id){
        if(pokemonService.findByIdPokemon(id) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ops...Pokemon ja salvo no banco de dados!!");
        }
        Mono<PokemonVO> pokemon = pokemonService.findAndPokemonByIdNet(id);
        pokemon.subscribe(pokemonVO -> {
            pokemonVO.converter(this.pokemonService, this.abilitiesService,this.movesService);
        });
        return ResponseEntity.status(HttpStatus.OK).body("pokemon salvo com sucesso !");
    }


    // METODO PARA DELETAR OS POKEMONS

    @DeleteMapping("/pokemon-delete-db/{id}")
    public ResponseEntity<Object> DeletePokemonInInternet(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.delete(id));
    }


    // METODO PARA VER TODOS MOVIMENTOS DE ACORDO COM CADA POKEMON
    @GetMapping("/get-moves-pokemon/{pokemonId}")
    public ResponseEntity<Object> getAllMovesPokemon(@PathVariable Long pokemonId) {
        ResponseEntity<Object> movimentos = pokemonService.getAllMovesForId(pokemonId);
        return ResponseEntity.status(HttpStatus.OK).body(movimentos);
    }

    // METODO PARA VER TODAS HABILIDADES DE ACORDO COM CADA POKEMON
    @GetMapping("/get-abilities-pokemon/{pokemonId}")
    public ResponseEntity<Object> getAllAbilitiesPokemon(@PathVariable Long pokemonId) {
        ResponseEntity<Object> abilities = pokemonService.getAllAbilitiesForId(pokemonId);
        return ResponseEntity.status(HttpStatus.OK).body(abilities);
    }


    // METODO BUSCAR OS POKEMONS PELO ID DO BANCO
    @GetMapping("/get-one-database/{id}")
    public ResponseEntity<Object> getOnePokemonInDb(@PathVariable Long id){
        ResponseEntity<Object> pokemon = pokemonService.getOnePokemonInDb(id);
        return ResponseEntity.status(HttpStatus.OK).body(pokemon);
    }


    // METODO PARA BUSCAR OS POKEMONS NO POKEAPI
    @GetMapping("/pokemon-in-web/{id}")
    public Mono<PokemonVO>getPokemonInInternet( @PathVariable String id)  {
        Mono<PokemonVO> pokemonOptional = pokemonService.findAndPokemonByIdNet(id);
        return pokemonOptional;
    }

    /* METODO PARA VER OS MOVIMENTOS POR ID
    @GetMapping("/pokemon-move/{id}")
    public ResponseEntity<Object> getMovesInDb(@PathVariable Long id) {
        Moves movesById = movesService.findMovesById(id);

        return ResponseEntity.status(HttpStatus.OK).body(movesById.getName());
    }
    */


    // METODO PARA BUSCAR POKEMONS NO BANCO DE DADOS PELO NOME
    @GetMapping("/pokemon-for-name/{name}")
    public  ResponseEntity getMovesForPokemon(@PathVariable String name) {
        return ResponseEntity.ok(pokemonService.searchPokemon(name));

    }

    /* METODO PARA VER O NOME DE TODOS POKEMONS NO BANCO
    @GetMapping("/get-all-database")
    public ResponseEntity<Stream<String>> getAll () {
        return ResponseEntity.ok(pokemonService.findAllPokemon());
    }
     */


    // METODO PARA VER TODOS MOVIMENTOS DO BANCO
    @GetMapping("/get-all-moves")
    public ResponseEntity<Stream<String>> getAllMoves () {
        return ResponseEntity.ok(movesService.findAllMoves());
    }

    // METODO PARA VER TODAS HABILIDADES DO BANCO
    @GetMapping("/get-all-abilities")
    public ResponseEntity<Stream<String>> getAllAbilities () {
        return ResponseEntity.ok(abilitiesService.findAllAbilities());
    }


    // METODO PARA VER TODOS POKEMONS E SEUS ATRIBUTOS
    @GetMapping("/get-all-pokemons")
    public ResponseEntity<Object> getAllpokemon () {
        return pokemonService.findAll();
    }


    @GetMapping("/mount-pokemons/{id}")
    public ResponseEntity<Object> mountPokemon (@PathVariable Long id) {
        List pokemonMount = new ArrayList();
        ResponseEntity<Object> pokemon = getOnePokemonInDb(id);
        ResponseEntity<Object> abilities = pokemonService.getAllAbilitiesForId(id);
        ResponseEntity<Object> moves = pokemonService.getAllMovesForId(id);
        pokemonMount.add(pokemon);
        pokemonMount.add(abilities);
        pokemonMount.add(moves);


        return ResponseEntity.status(HttpStatus.OK).body( pokemonMount);


    }

























}
