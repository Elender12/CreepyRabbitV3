package com.ecirstea.creepyrabbit.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.data.model.multimedia.MultimediaData
import com.ecirstea.creepyrabbit.network.FirestoreHelper
import com.ecirstea.creepyrabbit.ui.view.PlayerActivity
import kotlinx.android.synthetic.main.item_audio_model_fav.view.*

class FavsRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<FavsRecyclerAdapter.MetadataHolder>() {
    private var multimediaDataList = mutableListOf<MultimediaData>()
    private val store = FirestoreHelper()
    fun setListData(data: MutableList<MultimediaData>){
        multimediaDataList= data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetadataHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return MetadataHolder(layoutInflater.inflate(R.layout.item_audio_model_fav, parent, false))
    }

    override fun onBindViewHolder(holder: MetadataHolder, position: Int) {
        holder.render(multimediaDataList[position])
    }

    override fun getItemCount(): Int {
        return if (multimediaDataList.size >0 ){
            multimediaDataList.size
        }else{
            0
        }
    }



    inner class MetadataHolder(val view: View): RecyclerView.ViewHolder(view){
        fun render(metadata: MultimediaData){
            Glide.with(context).load(metadata.imageUrl).into(view.item_image)
            view.item_title.text= metadata.title
           view.delBtn.setOnClickListener{
             store.deleteFavorite(metadata.mediaId)
               Toast.makeText(view.context, "Item deleted!", Toast.LENGTH_LONG).show()
           }

        }
    }




}