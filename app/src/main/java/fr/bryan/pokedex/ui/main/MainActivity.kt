

package fr.bryan.pokedex.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import com.skydoves.bindables.BindingActivity
import fr.bryan.pokedex.R
import fr.bryan.pokedex.databinding.ActivityMainBinding
import fr.bryan.pokedex.ui.adapter.PokemonAdapter
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

  @get:VisibleForTesting
  internal val viewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    onTransformationStartContainer()
    super.onCreate(savedInstanceState)
    binding {
      adapter = PokemonAdapter()
      vm = viewModel
    }
  }
}
