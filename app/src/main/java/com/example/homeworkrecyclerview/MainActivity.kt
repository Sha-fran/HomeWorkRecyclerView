package com.example.homeworkrecyclerview

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworkrecyclerview.databinding.ActivityMainBinding
import com.google.gson.annotations.SerializedName
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = ApiClient.client.create(ApiInterface::class.java)
        api.getSuperHero()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val adapter = MyAdapter(it)
                binding.recyclerView.adapter = adapter
            },
                {
                    Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

data class Superhero(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("powerstats") val powerstats: Powerstats?,
    @SerializedName("appearance") val appearance: Appearance?,
    @SerializedName("biography") val biography: Biography?,
    @SerializedName("work") val work: Work?,
    @SerializedName("connections") val connections: Connections?,
    @SerializedName("images") val images: Images?
)

data class Powerstats(
    @SerializedName("intelligence") val intelligence: String?,
    @SerializedName("strength") val strength: String?,
    @SerializedName("speed") val speed: String?,
    @SerializedName("durability") val durability: String?,
    @SerializedName("power") val power: String?,
    @SerializedName("combat") val combat: String?
)

data class Appearance(
    @SerializedName("gender") val gender: String?,
    @SerializedName("race") val race: String?,
    @SerializedName("height") val height: ArrayList<String> = arrayListOf(),
    @SerializedName("weight") val weight: ArrayList<String> = arrayListOf(),
    @SerializedName("eyeColor") val eyeColor: String?,
    @SerializedName("hairColor") val hairColor: String?
)

data class Biography(
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("alterEgos") val alterEgos: String?,
    @SerializedName("aliases") val aliases: ArrayList<String> = arrayListOf(),
    @SerializedName("placeOfBirth") val placeOfBirth: String?,
    @SerializedName("firstAppearance") val firstAppearance: String?,
    @SerializedName("publisher") val publisher: String?,
    @SerializedName("alignment") val alignment: String?
)

data class Work(
    @SerializedName("occupation") val occupation: String?,
    @SerializedName("base") val base: String?
)

data class Connections(
    @SerializedName("groupAffiliation") val groupAffiliation: String? = null,
    @SerializedName("relatives") val relatives: String? = null
)

data class Images(
    @SerializedName("xs") val xs: String? = null,
    @SerializedName("sm") val sm: String? = null,
    @SerializedName("md") val md: String? = null,
    @SerializedName("lg") val lg: String? = null
)
