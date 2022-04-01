package com.contare.pokemonapp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Pokemon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true,nullable = false)
    private String idPokemon;

    @Column(unique=true,nullable = false)
    private String name;

    @Column(nullable = false)
    private int baseExperience;

    @Column(nullable = false)
    private int weight;

    @OneToMany(mappedBy="pokemon",
            cascade = CascadeType.ALL,orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Moves> moves;

    @OneToMany(mappedBy="pokemon",
            cascade = CascadeType.ALL,orphanRemoval = true,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Abilities> abilities;


}
