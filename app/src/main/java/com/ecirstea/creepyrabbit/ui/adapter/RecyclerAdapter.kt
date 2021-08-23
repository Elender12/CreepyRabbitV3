package com.ecirstea.creepyrabbit.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecirstea.creepyrabbit.R
import com.ecirstea.creepyrabbit.data.model.multimedia.MultimediaData
import com.ecirstea.creepyrabbit.network.FirestoreHelper
import com.ecirstea.creepyrabbit.ui.view.PlayerActivity
//import kotlinx.android.synthetic.main.item_audio_list.view.*
import kotlinx.android.synthetic.main.item_audio_model.view.*

class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.MetadataHolder>() {
    private var multimediaDataList = mutableListOf<MultimediaData>()
    private val store = FirestoreHelper()
    fun setListData(data: MutableList<MultimediaData>){
        multimediaDataList= data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MetadataHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return MetadataHolder(layoutInflater.inflate(R.layout.item_audio_model, parent, false))
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
          /*  view.tvAuthor.text = metadata.author
            view.tvNarrator.text = metadata.narrator
            view.tvTitle.text= metadata.title*/
         //   view.item_details_author.text = context.resources.getString(R.string.custom_string, metadata.author, metadata.narrator)
           // view.item_details_narrator.text = metadata.narrator
            view.item_title.text= metadata.title
            //view.tvCategory.text = metadata.category
            view.setOnClickListener{
                val intent = Intent(context, PlayerActivity::class.java).apply {
                    putExtra("audioURL", metadata.audioUrl)
                    putExtra("title",metadata.title)
                }
                context.startActivity(intent)
            }
           view.favsBtn.setOnClickListener{
             store.saveFavorites(metadata.mediaId)
               view.favsBtn.text = context.getString(R.string.added)
           }

        }
    }




}