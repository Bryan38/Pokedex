

package fr.bryan.pokedex.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import fr.bryan.pokedex.model.PokemonInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject

@ProvidedTypeConverter
class AbilityResponseConverter @Inject constructor(
  private val moshi: Moshi
) {

  @TypeConverter
  fun fromString(value: String): List<PokemonInfo.AbilityResponse>? {
    val listType = Types.newParameterizedType(List::class.java, PokemonInfo.AbilityResponse::class.java)
    val adapter: JsonAdapter<List<PokemonInfo.AbilityResponse>> = moshi.adapter(listType)
    return adapter.fromJson(value)
  }

  @TypeConverter
  fun fromInfoAbility(type: List<PokemonInfo.AbilityResponse>?): String {
    val listType = Types.newParameterizedType(List::class.java, PokemonInfo.AbilityResponse::class.java)
    val adapter: JsonAdapter<List<PokemonInfo.AbilityResponse>> = moshi.adapter(listType)
    return adapter.toJson(type)
  }
}
