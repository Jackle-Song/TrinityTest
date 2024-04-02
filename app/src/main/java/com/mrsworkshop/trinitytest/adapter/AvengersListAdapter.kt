package com.mrsworkshop.trinitytest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mrsworkshop.trinitytest.R
import com.mrsworkshop.trinitytest.model.AvengersDataVO

class AvengersListAdapter(
    private var mContext : Context,
    private var avengersDataList : MutableList<AvengersDataVO>?,
    private var mListener : AvengersDetailInterface,
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface AvengersDetailInterface {
        fun viewAvengersDetail(avengersDataVO: AvengersDataVO)
    }

    class AvengersDetailsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val layoutAvengersDetail : LinearLayout = itemView.findViewById(R.id.layoutAvengersDetail)
        val imgAvengers : ImageView = itemView.findViewById(R.id.imgAvengers)
        val txtAvengersTitle : TextView = itemView.findViewById(R.id.txtAvengersTitle)
        val txtAvengersReviews : TextView = itemView.findViewById(R.id.txtAvengersReviews)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_avengers_list, parent, false)
        return AvengersDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return avengersDataList?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val avengersDetailItem = avengersDataList?.get(position)
        val avengersDetailsViewHolder = holder as AvengersDetailsViewHolder

        Glide.with(mContext)
            .load(avengersDetailItem?.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(avengersDetailsViewHolder.imgAvengers)

        avengersDetailsViewHolder.txtAvengersTitle.text = avengersDetailItem?.title
        avengersDetailsViewHolder.txtAvengersReviews.text = avengersDetailItem?.review

        avengersDetailsViewHolder.layoutAvengersDetail.setOnClickListener {
            if (avengersDetailItem != null) {
                mListener.viewAvengersDetail(avengersDetailItem)
            }
        }
    }

}