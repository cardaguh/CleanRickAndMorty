package co.cyclopsapps.cleanrickandmorty

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RestaurantAdapter(
    private val onCustomClickListener1: OnCustomClickListener,
    private val onCustomClickListener2: (img: String) -> Unit

): RecyclerView.Adapter<RestaurantAdapter.MyHolder>() {

    private var  categoryList: MutableList<CharacterData> = mutableListOf()
    private var restaurant: Character? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.character_row, parent, false)
        return MyHolder(layoutView)
    }


    fun setRestaurantData(response : CharacterResponse) {
        restaurant = response.company

        response?.category?.let {
            categoryList.clear()
            categoryList.addAll(it)
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val category = categoryList[position]

        //Permite acceder a los componentes sin tener que estar escribiendo
        // category.name o category.img
        with(category) {
            Glide.with(holder.itemView.context).load(img).into(holder.imgRestaurant)
            holder.txtResturantTitle.text = name
        }


        holder.itemView.setOnClickListener {
            //onCustomClickListener1.showCategoryDetail(category.img)
            onCustomClickListener2(category.img)
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgRestaurant: ImageView = itemView.findViewById(R.id.img_character)
        val txtResturantTitle: TextView = itemView.findViewById(R.id.txt_name)
    }


}