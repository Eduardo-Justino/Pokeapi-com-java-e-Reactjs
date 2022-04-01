package com.contare.pokemonapp.repository;

import com.contare.pokemonapp.models.Abilities;
import com.contare.pokemonapp.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilitiesRepository extends JpaRepository<Abilities,Long> {


}
