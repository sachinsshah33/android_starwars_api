package extension.domain.androidstarwarsapi.data.people

import androidx.paging.PagingSource
import androidx.room.*
import extension.domain.androidstarwarsapi.data.models.PeopleModel


@Dao
interface PeopleDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(models: List<PeopleModel>)
    @Update
    fun update(models: List<PeopleModel>)
    @Delete
    fun delete(models: List<PeopleModel>)
    @Query("DELETE FROM characters")
    fun deleteAll()

    @Query("SELECT * FROM characters")
    fun getPeopleFromLocalPaginated(): PagingSource<Int, PeopleModel>
}
