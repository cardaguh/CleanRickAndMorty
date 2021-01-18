package co.cyclopsapps.cleanrickandmorty.character.list.adapater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.cyclopsapps.cleanrickandmorty.OnCustomClickListener
import co.cyclopsapps.cleanrickandmorty.R
import co.cyclopsapps.cleanrickandmorty.character.list.model.CharacterDataModel
import co.cyclopsapps.cleanrickandmorty.character.list.model.CharacterResponse
import com.bumptech.glide.Glide

class CharacterAdapter(
    private val onCustomClickListener1: OnCustomClickListener,
    private val onCustomClickListener2: (name: String, img: String) -> Unit

): RecyclerView.Adapter<CharacterAdapter.MyHolder>() {

    private var allCharacter: MutableList<CharacterDataModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.character_row, parent, false)
        return MyHolder(layoutView)
    }


    fun setCharacterData(response : CharacterResponse) {
        response.results?.let {
            allCharacter.clear()
            allCharacter.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val character = allCharacter[position]

        //Permite acceder a los componentes sin tener que estar escribiendo
        // category.name o category.img
        with(character) {
            Glide.with(holder.itemView.context).load(image).into(holder.imgRestaurant)
            holder.txtResturantTitle.text = name
        }


        holder.itemView.setOnClickListener {
            //onCustomClickListener1.showCategoryDetail(category.img)
            onCustomClickListener2(character.name, character.image)
        }

    }

    override fun getItemCount(): Int {
        return allCharacter.size
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgRestaurant: ImageView = itemView.findViewById(R.id.img_character)
        val txtResturantTitle: TextView = itemView.findViewById(R.id.txt_name)
    }


}