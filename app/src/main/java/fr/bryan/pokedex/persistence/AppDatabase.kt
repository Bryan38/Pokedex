

package fr.bryan.pokedex.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.bryan.pokedex.model.Pokemon
import fr.bryan.pokedex.model.PokemonInfo
import fr.bryan.pokedex.model.PokemonSpecies

@Database(entities = [Pokemon::class, PokemonInfo::class, PokemonSpecies::class], version = 3, exportSchema = true)
@TypeConverters(value = [TypeResponseConverter::class,AbilityResponseConverter::class,SpeciesResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

  abstract fun pokemonDao(): PokemonDao
  abstract fun pokemonInfoDao(): PokemonInfoDao
  abstract fun pokemonSpeciesDao(): PokemonSpeciesDao
}
