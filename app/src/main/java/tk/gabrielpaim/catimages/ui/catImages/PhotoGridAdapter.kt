package tk.gabrielpaim.catimages.ui.catImages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tk.gabrielpaim.catimages.databinding.GridViewItemBinding
import tk.gabrielpaim.catimages.model.Image

class PhotoGridAdapter : ListAdapter<Image, PhotoGridAdapter.CatImageViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class CatImageViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(catImage: Image) {
            binding.catImage = catImage
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatImageViewHolder {
        return CatImageViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CatImageViewHolder, position: Int) {
        val catImage = getItem(position)
        holder.bind(catImage)
    }
}