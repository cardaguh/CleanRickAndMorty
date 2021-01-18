package co.cyclopsapps.cleanrickandmorty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import co.cyclopsapps.cleanrickandmorty.character.CharacterState
import co.cyclopsapps.cleanrickandmorty.character.detail.DetailFragment
import co.cyclopsapps.cleanrickandmorty.character.list.adapater.CharacterAdapter
import co.cyclopsapps.cleanrickandmorty.databinding.FragmentMainBinding
import org.w3c.dom.CharacterData


class MainFragment : Fragment(), OnCustomClickListener {

    // BINDING
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    private lateinit var characterAdapter: CharacterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.getState().observe(this, Observer { onChanged(it) })
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
            mainViewModel.fetchCharacterData()
        }

    }

    private fun setupRecyclerView() {
        //Recibe interfaz y el otro recibe función

        //Primero pasamos interfaz y el segundo paramatro es una función
        characterAdapter = CharacterAdapter(this, ::onCategoryClickListener)

        with(binding.rvCharacterss) {
            layoutManager = LinearLayoutManager(binding.root.context)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = characterAdapter
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
                    characterAdapter.setCharacterData(renderState.response)
                    binding.progress.isVisible = false

                }

                else -> {
                    binding.progress.isVisible = false
                }
            }
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