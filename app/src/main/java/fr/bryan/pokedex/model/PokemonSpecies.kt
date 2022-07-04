package fr.bryan.pokedex.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class PokemonSpecies(
    @field:Json(name = "id") @PrimaryKey val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "names") val names: List<NameResponse>
) {

    fun getFrench(): String {
        var nameFrench = ""
        for (name in names) {
            if (name.language.name == "fr"){
                nameFrench = name.name
            }
        }
        return nameFrench
    }

    @JsonClass(generateAdapter = true)
    data class NameResponse(
        @field:Json(name = "name") val name: String,
        @field:Json(name = "language") val language: Language
    )

    @JsonClass(generateAdapter = true)
    data class Language(
        @field:Json(name = "name") val name: String
    )

}
