package com.contare.pokemonapp.service;
import com.contare.pokemonapp.models.Abilities;
import com.contare.pokemonapp.models.Pokemon;
import com.contare.pokemonapp.repository.AbilitiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.stream.Stream;

@Service
public class AbilitiesService {

    final AbilitiesRepository abilitiesRepository;

    public AbilitiesService(AbilitiesRepository abilitiesRepository) {
        this.abilitiesRepository = abilitiesRepository;
    }

    public Abilities save(Abilities abilities) {
        return abilitiesRepository.save(abilities);
    }

    public Stream<String> findAllAbilities(){return abilitiesRepository.findAll().stream().map(Abilities::getName);}


}
