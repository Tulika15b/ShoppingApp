package com.example.shoppingapp

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.databinding.ActivityMainBinding
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.ui.CartsFragment
import com.example.shoppingapp.ui.ProductsFragment
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.sqrt
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ProductsFragment.ItemClickedListener   {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        if(savedInstanceState == null){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val fragment = ProductsFragment()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }
    override fun onResume() {
        super.onResume()
    }
    override fun onPause() {
        super.onPause()
    }

    override fun showCartDetails() {
        if(!supportFragmentManager.isDestroyed){
            val fragmentTransaction = supportFragmentManager.beginTransaction()

            fragmentTransaction.add(R.id.fragment_container, CartsFragment.newInstance())
                .addToBackStack("CartsFragment")
            fragmentTransaction.commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}