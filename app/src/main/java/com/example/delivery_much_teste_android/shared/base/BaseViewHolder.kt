package com.example.delivery_much_teste_android.shared.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
abstract class BaseViewHolder<ObjectType>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: ObjectType, position: Int)

    open fun bind() = Unit
}
