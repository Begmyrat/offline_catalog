package com.fabrika.tagtabazar.adapters

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.fabrika.tagtabazar.R
import com.fabrika.tagtabazar.model.Category
import com.fabrika.tagtabazar.model.Product
import kotlinx.android.synthetic.main.list_item_magazine.view.*


class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.VenueViewHolder>(){
    
    inner class VenueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    var currentIndex = 0

    private val differCallback = object : DiffUtil.ItemCallback<Category>(){
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        return VenueViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_magazine,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.itemView.apply{
            t_title.text = category.Name
            view.visibility = if(position==currentIndex) View.VISIBLE else View.INVISIBLE

            if(currentIndex==position){
                t_title.setTextColor(ContextCompat.getColor(context, R.color.colorPink))
                t_title.setTypeface(null, Typeface.BOLD)
                t_title.setShadowLayer(1f, -1f, 1f, R.color.colorPink)
            }
            else{
                t_title.setTextColor(ContextCompat.getColor(context, R.color.black))
                t_title.setTypeface(null, Typeface.NORMAL)
                t_title.setShadowLayer(0f, -1f, 1f, R.color.white)
            }
            setOnClickListener{
                onItemCLickListener?.let {
                    currentIndex = position
                    it(category)
                    notifyDataSetChanged()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemCLickListener: ((Category) -> Unit)? = null

    fun setOnItemClickListener(listener: (Category) -> Unit){
        onItemCLickListener = listener
    }
}