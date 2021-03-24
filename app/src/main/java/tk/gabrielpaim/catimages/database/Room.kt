package tk.gabrielpaim.catimages.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImageDao {
    @Query("select * from databaseimage")
    fun getImages(): LiveData<List<DatabaseImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg images: DatabaseImage)
}

@Database(entities = [DatabaseImage::class], version = 1)
abstract class ImagesDatabase : RoomDatabase() {
    abstract val imageDao: ImageDao
}

private lateinit var INSTANCE: ImagesDatabase

fun getDatabase(context: Context): ImagesDatabase {
    synchronized(ImagesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ImagesDatabase::class.java,
                "images"
            ).build()
        }
    }
    return INSTANCE
}