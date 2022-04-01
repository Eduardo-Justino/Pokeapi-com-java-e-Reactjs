import api from "../../services/api"
import React, { useState } from "react"


const GetOnePokemonDb = () => {

    const [id, Setid] = useState()
    const [idPokemon, SetidPokemon] = useState()
    const [name, Setname] = useState()
    const [baseExperience, SetbaseExperience] = useState()
    const [weight, Setweight] = useState()
    const [error, SetError] = useState(false)
    const [status, SetStatus] = useState(false)

    const getPokemonsById = () => {
        api.get(`/pokemon/get-one-database/${id}/`)
            .then((response) => {
                const data = response.data
                const corpo = data.body;
                if (data.statusCodeValue === 404) {
                    SetStatus(false)
                    SetError(true)
                    return
                }
                SetStatus(true)
                SetError(false)
                SetidPokemon(corpo.idPokemon);
                Setname(corpo.name);
                SetbaseExperience(corpo.baseExperience);
                Setweight(corpo.weight);

            }).catch((err) => {
                if (err.response) {
                    SetError(true)

                }
            });
    }
    return (

        <div>
            <h1>Bem vindo a pokedex </h1>
            <p>digite o id do pokemon:</p>
            <input placeholder="Senha" type="number" value={id} onChange={e => Setid(e.target.value)} />
            <button type="button" onClick={getPokemonsById}>enviar</button>
            {status ?
                <div>
                    <div>
                        <h1>Pokemon encontrado no banco</h1>
                        <img src={`https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${idPokemon}.png`} />
                        <ul>Id do pokemon: {idPokemon} </ul>
                        <ul>Nome do Pokemon:{name} </ul>
                        <ul>Base de experiencia:{baseExperience} </ul>
                        <ul>Peso: {weight} </ul>
                    </div>
                </div>
                : null}
            <div>
                {error ?
                    <div>
                        <h1>Nenhum pokemon encontrado no banco</h1>
                    </div>
                    : null}
            </div>
        </div>




    )
}

export default GetOnePokemonDb;