package com.contare.pokemonapp.service;
import com.contare.pokemonapp.models.Moves;
import com.contare.pokemonapp.repository.MovesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MovesService  {


    final MovesRepository movesRepository;

    public MovesService(MovesRepository movesRepository) {this.movesRepository = movesRepository;}

    public Moves save (Moves moves) {return movesRepository.save(moves);}

    public Moves findMovesById (Long id){ return movesRepository.getById(id);}

    public Stream<String> findAllMoves(){return movesRepository.findAll().stream().map(Moves::getName);}



}
