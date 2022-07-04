

package fr.bryan.pokedex.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.bryan.pokedex.persistence.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule { //persistence avec la base de données

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .addLast(KotlinJsonAdapterFactory())
      .build()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application,
    typeResponseConverter: TypeResponseConverter,
    abilityResponseConverter: AbilityResponseConverter,
    speciesResponseConverter: SpeciesResponseConverter
  ): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, "Pokedex.db")
      .fallbackToDestructiveMigration()
      .addTypeConverter(typeResponseConverter)
      .addTypeConverter(abilityResponseConverter)
      .addTypeConverter(speciesResponseConverter)
      .build()
  }

  @Provides
  @Singleton
  fun providePokemonDao(appDatabase: AppDatabase): PokemonDao {
    return appDatabase.pokemonDao()
  }

  @Provides
  @Singleton
  fun providePokemonInfoDao(appDatabase: AppDatabase): PokemonInfoDao {
    return appDatabase.pokemonInfoDao()
  }

  @Provides
  @Singleton
  fun providePokemonSpeciesDao(appDatabase: AppDatabase): PokemonSpeciesDao {
    return appDatabase.pokemonSpeciesDao()
  }

  @Provides
  @Singleton
  fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
    return TypeResponseConverter(moshi)
  }

  @Provides
  @Singleton
  fun provideAbilityResponseConverter(moshi: Moshi): AbilityResponseConverter {
    return AbilityResponseConverter(moshi)
  }

  @Provides
  @Singleton
  fun provideSpeciesResponseConverter(moshi: Moshi): SpeciesResponseConverter {
    return SpeciesResponseConverter(moshi)
  }
}
