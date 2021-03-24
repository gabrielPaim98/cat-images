package tk.gabrielpaim.catimages.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import tk.gabrielpaim.catimages.database.DatabaseImage
import tk.gabrielpaim.catimages.database.ImagesDatabase
import tk.gabrielpaim.catimages.database.asDomainModel
import tk.gabrielpaim.catimages.domain.Image
import tk.gabrielpaim.catimages.network.ImgurApi
import tk.gabrielpaim.catimages.network.asDatabaseModel

class ImagesRepository(private val database: ImagesDatabase) {

    /**
     * A list of images that can be shown on the screen.
     */
    val images: LiveData<List<Image>> = Transformations.map(database.imageDao.getImages()) {
        it.asDomainModel()
    }

    /**
     * Refresh the images in the offline cache.
     * This fetches the imgur service and updates the cache
     * using the single source of truth principle.
     *
     * To actually load the images for use [images]
     */
    suspend fun refreshImages() {
        withContext(Dispatchers.IO) {
            val dbImages = ArrayList<DatabaseImage>()
            val responseData = ImgurApi.service.searchFor().data
            for (data in responseData) {
                dbImages.addAll(data.asDatabaseModel())
            }
            database.imageDao.insertAll(*dbImages.toTypedArray())
        }
    }
}