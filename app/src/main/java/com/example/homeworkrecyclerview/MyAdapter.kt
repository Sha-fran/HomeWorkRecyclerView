package com.example.homeworkrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.example.homeworkrecyclerview.databinding.ItemBinding

class MyAdapter(var items: MutableList<Superhero> = mutableListOf()) : Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from((parent.context)),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val resources = holder.itemView.context.resources

        holder.name.text = String.format(resources.getString(R.string.name), items[position].name)
        holder.slug.text = String.format(resources.getString(R.string.slug), items[position].slug)
        holder.powerstatsdescription.text = String.format(
            resources.getString(R.string.powerstatsinit),
            items[position].powerstats?.intelligence,
            items[position].powerstats?.strength,
            items[position].powerstats?.speed,
            items[position].powerstats?.durability
        )
        holder.biography.text = String.format(
            resources.getString(R.string.biography),
            items[position].biography?.fullName,
            items[position].biography?.alterEgos,
            items[position].biography?.aliases,
            items[position].biography?.placeOfBirth,
            items[position].biography?.firstAppearance,
            items[position].biography?.publisher,
            items[position].biography?.alignment
        )
        holder.gender.text =
            String.format(resources.getString(R.string.gender, items[position].appearance?.gender))
        holder.race.text =
            String.format(resources.getString(R.string.race), items[position].appearance?.race)

        Glide.with(holder.itemView)
            .load(items[position].images?.lg)
            .into(holder.image)
    }

}

class MyViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
    val name: TextView = binding.textViewName
    val slug: TextView = binding.slug
    val powerstatsdescription: TextView = binding.powerstatsdescription
    val biography: TextView = binding.textViewBiography
    val image: ImageView = binding.imageViewPhoto
    val gender: TextView = binding.gender
    val race: TextView = binding.race
}
