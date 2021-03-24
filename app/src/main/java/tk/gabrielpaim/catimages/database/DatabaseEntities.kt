package tk.gabrielpaim.catimages.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import tk.gabrielpaim.catimages.domain.Image

@Entity
data class DatabaseImage constructor(
    @PrimaryKey
    val id: String,
    val imgUrl: String
)

fun List<DatabaseImage>.asDomainModel(): List<Image> {
    return map {
        Image(
            id = it.id,
            imgUrl = it.imgUrl
        )
    }
}