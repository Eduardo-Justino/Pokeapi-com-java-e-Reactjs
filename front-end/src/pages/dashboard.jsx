import Header from "../components/header"
import GetOnePokemonInWeb from "../components/getOnePokemonInWeb"
import SavePokemonInDatabase from "../components/savePokemonInDatabase"
import GetAllPokemonDB from "../components/getAllPokemonDB"
import styles from './styles.css'
import { useState, useEffect } from "react"
import Cabecalho from "../components/cabecalho"
import DeletePokemonIdDb from "../components/deletePokemonIdDB"

const Dashboard = () => {

    const [showSave, setShowSave] = useState(false);
    const [showSearch, setShowSearch] = useState(false)
    const [showDelete, setShowDelete] = useState(false)

   

   

    const handlerSearch = () => {
        setShowSearch(true);
        setShowSave(false)
        setShowDelete(false)
        return
    }

    const handlerSave = () => {

        setShowSave(true)
        setShowSearch(false)
        setShowDelete(false)
        return

    }

    const handlerDelete = () => {
        setShowDelete(true)
        setShowSave(false)
        setShowSearch(false)
        return
    }


    return (
        <div>
            <div>
                <Header />
                <div className="center">
                    <div className="dashboard">
                        <button onClick={handlerSearch} className="pesquisar">Buscar Pokemon</button>
                    </div>
                    <div className="dashboard">
                        <button onClick={handlerSave} className="pesquisar">Salvar Pokemon</button>
                    </div>
                    <div className="dashboard">
                        <button onClick={handlerDelete} className="pesquisar">Deletar Pokemon</button>
                    </div>
                    <div>
                        {showSave ?
                            <div>
                                <div className="centerPesquisa">
                                    <SavePokemonInDatabase />
                                </div>
                            </div>
                            : null}
                    </div>
                    {showSearch ?
                        <div className="centerPesquisa">
                            <GetOnePokemonInWeb />
                        </div>
                        : null}
                        <div>
                        {showDelete?
                        <div className="centerPesquisa">
                        <DeletePokemonIdDb />
                         </div>
                         :null}
                         </div>
                    <div className="center">
                        <Cabecalho />
                        <GetAllPokemonDB />

                    </div>
                 
                </div>
            </div>
        </div>



    )

}

export default Dashboard