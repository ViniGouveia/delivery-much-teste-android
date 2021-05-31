package com.example.delivery_much_teste_android.shared.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * @author Vinicius Gouveia on 26/05/2021
 */
@Suppress("TooManyFunctions")
abstract class BaseAdapter<ObjectType>(
    private var context: Context
) : RecyclerView.Adapter<BaseViewHolder<ObjectType>>() {

    open var genericItemClickDelay = ITEM_CLICK_DELAY
    open var hasAnotherEmptyList = false

    var genericEmptyListData: EmptyListViewHolderData? = null

    var genericCallback: (ObjectType) -> Unit = {}
    var genericDisposable: CompositeDisposable? = null
        set(value) = value?.let {
            genericSubject = PublishSubject.create<() -> Unit>().apply {
                throttleFirst(genericItemClickDelay, TimeUnit.MILLISECONDS)
                    .subscribe { callback -> callback() }
                    .addTo(it)
            }
            field = it
        } ?: let {
            genericSubject = null
            field = null
        }

    protected var genericSubject: PublishSubject<() -> Unit>? = null
    protected var dataSet = listOf<ObjectType>()

    @LayoutRes
    abstract fun getLayoutResId(viewType: Int): Int

    abstract fun getViewHolder(itemView: View, viewType: Int): BaseViewHolder<ObjectType>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == VIEW_TYPE_EMPTY) {
            getEmptyViewHolder(parent)
        } else {
            getViewHolder(inflate(getLayoutResId(viewType), parent), viewType)
        }

    override fun getItemViewType(position: Int) =
        if (dataSet.isEmpty()) {
            VIEW_TYPE_EMPTY
        } else {
            super.getItemViewType(position)
        }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ObjectType>,
        position: Int
    ) = dataSet.getOrNull(position)
        ?.let { holder.bind(it, position) }
        ?: holder.bind()

    override fun getItemCount() = if (dataSet.isEmpty() && genericEmptyListData != null) {
        MINIMUM_EMPTY_LIST
    } else {
        dataSet.size
    }

    open fun setList(
        newDataSet: List<ObjectType>,
        diffCallback: DiffUtil.Callback = BaseDiffCallback(dataSet, newDataSet)
    ) = if (dataSet.isEmpty()) {
        dataSet = newDataSet
        notifyDataSetChanged()
    } else {
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataSet = newDataSet
        diffResult.dispatchUpdatesTo(this)
    }

    open fun getEmptyViewHolder(parent: ViewGroup): BaseViewHolder<ObjectType> =
        EmptyListViewHolder.newInstance(context, parent, genericEmptyListData)

    fun getList(): List<ObjectType> = dataSet

    fun getItem(position: Int): ObjectType = dataSet[position]

    fun isEmpty(): Boolean = dataSet.isEmpty()

    fun isTheLastItem(position: Int): Boolean = position == dataSet.size - 1

    fun clear() = listOf<ObjectType>().let { dataSet = it }

    fun addList(list: List<ObjectType>) = setList(
        dataSet.toMutableList().apply {
            addAll(list)
        }
    )

    @NonNull
    protected fun inflate(
        @LayoutRes layoutRes: Int,
        @Nullable parent: ViewGroup
    ): View = LayoutInflater.from(context).inflate(layoutRes, parent, false)

    class BaseDiffCallback<ObjectType>(
        private val oldList: List<ObjectType>,
        private val newList
        : List<ObjectType>
    ) : DiffUtil.Callback() {

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            areItemsTheSame(oldItemPosition, newItemPosition)
    }

    private companion object {
        private const val ITEM_CLICK_DELAY = 1000L
        private const val MINIMUM_EMPTY_LIST = 1
        private const val VIEW_TYPE_EMPTY = 1000
    }
}
