package tk.gabrielpaim.catimages.ui.catImages

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tk.gabrielpaim.catimages.database.getDatabase
import tk.gabrielpaim.catimages.domain.ApiStatus
import tk.gabrielpaim.catimages.repository.ImagesRepository

class MainFeedViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * The current screen status.
     * Use [_status.value] to change the status.
     * [status] exposes the current value
     */
    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val database = getDatabase(application)
    private val imagesRepository = ImagesRepository(database)

    /**
     * Exposes a list of catImages
     */
    val catImages = imagesRepository.images

    init {
        viewModelScope.launch {
            try {
                _status.value = ApiStatus.LOADING
                imagesRepository.refreshImages()
                _status.value = ApiStatus.DONE
            } catch (t: Throwable) {
                _status.value = ApiStatus.ERROR
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainFeedViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainFeedViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}