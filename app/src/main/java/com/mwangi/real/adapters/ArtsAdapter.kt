package com.mwangi.real.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.signature.ObjectKey
import com.mwangi.real.R
import com.mwangi.real.models.Arts
class ArtsAdapter(private val context: Context, private var data: MutableList<Arts>) : BaseAdapter() {

    private class ViewHolder(row: View?) {
        val imgArt: ImageView = row?.findViewById(R.id.mImgArt) as ImageView
        val txtUserDescription: TextView = row?.findViewById(R.id.User_description) as TextView
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.art_layout, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val item: Arts = getItem(position) as Arts

        Glide.with(context)
            .load(item.imageUrl)
            .signature(ObjectKey(item.imageUrl))
            .transform(CenterCrop(), RoundedCorners(16))
            .into(viewHolder.imgArt)

        viewHolder.txtUserDescription.text = item.userDescription

        return view
    }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    fun setSearchOperation(newList: List<Arts>) {
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }
}
