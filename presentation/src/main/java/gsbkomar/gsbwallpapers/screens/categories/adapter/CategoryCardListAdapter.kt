package gsbkomar.gsbwallpapers.screens.categories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gsbkomar.gsbwallpapers.databinding.CategoriesItemBinding
import gsbkomar.gsbwallpapers.screens.categories.CategoriesFragment
import gsbkomar.gsbwallpapers.screens.categories.models.CategoryCard
import java.util.Locale
import javax.inject.Inject

class CategoryCardListAdapter @Inject constructor(
    private val context: CategoriesFragment,
    private val onClick: (item: String) -> Unit
) : ListAdapter<CategoryCard, CategoryCardViewHolder>(DifUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryCardViewHolder {
        return CategoryCardViewHolder(
            CategoriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryCardViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            Glide.with(context.requireContext())
                .load(item.url)
                .into(photo)
            category.text = item.name

            root.setOnClickListener { onClick(item.name.lowercase(Locale.ROOT)) }
        }
    }
}

class DifUtilCallBack : DiffUtil.ItemCallback<CategoryCard>() {
    override fun areItemsTheSame(
        oldItem: CategoryCard,
        newItem: CategoryCard
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: CategoryCard,
        newItem: CategoryCard
    ): Boolean {
        return oldItem == newItem
    }
}

class CategoryCardViewHolder(val binding: CategoriesItemBinding) : RecyclerView.ViewHolder(binding.root)