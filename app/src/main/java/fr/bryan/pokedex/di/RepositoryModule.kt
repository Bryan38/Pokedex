

package fr.bryan.pokedex.di

import fr.bryan.pokedex.network.PokedexClient
import fr.bryan.pokedex.persistence.PokemonDao
import fr.bryan.pokedex.persistence.PokemonInfoDao
import fr.bryan.pokedex.repository.DetailRepository
import fr.bryan.pokedex.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import fr.bryan.pokedex.persistence.PokemonSpeciesDao
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    pokedexClient: PokedexClient,
    pokemonDao: PokemonDao,
    coroutineDispatcher: CoroutineDispatcher
  ): MainRepository {
    return MainRepository(pokedexClient, pokemonDao, coroutineDispatcher)
  }

  @Provides
  @ViewModelScoped
  fun provideDetailRepository(
    pokedexClient: PokedexClient,
    pokemonInfoDao: PokemonInfoDao,
    pokemonSpeciesDao: PokemonSpeciesDao,
    coroutineDispatcher: CoroutineDispatcher
  ): DetailRepository {
    return DetailRepository(pokedexClient, pokemonInfoDao, pokemonSpeciesDao, coroutineDispatcher)
  }
}
