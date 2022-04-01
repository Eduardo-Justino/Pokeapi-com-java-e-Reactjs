import api from "../../services/api"
import React, { useState } from "react"


const DeletePokemonIdDb = () => {

    const [id, Setid] = useState()
    const [status, SetStatus] = useState("")

    const DeletePokemonsById = async () => {
        await api.delete(`pokemon/pokemon-delete-db/${id}/`,)
            .then((response) => {
                const data = response.data
                const corpo = data.body;
                SetStatus(": " + corpo)
                window.location.reload()
            }).catch((err) => {
                if (err.response) {
                    alert("servidor fora do ar")
                }
            });
    }
    return (

        <div className="cardpesquisa">
            <input className="pesquisar" placeholder="id do pokemob" type="number" value={id} onChange={e => Setid(e.target.value)} />
            <button className="pesquisar" type="button" onClick={DeletePokemonsById}>enviar</button>
            <div>{status}</div>

        </div>




    )




}



export default DeletePokemonIdDb