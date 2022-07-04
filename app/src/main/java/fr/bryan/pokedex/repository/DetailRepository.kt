

package fr.bryan.pokedex.repository

import androidx.annotation.WorkerThread
import fr.bryan.pokedex.mapper.ErrorResponseMapper
import fr.bryan.pokedex.model.PokemonErrorResponse
import fr.bryan.pokedex.model.PokemonInfo
import fr.bryan.pokedex.network.PokedexClient
import fr.bryan.pokedex.persistence.PokemonInfoDao
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import fr.bryan.pokedex.model.PokemonSpecies
import fr.bryan.pokedex.persistence.PokemonSpeciesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class DetailRepository @Inject constructor(
  private val pokedexClient: PokedexClient,
  private val pokemonInfoDao: PokemonInfoDao,
  private val pokemonSpeciesDao: PokemonSpeciesDao,
  private val ioDispatcher: CoroutineDispatcher
) : Repository {

  @WorkerThread
  fun fetchPokemonInfo(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow<PokemonInfo?> {
    val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
    if (pokemonInfo == null) {
      val response = pokedexClient.fetchPokemonInfo(name = name)
      response.suspendOnSuccess {
        pokemonInfoDao.insertPokemonInfo(data)
        emit(data)
      }
        // handles the case when the API request gets an error response.
        // e.g., internal server error.
        .onError {
          /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
          map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
        // handles the case when the API request gets an exception response.
        // e.g., network connection error.
        .onException { onError(message) }
    } else {
      emit(pokemonInfo)
    }
  }.onCompletion { onComplete() }.flowOn(ioDispatcher)

  @WorkerThread
  fun fetchPokemonSpecies(
    name: String,
    onComplete: () -> Unit,
    onError: (String?) -> Unit
  ) = flow<PokemonSpecies?> {
    val pokemonSpecies = pokemonSpeciesDao.getPokemonSpecies(name)
    if (pokemonSpecies == null) {
      val response = pokedexClient.fetchPokemonSpecies(name = name)
      response.suspendOnSuccess {
        pokemonSpeciesDao.insertPokemonSpecies(data)
        emit(data)
      }
        // handles the case when the API request gets an error response.
        // e.g., internal server error.
        .onError {
          /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
          map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
        // handles the case when the API request gets an exception response.
        // e.g., network connection error.
        .onException { onError(message) }
    } else {
      emit(pokemonSpecies)
    }
  }.onCompletion { onComplete() }.flowOn(ioDispatcher)
}
