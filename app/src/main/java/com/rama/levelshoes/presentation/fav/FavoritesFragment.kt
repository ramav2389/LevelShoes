
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rama.levelshoes.R
import com.rama.levelshoes.databinding.FragmentFavoritesBinding
import com.rama.levelshoes.util.Resource
import com.rama.levelshoes.util.gone
import com.rama.levelshoes.util.snack
import com.rama.levelshoes.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val binding by viewBinding(FragmentFavoritesBinding::bind)

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private val favoritesAdapter by lazy { FavoritesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        favoritesAdapter.onDeleteClick = {
            favoritesViewModel.deleteFromFavorites(it)
        }
    }

    private fun initObservers() {
        with(binding) {
            favoritesViewModel.favoriteList.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        progressBar.gone()
                        favoritesAdapter.updateList(it.data)
                        rvFavorites.adapter = favoritesAdapter
                    }
                    is Resource.Error -> {
                        progressBar.gone()
                        requireView().snack(it.throwable.message.toString())
                    }
                    is Resource.Loading -> progressBar.gone()
                }
            }
        }
    }
}