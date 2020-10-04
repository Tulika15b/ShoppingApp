package com.example.shoppingapp.ui

import android.content.Context
import android.hardware.SensorManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CartProductRVAdapter
import com.example.shoppingapp.databinding.FragmentCartDetailsBinding
import com.example.shoppingapp.model.CartProductModel
import com.example.shoppingapp.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_cart_details.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartsFragment : Fragment() {

    private lateinit var binding : FragmentCartDetailsBinding
    private val mViewModel: CartViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_cart_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch{
            val result : List<CartProductModel> = mViewModel.getAllCartItems()
            shopping_cart_rv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = CartProductRVAdapter(context, result)
            }
            binding.toolbar.title = "Cart Total : $"+mViewModel.getTotalValue().toString()
        }

    }

    override fun onPause() {
        super.onPause()

    }

    companion object {
        fun newInstance(): CartsFragment = CartsFragment()
    }
}