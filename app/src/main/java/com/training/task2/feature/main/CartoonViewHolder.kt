package com.training.task2.feature.main

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.training.task2.R
import com.training.task2.model.Cartoon
import com.training.task2.util.isFemale
import com.training.task2.util.isMale

class CartoonViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val cardView: CardView = view.findViewById(R.id.cvCartoonDetail)
    private val image: ImageView = view.findViewById(R.id.ivCharacter)
    private val ivGender: ImageView = view.findViewById(R.id.ivGender)
    private val name: TextView = view.findViewById(R.id.tvName)
    private val status: TextView = view.findViewById(R.id.tvStatus)
    private val species: TextView = view.findViewById(R.id.tvSpecies)

    private var cartoon: Cartoon? = null

    init {
        view.setOnClickListener {
            cartoon?.url.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    fun bind(cartoon: Cartoon?) {
        if (cartoon == null) {
            cardView.isGone = true
        } else {
            showCartoonData(cartoon)
        }
    }

    private fun showCartoonData(cartoon: Cartoon) {
        this.cartoon = cartoon
        Glide.with(image)
            .load(cartoon.image)
            .circleCrop()
            .into(image)
        cartoon.gender.let {
            if(it.isMale()){
                ivGender.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_gender_male)
            }else if(it.isFemale()){
                ivGender.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_gender_female)
            }else{
                ivGender.background = ContextCompat.getDrawable(itemView.context, R.drawable.ic_gender_unknown)
            }
        }
        name.text = cartoon.name
        status.text = cartoon.status
        species.text = cartoon.species
    }

    companion object {
        fun create(parent: ViewGroup):CartoonViewHolder{
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cartoon_item, parent, false)
            return CartoonViewHolder(view)
        }
    }
}