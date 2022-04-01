package com.contare.pokemonapp.dtos;

import com.contare.pokemonapp.models.Moves;
import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MovesVO {
   private MoveDetailsVO move;



    public MoveDetailsVO getMove() {
      return move;
   }

   public void setMove(MoveDetailsVO move) {
      this.move = move;
   }
}

