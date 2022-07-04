package fr.bryan.pokedex.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fr.bryan.pokedex.model.PokemonSpecies

@Dao
interface PokemonSpeciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonSpecies(pokemonSpecies: PokemonSpecies)

    @Query("SELECT * FROM PokemonSpecies WHERE name = :name_")
    suspend fun getPokemonSpecies(name_: String): PokemonSpecies?
}