package com.contare.pokemonapp.repository;
import com.contare.pokemonapp.models.Moves;
import com.contare.pokemonapp.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovesRepository extends JpaRepository<Moves,Long> {



}
