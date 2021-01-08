package extension.domain.androidstarwarsapi.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "characters")
data class PeopleModel(
    @ColumnInfo(collate = ColumnInfo.NOCASE)//use this for if implementing search from local database in future
    @SerializedName("name")
    @Expose
    var name: String? = "",

    @SerializedName("height")
    @Expose
    var height: String? = "",//this is an INT being returned as a STRING by the API

    @SerializedName("mass")
    @Expose
    var mass: String? = "",//this is an INT being returned as a STRING by the API

    @SerializedName("created")
    @Expose
    var created: Date? = null,//e.g. format 2014-12-10T16:20:44.310000Z

    @PrimaryKey
    @SerializedName("url")
    @Expose
    var url: String = ""//use this PrimaryKey as names can be the same (although close to impossible)
) : Parcelable {

}