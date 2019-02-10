package com.babenko.datafields.screen.feature.images.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.babenko.datafields.R
import com.babenko.datafields.model.entity.ImageItem

class ImagesAdapter(context: Context, private val mCallback: ImagesListener) :
    RecyclerView.Adapter<ImagesAdapter.ImagesItemHolder>() {
    private val inflater = LayoutInflater.from(context)

    private val imageList = ArrayList<ImageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesItemHolder {
        val convertView = inflater.inflate(R.layout.item_image, parent, false)
        return ImagesItemHolder(convertView)
    }

    override fun onBindViewHolder(holder: ImagesItemHolder, position: Int) {
        holder.updateView(imageList[position])
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun replaceImages(images: List<ImageItem>) {
        imageList.addAll(images)
        notifyDataSetChanged()
    }

    inner class ImagesItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val pictureElementId = view.findViewById<TextView>(R.id.pictureElementId)
        private val pictureElementTitle = view.findViewById<TextView>(R.id.pictureElementTitle)
        private val pictureElementImageView = view.findViewById<ImageView>(R.id.pictureElementImageView)

        fun updateView(item: ImageItem) {
//            Picasso.get().load(item.thumbnailUrl).error(R.drawable.question_mark)
//                .placeholder(R.drawable.question_mark).into(pictureElementImageView)
            pictureElementId.text = item.id.toString()
            pictureElementTitle.text = item.title
            pictureElementImageView.rootView.setOnClickListener { v -> mCallback.itemClicked(item.url) }
        }

    }
}
