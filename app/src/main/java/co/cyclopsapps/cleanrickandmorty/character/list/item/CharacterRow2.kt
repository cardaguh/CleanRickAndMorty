package co.cyclopsapps.cleanrickandmorty.character.list.item

import android.view.View
import co.cyclopsapps.cleanrickandmorty.R
import co.cyclopsapps.cleanrickandmorty.character.list.model.CharacterDataModel
import co.cyclopsapps.cleanrickandmorty.databinding.CharacterRow2Binding
import com.bumptech.glide.Glide
import com.xwray.groupie.viewbinding.BindableItem

class CharacterRow2(
    private val character: CharacterDataModel,
    private val onCustomClickListener: (name: String, img: String) -> Unit
) : BindableItem<CharacterRow2Binding>() {

    override fun bind(viewBinding: CharacterRow2Binding, position: Int) {
        with(character) {
            Glide.with(viewBinding.root.context).load(image).into(viewBinding.imgCharacter)
            viewBinding.txtName.text = name
            viewBinding.txtDescription.text = species
        }

        viewBinding.root.setOnClickListener {
            onCustomClickListener(character.name, character.image)
        }
    }

    override fun getLayout() = R.layout.character_row2
    override fun initializeViewBinding(view: View) = CharacterRow2Binding.bind(view)
}