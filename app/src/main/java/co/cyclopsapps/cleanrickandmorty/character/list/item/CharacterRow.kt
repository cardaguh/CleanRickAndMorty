package co.cyclopsapps.cleanrickandmorty.character.list.item

import android.view.View
import co.cyclopsapps.cleanrickandmorty.R
import co.cyclopsapps.cleanrickandmorty.character.list.model.CharacterDataModel
import co.cyclopsapps.cleanrickandmorty.databinding.CharacterRowBinding
import com.bumptech.glide.Glide
import com.xwray.groupie.viewbinding.BindableItem

class CharacterRow(
    private val character: CharacterDataModel,
    private val onCustomClickListener: (name: String, img: String) -> Unit
) : BindableItem<CharacterRowBinding>() {

    override fun bind(viewBinding: CharacterRowBinding, position: Int) {
        with(character) {
            Glide.with(viewBinding.root.context).load(image).into(viewBinding.imgCharacter)
            viewBinding.txtName.text = name
            viewBinding.txtDescription.text = species
        }


        viewBinding.root.setOnClickListener {
            onCustomClickListener(character.name, character.image)
        }
    }

    override fun getLayout() = R.layout.character_row

    override fun initializeViewBinding(view: View) = CharacterRowBinding.bind(view)

}