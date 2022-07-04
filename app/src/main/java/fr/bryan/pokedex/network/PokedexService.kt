

package fr.bryan.pokedex.network

import fr.bryan.pokedex.model.PokemonInfo
import fr.bryan.pokedex.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import fr.bryan.pokedex.model.PokemonSpecies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexService {

  @GET("pokemon")
  suspend fun fetchPokemonList( //récupère la liste des pokémons
    @Query("limit") limit: Int = 20,
    @Query("offset") offset: Int = 0
  ): ApiResponse<PokemonResponse>

  @GET("pokemon/{name}") //récupère les infos
  suspend fun fetchPokemonInfo(@Path("name") name: String): ApiResponse<PokemonInfo> //récupère les details d'un pokémon

  @GET("pokemon-species/{name}") //récupère les species (nom en francais principalement)
  suspend fun fetchPokemonSpecies(@Path("name") name: String): ApiResponse<PokemonSpecies> //récupère les details d'un pokémon un peu plus poussé (utilisais pour afficher le nom en francais dans le détail
}
