package com.example.delivery_much_teste_android.ui.repositories.adapter

import android.content.Context
import android.view.View
import com.example.delivery_much_teste_android.R
import com.example.delivery_much_teste_android.shared.base.BaseAdapter
import com.example.delivery_much_teste_android.shared.base.BaseViewHolder
import com.example.delivery_much_teste_android.shared.model.Repository
import kotlinx.android.synthetic.main.repositories_list_item_layout.view.*

/**
 * @author Vinicius Gouveia on 29/05/2021
 */
class RepositoriesAdapter(context: Context) : BaseAdapter<Repository>(context) {

    lateinit var callback: ((Repository) -> Unit)

    override fun getLayoutResId(viewType: Int) = R.layout.repositories_list_item_layout

    override fun getViewHolder(itemView: View, viewType: Int) =
        RepositoriesViewHolder(itemView, callback)

    inner class RepositoriesViewHolder(
        itemView: View,
        private val callback: ((Repository) -> Unit)
    ) : BaseViewHolder<Repository>(itemView) {
        override fun bind(item: Repository, position: Int) {
            with(itemView) {
                item.apply {
                    repositories_list_item_tv_name.text = repositoryName
                    repositories_list_item_tv_owner.text = repositoryOwnerUsername
                }

                setOnClickListener { callback.invoke(item) }
            }
        }
    }
}