package com.contare.pokemonapp.dtos;


import com.fasterxml.jackson.annotation.JsonAutoDetect;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AbilitiesVO {

    private AbilityDetailsVO ability;

    public AbilityDetailsVO getAbility() {
        return ability;
    }

    public void setAbility(AbilityDetailsVO ability) {
        this.ability = ability;
    }
}
