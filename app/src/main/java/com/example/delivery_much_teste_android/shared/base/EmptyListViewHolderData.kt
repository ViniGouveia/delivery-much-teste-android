package com.example.delivery_much_teste_android.shared.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.delivery_much_teste_android.R
import com.google.android.material.textview.MaterialTextView
import kotlinx.android.synthetic.main.empty_list_layout.view.*

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
data class EmptyListViewHolderData(
    @DrawableRes var image: Int = 0,
    var body: String
)

class EmptyListViewHolder<ObjectType>(
    itemView: View,
    viewHolderData: EmptyListViewHolderData?
) : BaseViewHolder<ObjectType>(itemView) {

    private val ivImage: ImageView by lazy { itemView.empty_list_iv_image }
    private val tvBody: MaterialTextView by lazy { itemView.empty_list_tv_body }

    init {
        viewHolderData?.let {
            Glide.with(itemView)
                .load(it.image)
                .into(ivImage)

            tvBody.text = it.body
        }
    }

    override fun bind(item: ObjectType, position: Int) = Unit

    companion object {
        fun <ObjectType> newInstance(
            context: Context,
            parent: ViewGroup,
            viewHolderData: EmptyListViewHolderData?
        ) = LayoutInflater.from(context).inflate(
            R.layout.empty_list_layout,
            parent,
            false
        ).let { EmptyListViewHolder<ObjectType>(it, viewHolderData) }
    }
}
