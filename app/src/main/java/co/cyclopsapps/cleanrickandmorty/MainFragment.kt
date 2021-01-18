package co.cyclopsapps.cleanrickandmorty

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider


class MainFragment : Fragment(), OnCustomClickListener {

    // BINDING
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var restaurantViewModel: MainViewModel

    private lateinit var adapter: RestaurantAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurantViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        restaurantViewModel.getState().observe(this, Observer { onChanged(it) })
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


        with(binding) {
            button.setOnClickListener { view ->
                restaurantViewModel.getRestaurantData()
            }
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
                    adapter.setRestaurantData(renderState.response)
                    binding.progress.isVisible = false

                }

                else -> {
                    binding.progress.isVisible = false
                }
            }
        }
    }


    private fun setupRecyclerView() {
        //Recibe interfaz y el otro recibe función

        //Primeor pasamos interfaz y el segundo paramatro es una función
        adapter = RestaurantAdapter(this, ::onCategoryClickListener)

        with(binding) {
            rvRestaurants.layoutManager = LinearLayoutManager(requireContext())
            rvRestaurants.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            rvRestaurants.adapter = adapter
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

        openDetail()
    }

    //2 Con una función (Listener)
    //No tienes que estar creando interfaces
    private fun onCategoryClickListener(img: String) {
        openDetail()

        Toast.makeText(context, "Mi imagen click2 $img", Toast.LENGTH_SHORT).show()
    }


    private fun openDetail() {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.add(android.R.id.content, CategoryFragment.newInstance())
            ?.addToBackStack(null)
            ?.commit()
    }

}