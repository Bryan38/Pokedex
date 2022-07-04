

package fr.bryan.pokedex.utils

import fr.bryan.pokedex.R

object PokemonTypeUtils {

  fun getTypeColor(type: String): Int { //fait correspondre les type à une couleur
    return when (type) {
      "fighting" -> R.color.combat
      "flying" -> R.color.vol
      "poison" -> R.color.poison
      "ground" -> R.color.sol
      "rock" -> R.color.roche
      "bug" -> R.color.insecte
      "ghost" -> R.color.spectre
      "steel" -> R.color.acier
      "fire" -> R.color.feu
      "water" -> R.color.eau
      "grass" -> R.color.plante
      "electric" -> R.color.electrik
      "psychic" -> R.color.psy
      "ice" -> R.color.glace
      "dragon" -> R.color.dragon
      "fairy" -> R.color.fee
      "dark" -> R.color.tenebres
      else -> R.color.gray_21
    }
  }

  fun getNameColor(type: String): String { //avoir les types en francais, méthode un peu border
    return when (type) {
      "fighting" -> "Combat"
      "flying" -> "Vol"
      "poison" -> "Poison"
      "ground" -> "Sol"
      "rock" -> "Roche"
      "bug" -> "Insecte"
      "ghost" -> "Spectre"
      "steel" -> "Acier"
      "fire" -> "Feu"
      "water" -> "Eau"
      "grass" -> "Plante"
      "electric" -> "Electrik"
      "psychic" -> "Psy"
      "ice" -> "Glace"
      "dragon" -> "Dragon"
      "fairy" -> "Fee"
      "dark" -> "Tenebres"
      else -> "Normal"
    }
  }
}
