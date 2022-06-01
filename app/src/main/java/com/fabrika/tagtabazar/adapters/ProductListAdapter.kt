package com.fabrika.tagtabazar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fabrika.tagtabazar.R
import com.fabrika.tagtabazar.model.Product
import kotlinx.android.synthetic.main.list_item_gridview.view.*


class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.VenueViewHolder>(){
    
    inner class VenueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        return VenueViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_gridview,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.itemView.apply{
            t_productName.text = product.Name
            view_space.visibility = if(position%2==0) View.GONE else View.VISIBLE
            Glide.with(context).load(product.Img_icon).into(i_image)
            t_price.text = "${product.Price}"
            setOnClickListener{
                onItemCLickListener?.let {
                    it(product)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemCLickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit){
        onItemCLickListener = listener
    }
}