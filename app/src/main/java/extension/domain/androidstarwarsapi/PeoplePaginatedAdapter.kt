package extension.domain.androidstarwarsapi

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import extension.domain.androidstarwarsapi.data.models.PeopleModel
import kotlinx.android.synthetic.main.person_item.view.*

class PeoplePaginatedAdapter(val onItemSelected: ((PeopleModel?) -> Unit)? = null) : PagingDataAdapter<PeopleModel, RecyclerView.ViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PeopleModel>() {
            override fun areItemsTheSame(oldItem: PeopleModel, newItem: PeopleModel) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: PeopleModel, newItem: PeopleModel) =
                oldItem == newItem
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.getInstance(parent)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun getInstance(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.person_item, parent, false)
                return ViewHolder(view)
            }
        }

        fun bind(item: PeopleModel?) {
            itemView.apply {
                item?.let {
                    person_name.text = it.name
                }
            }
        }
    }
}