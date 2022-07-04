package fr.bryan.pokedex.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import fr.bryan.pokedex.model.PokemonSpecies
import javax.inject.Inject

@ProvidedTypeConverter
class SpeciesResponseConverter  @Inject constructor(
    private val moshi: Moshi
) {
    @TypeConverter
    fun fromString(value: String): List<PokemonSpecies.NameResponse>? {
        val listSpecies =
            Types.newParameterizedType(List::class.java, PokemonSpecies.NameResponse::class.java)
        val adapter: JsonAdapter<List<PokemonSpecies.NameResponse>> = moshi.adapter(listSpecies)
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromInfoSpecies(type: List<PokemonSpecies.NameResponse>?): String {
        val listSpecies =
            Types.newParameterizedType(List::class.java, PokemonSpecies.NameResponse::class.java)
        val adapter: JsonAdapter<List<PokemonSpecies.NameResponse>> = moshi.adapter(listSpecies)
        return adapter.toJson(type)
    }
}