package co.cyclopsapps.cleanrickandmorty.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import co.cyclopsapps.cleanrickandmorty.character.CharacterState
import co.cyclopsapps.cleanrickandmorty.character.detail.DetailFragment
import co.cyclopsapps.cleanrickandmorty.character.list.adapater.CharacterAdapter
import co.cyclopsapps.cleanrickandmorty.character.list.item.CharacterRow
import co.cyclopsapps.cleanrickandmorty.character.list.item.CharacterRow2
import co.cyclopsapps.cleanrickandmorty.character.list.model.CharacterDataModel
import co.cyclopsapps.cleanrickandmorty.databinding.FragmentMainBinding
import co.cyclopsapps.cleanrickandmorty.utilities.ScreenState
import co.cyclopsapps.cleanrickandmorty.views.viewmodels.MainViewModel
import com.xwray.groupie.GroupieAdapter
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MainFragment : Fragment(), OnCustomClickListener {

    // BINDING
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var characterAdapter: CharacterAdapter
    private var groupieAdapter = GroupieAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getState().observe(this, Observer { onChanged(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.button.setOnClickListener {
            viewModel.fetchCharacterData()
        }

    }

    private fun setupRecyclerView() {
        //Recibe interfaz y el otro recibe función

        //Primero pasamos interfaz y el segundo paramatro es una función
        //characterAdapter = CharacterAdapter(this, ::onCategoryClickListener)

        with(binding.rvCharacterss) {
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            // adapter = characterAdapter
            adapter = groupieAdapter // Groupie
        }
    }

    /**
     * Validate answers
     * @param screenState screenState type to validate
     */
    private fun onChanged(screenState: ScreenState<CharacterState>?) {
        this@MainFragment.context?.let {
            screenState?.let {
                if (screenState is ScreenState.Render) {
                    updateUI(screenState.renderState)
                } else {
                    when (screenState) {
                        ScreenState.Loading -> {
                            binding.progress.isVisible = true
                        }
                        ScreenState.InternetError -> {
                            binding.progress.isVisible = false

                        }

                        ScreenState.ErrorServer -> {
                            binding.progress.isVisible = false
                            // show error
                        }
                        else -> {
                            binding.progress.isVisible = false

                        }
                    }
                }
            }
        }
    }

    /**
     * Validate different kind of response
     * @param renderState type de renderState to validate
     */
    private fun updateUI(renderState: Any) {
        this@MainFragment.context?.let {
            when (renderState) {
                is CharacterState.ShowRestaurantData -> {
                    //characterAdapter.setCharacterData(renderState.response)
                    setCharacters(renderState.response.results)
                    binding.progress.isVisible = false

                }

                else -> {
                    binding.progress.isVisible = false
                }
            }
        }
    }

    private fun setCharacters(list: MutableList<CharacterDataModel>?) {
        list?.forEach { character ->
            groupieAdapter.add(CharacterRow2(character, ::onCategoryClickListener)) // ITEM 2
            groupieAdapter.add(CharacterRow(character, ::onCategoryClickListener)) // ITEM 1
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //FORMAS DE USAR EL CLICK LISTENES

    //1 Con Interfaces
    // Crear interface e implementar funciones
    override fun showCategoryDetail(img: String) {
        Toast.makeText(context, "Mi imagen click1 $img", Toast.LENGTH_SHORT).show()
        // Aqui haces la funcionalidad de abrir el detalle

        //openDetail()
    }

    //2 Con una función (Listener)
    //No tienes que estar creando interfaces
    private fun onCategoryClickListener(name: String, img: String) {
        openDetail(name, img)

        Toast.makeText(context, "Mi imagen click2 $img", Toast.LENGTH_SHORT).show()
    }


    private fun openDetail(name: String, img: String) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.add(
                android.R.id.content,
                DetailFragment.newInstance(
                    name = name,
                    img = img
                )
            )
            ?.addToBackStack(null)
            ?.commit()
    }

}