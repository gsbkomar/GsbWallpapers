package gsbkomar.gsbwallpapers.screens.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gsbkomar.data.dto.db.PhotoDbDto
import gsbkomar.gsbwallpapers.databinding.PhotoItemBinding
import gsbkomar.gsbwallpapers.screens.favorite.FavoriteFragment
import javax.inject.Inject

class FavoriteListAdapter @Inject constructor(
    private val context: FavoriteFragment,
    private val onClick: (item: String) -> Unit
) : ListAdapter<PhotoDbDto, FavoriteViewHolder>(DifUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            Glide.with(context.requireContext())
                .load(item.url)
                .into(photo)
            root.setOnClickListener { onClick(item.url) }
        }
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<PhotoDbDto>() {
    override fun areItemsTheSame(
        oldItem: PhotoDbDto,
        newItem: PhotoDbDto
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PhotoDbDto,
        newItem: PhotoDbDto
    ): Boolean {
        return oldItem == newItem
    }
}

class FavoriteViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)