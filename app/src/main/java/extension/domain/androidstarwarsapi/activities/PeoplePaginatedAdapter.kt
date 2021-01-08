package extension.domain.androidstarwarsapi.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import extension.domain.androidstarwarsapi.R
import extension.domain.androidstarwarsapi.data.models.PeopleModel
import kotlinx.android.synthetic.main.person_item.view.*

class PeoplePaginatedAdapter(val onItemSelected: ((PeopleModel?) -> Unit)? = null) : PagingDataAdapter<PeopleModel, PeoplePaginatedAdapter.ViewHolder>(
    COMPARATOR
) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PeopleModel>() {
            override fun areItemsTheSame(oldItem: PeopleModel, newItem: PeopleModel) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: PeopleModel, newItem: PeopleModel) =
                oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.person_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBindView(data: PeopleModel, position: Int) {
            itemView.apply {
                data.let {
                    person_name.text = it.name
                    person_name.setOnClickListener {_->
                        onItemSelected?.invoke(it)
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBindView(it, position)
        }
    }
}