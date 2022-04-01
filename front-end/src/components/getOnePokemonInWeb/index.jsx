import api from "../../services/api"
import React, { useState, useEffect } from "react"




const GetOnePokemonInWeb = () => {

    const [abilitiesPokemon, SetAbilities] = useState([]);
    const [movesPokemon, SetMoves] = useState([]);
    const [idPokemon, SetidPokemon] = useState()
    const [name, Setname] = useState()
    const [base_experience, SetbaseExperience] = useState()
    const [weight, Setweight] = useState()
    const [id, Setid] = useState()
    const [error, SetError] = useState(false)
    const [status, SetStatus] = useState(false)




    const getPokemonsById = async () => {
       await api.get(`/pokemon/pokemon-in-web/${id}/`)
            .then((response) => {
                console.log(response.data)
                SetStatus(true)
                const { name, id, weight, base_experience, moves, abilities,
                } = response.data;

                SetidPokemon(id);
                Setname(name);
                SetbaseExperience(base_experience);
                Setweight(weight);
                SetMoves(moves)
                SetAbilities(abilities)
                SetError(false)

            }).catch((err) => {
                if (err.response) {
                    SetError(true)
                    SetStatus(false)
                }
            });
    }




    return (

        <div>
            <div className="cardpesquisa">
                <input className="pesquisar" placeholder="digite o id ou nome do pokemon para pesquisar" type="text" value={id} onChange={e => Setid(e.target.value)} />
                <button className="pesquisar" type="button" onClick={getPokemonsById}>Pesquisar</button>
            </div>
            {status ?
                <div className="card">
                    <img src={`https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${idPokemon}.png`} className="imgweb" />
                    <ul className="info">Id do pokemon: <p className="info"> {idPokemon} </p> </ul>
                    <ul className="info">Nome do Pokemon: <p className="info">{name}</p> </ul>
                    <ul className="info">Base de experiencia: <p className="info">{base_experience}</p> </ul>
                    <ul className="info">Peso: <p className="info">{weight}</p>  </ul>
                    <select className="habilidades">
                    {abilitiesPokemon.map((ability, index) => {
                        return (    
                            <>
                                    <option>Habilidades</option>
                                    <option className="list"> {ability.ability.name}</option>
                                    </>
                                   

                        );
                    })}
                    </select>
                    <select className="movimentos">
                    {movesPokemon.map((move, index) => {
                        return (   
                                    <>
                                     <option>Movimentos</option>
                                    <option className="list"> {move.move.name}</option>
                                    </>
                                   

                        );
                    })}
                    </select>

                </div>
                : null}
            <div>
                {error ?
                    <div>
                        <h2 className="dadosPokemon">Este pokemon não existe</h2>
                        <img className="imgweb" src="https://pm1.narvii.com/6375/69386816c2d10aace9de3c73e9fe60f059eb5cc7_hq.jpg" />
                    </div>
                    : null}
            </div>
          
     

        </div>





    )
}

export default GetOnePokemonInWeb;