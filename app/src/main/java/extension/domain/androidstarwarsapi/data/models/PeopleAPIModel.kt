package extension.domain.androidstarwarsapi.data.models

import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
@Entity(tableName = "characters")
data class PeopleAPIModel(
    @SerializedName("count")
    @Expose
    var count: Int? = 0,

    @SerializedName("next")
    @Expose
    var next: String? = "",

    @SerializedName("mass")
    @Expose
    var mass: String? = "",//this is an INT being returned as a STRING by the API

    @SerializedName("results")
    @Expose
    var results: ArrayList<PeopleModel> = ArrayList()
) : Parcelable {

}