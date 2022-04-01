import React, { useState, useEffect } from "react"
import api from "../../services/api";

const GetAllPokemonDB = () => {

    const [data, setData] = useState([])



    useEffect(() => {
        api.get(`/pokemon/get-all-pokemons/`)
            .then((response) => {
                const data = response.data
                setData(data)

            });

    }, []);


    return (

        <div>
            <div className="center" >
            
                {data.map((item) => {
                    const { id, name, weight, base_experience, idPokemon } = item;
                    return (                                        
                         <div className="cardAll" key={id}>
                        <img src={`https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${idPokemon}.png`} className="imgweb" />
                        <ul className="info">Id do pokemon: <p className="info"> {id} </p> </ul>
                        <ul className="info">Nome do Pokemon: <p className="info">{name}</p> </ul>
                        <ul className="info">Base de experiencia: <p className="info">{base_experience}</p> </ul>
                        <ul className="info">Peso: <p className="info">{weight}</p>  </ul>

                        </div>
                     
                    );

                })}
            </div>
        </div>







    )

}

export default GetAllPokemonDB;