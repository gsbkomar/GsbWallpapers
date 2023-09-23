package gsbkomar.gsbwallpapers.screens.photo_list_category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gsbkomar.data.dto.tree.TagsDto
import gsbkomar.gsbwallpapers.databinding.PhotoItemBinding
import gsbkomar.gsbwallpapers.screens.photo_list_category.PhotoListFragment
import javax.inject.Inject

class PhotoListAdapter @Inject constructor(
    private val context: PhotoListFragment,
    private val onClick: (item: String) -> Unit
) : ListAdapter<TagsDto, PhotoListViewHolder>(DifUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListViewHolder {
        return PhotoListViewHolder(
            PhotoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoListViewHolder, position: Int) {
        val item = getItem(position)
        val photoItem = item.source?.coverPhoto?.urls?.raw

        with(holder.binding) {
            Glide.with(context.requireContext())
                .load(photoItem)
                .into(photo)
            root.setOnClickListener { onClick(photoItem!!) }
        }
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<TagsDto>() {
    override fun areItemsTheSame(
        oldItem: TagsDto,
        newItem: TagsDto
    ): Boolean {
        return oldItem.source == newItem.source
    }

    override fun areContentsTheSame(
        oldItem: TagsDto,
        newItem: TagsDto
    ): Boolean {
        return oldItem == newItem
    }
}

class PhotoListViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)