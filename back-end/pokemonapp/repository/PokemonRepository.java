package com.contare.pokemonapp.repository;
import com.contare.pokemonapp.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PokemonRepository extends JpaRepository<Pokemon,Long> {

    Pokemon findByIdPokemon(String id);

    public List<Pokemon> findByNameContaining(String name);

    @Override
    List<Pokemon> findAll();


}