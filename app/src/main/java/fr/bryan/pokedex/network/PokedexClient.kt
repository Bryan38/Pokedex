

package fr.bryan.pokedex.network

import fr.bryan.pokedex.model.PokemonInfo
import fr.bryan.pokedex.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import fr.bryan.pokedex.model.PokemonSpecies
import javax.inject.Inject

class PokedexClient @Inject constructor(
  private val pokedexService: PokedexService
) {

  suspend fun fetchPokemonList(
    page: Int
  ): ApiResponse<PokemonResponse> =
    pokedexService.fetchPokemonList(
      limit = PAGING_SIZE,
      offset = page * PAGING_SIZE
    )

  suspend fun fetchPokemonInfo( //récupère les infos
    name: String
  ): ApiResponse<PokemonInfo> =
    pokedexService.fetchPokemonInfo(
      name = name
    )

  suspend fun fetchPokemonSpecies( //récupère les species (utilisé pour les noms en francais)
    name: String
  ): ApiResponse<PokemonSpecies> =
    pokedexService.fetchPokemonSpecies(
      name = name
    )

  companion object {
    private const val PAGING_SIZE = 20
  }
}
