package tk.gabrielpaim.catimages.ui.catImages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import tk.gabrielpaim.catimages.databinding.FragmentMainFeedBinding

class MainFeedFragment : Fragment() {
    private val viewModel: MainFeedViewModel by lazy {
        ViewModelProvider(this).get(MainFeedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainFeedBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root
    }
}