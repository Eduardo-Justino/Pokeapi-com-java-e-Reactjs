import api from "../../services/api"
import React, { useState } from "react"

const SavePokemonInDatabase = () =>{

    const [id, Setid] = useState()
    const [error, SetError] = useState()
    const [status,SetStatus] = useState (false)

     const SavePokemonsById = async () => {     
      await api.post(`/pokemon/save-in-database/${id}/`)
            .then((response)  => {

                window.setTimeout(function() {
                    window.location.href =window.location.href;
                }, 2000);
               
                SetStatus(true)
                SetError(false)
                         
            }).catch((err)=>{
                if(err.response){
                    SetError("Pokemon ja cadastrado") 
                    console.log(err.response.status)     
                }
            });
        }
    return(    
        
        <div className="cardpesquisa">
        <input  className="pesquisar"   placeholder="digite o id para salvar no banco" type="number" value={id} onChange={e => Setid(e.target.value)}/>
        <p className="error">{error}</p>
        <button className="pesquisar" type="button" onClick={SavePokemonsById}>Salvar</button>
        {status?
        <div>
            {!error?
            <div>
           <img className="imgweb" src="https://c.tenor.com/nOyZufiNO0EAAAAC/charmander-ok.gif" />
            </div>
            :
            <div>
                 <img className="imgweb" src="https://c.tenor.com/nOyZufiNO0EAAAAC/charmander-ok.gif" />
            </div>
            }
        </div>
        :null}
    </div>
 
       
          
        
    )




}

export default SavePokemonInDatabase;