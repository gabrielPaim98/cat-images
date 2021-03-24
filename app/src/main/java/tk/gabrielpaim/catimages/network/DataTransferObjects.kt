package tk.gabrielpaim.catimages.network

import tk.gabrielpaim.catimages.database.DatabaseImage
import tk.gabrielpaim.catimages.domain.Image

/**
 * Objects responsible for parsing responses from the server.
 * Should be formatted to domain objects before using them.
 */

data class NetworkApiResponse(
    val data: List<NetworkApiResponseData>,
    val status: Int,
    val success: Boolean
)

data class NetworkApiResponseData(
    val id: String,
    val images: List<NetworkImage>?
)

data class NetworkImage(
    val id: String,
    val link: String
)


/**
 * Convert Network results to domain objects
 */

fun NetworkApiResponseData.asDomainModel(): List<Image> {
    return images?.let { images ->
        images.map {
            Image(
                id = it.id,
                imgUrl = it.link
            )
        }
    } ?: ArrayList()
}

/**
 * Convert Network results to database objects
 */

fun NetworkApiResponseData.asDatabaseModel(): Array<DatabaseImage> {
    return images?.map {
        DatabaseImage(
            id = it.id,
            imgUrl = it.link
        )
    }?.toTypedArray() ?: emptyArray()
}