
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import com.canerture.e_commerce_app.presentation.detail.ProductDetailImagesAdapter
import com.rama.levelshoes.R
import com.rama.levelshoes.databinding.FragmentProductDetailBinding
import com.rama.levelshoes.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

@AndroidEntryPoint
class ProductDetailFragment : Fragment(R.layout.fragment_product_detail) {

    private val binding by viewBinding(FragmentProductDetailBinding::bind)

    private val detailViewModel: DetailViewModel by viewModels()

    private val productDetailImagesAdapter by lazy { ProductDetailImagesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        with(binding) {

            imgBack.setOnClickListener {
                findNavController().navigate(R.id.action_productFragment_to_productDetailFragment)
            }

            imgFavorite.setOnClickListener { detailViewModel.setFavoriteState() }

            btnAddToBag.setOnClickListener { detailViewModel.addToBag() }
        }
    }

    private fun initObservers() {

        with(binding) {

            with(detailViewModel) {

                product.observe(viewLifecycleOwner) {
                    productModel = it
                    val imageList = listOf(it.image)
                    productDetailImagesAdapter.updateList(imageList)
                    val compositePageTransformer = CompositePageTransformer()
                    compositePageTransformer.addTransformer { page, position ->
                        val r = 1 - abs(position)
                        page.scaleY = (0.85f + r * 0.15f)
                    }

                    viewPager.apply {
                        adapter = productDetailImagesAdapter
                        clipToPadding = false
                        clipChildren = false
                        offscreenPageLimit = 3
                        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                        setPageTransformer(compositePageTransformer)
                        currentItem = 1
                    }
                }

                crudResponse.observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Success -> {
                            progressBar.gone()
                            requireView().snack(it.data.title+"Added")
                        }
                        is Resource.Error -> {
                            progressBar.gone()
                            requireView().snack(it.throwable.message.toString())
                        }
                        is Resource.Loading -> progressBar.visible()
                    }
                }

                isFavorite.observe(viewLifecycleOwner) {
                    if (it) {
                        imgFavorite.setImageResource(R.drawable.ic_fav_selected)
                    } else {
                        imgFavorite.setImageResource(R.drawable.ic_fav)
                    }
                }
            }
        }
    }
}