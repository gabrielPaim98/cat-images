package tk.gabrielpaim.catimages.ui.catImages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tk.gabrielpaim.catimages.model.ApiStatus
import tk.gabrielpaim.catimages.model.Image
import tk.gabrielpaim.catimages.service.ImgurApi

class MainFeedViewModel : ViewModel() {
    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _cats = MutableLiveData<List<Image>>()

    val cats: LiveData<List<Image>>
        get() = _cats

    init {
        getCatImages()
    }

    private fun getCatImages() {
        viewModelScope.launch {
            try {
                val catImages = ArrayList<Image>()
                _status.value = ApiStatus.LOADING
                val responseData = ImgurApi.service.searchFor().data
                for (data in responseData) {
                    data.images?.let {
                        catImages.addAll(it)
                    }
                }
                if (catImages.isNotEmpty()) {
                    _cats.value = catImages
                }
                _status.value = ApiStatus.DONE
            } catch (t: Throwable) {
                _status.value = ApiStatus.ERROR
                _cats.value = ArrayList()
            }
        }
    }
}