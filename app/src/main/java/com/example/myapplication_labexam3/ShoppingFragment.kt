package com.example.myapplication_labexam3

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication_labexam3.database.Shop
import com.example.myapplication_labexam3.database.ShopDataBase
import com.example.myapplication_labexam3.database.ShopRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoppingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoppingFragment : Fragment() {
    private lateinit var adapter: ShopAdapter
    private lateinit var viewModel:ShopActivityData
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_shopping,container,false)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.rvItems)
        val repository = ShopRepository(ShopDataBase.getInstance(requireContext()))

        viewModel = ViewModelProvider(this)[ShopActivityData::class.java]

        viewModel.data.observe(viewLifecycleOwner){
            adapter = ShopAdapter(it, repository, viewModel)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllShopItems()
            requireActivity().runOnUiThread {
                viewModel.setData(data)
            }
        }
        val btnAddItem: Button = rootView.findViewById(R.id.btnAdd)
        btnAddItem.setOnClickListener {
            displayDialog(repository)
        }
        return rootView
}

    private fun displayDialog(repository: ShopRepository){
        val builder = AlertDialog.Builder(requireContext())

        // Set the alert dialog title and message
        builder.setTitle(getText(R.string.alertTitle))
        builder.setMessage(getText(R.string.alertMessage))
        // Create an EditText input field
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        // Set the positive button action
        builder.setPositiveButton("OK") { dialog, which ->
// Get the input text and display a Toast message
            val item = input.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Shop(item))
                val data = repository.getAllShopItems()
                requireActivity().runOnUiThread {
                    viewModel.setData(data)
                }
            }
        }
        // Set the negative button action
        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.cancel()
        }
// Create and show the alert dialog
        val alertDialog = builder.create()
        alertDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoppingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ShoppingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}


