package com.me.booksdemoandroid.feature.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.me.booksdemoandroid.R
import com.me.booksdemoandroid.feature.home.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_book.view.*


class BookAdapter(
    private val mContext: Context,
    private val clickListener: (Book) -> Unit
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private var data: ArrayList<Book> = ArrayList()

    fun setData(newData: ArrayList<Book>) {
        data = newData
        notifyDataSetChanged()
    }

    fun addData(newData: ArrayList<Book>) {
        data.addAll(newData)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    class ItemViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val view: View = v

        init {
            //view.setOnClickListener()
        }

        fun bind(
            position: Int, book: Book, clickListener: (Book) -> Unit
        ) {
            itemView.setOnClickListener { clickListener(book) }
            itemView.txtViewTitle.text = book.title
            Picasso.get().load(book.preview).into(itemView.imageViewBook)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.row_book, parent, false)
        return BookAdapter.ItemViewHolder(view)
    }

    override fun onBindViewHolder(
        rawHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder,
        position: Int
    ) {
        val holder = rawHolder as ItemViewHolder
        holder.bind(position, data[position], clickListener)
        holder.view.tag = position
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}
