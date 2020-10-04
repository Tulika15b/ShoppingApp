package com.example.shoppingapp.ui

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.os.postDelayed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.R
import com.example.shoppingapp.adapter.CategoryRVAdapter
import com.example.shoppingapp.adapter.ProductRVAdapter
import com.example.shoppingapp.databinding.FragmentProductsBinding
import com.example.shoppingapp.model.Category
import com.example.shoppingapp.model.Product
import com.example.shoppingapp.viewmodel.CartViewModel
import com.example.shoppingapp.viewmodel.CatalogViewModel
import com.yuyakaido.android.cardstackview.*
import com.yuyakaido.android.cardstackview.RewindAnimationSetting
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class ProductsFragment : Fragment(), View.OnClickListener, CardStackListener {

    var mSensorManager : SensorManager? = null
    private var acceleration = 0f
    private var currentAcceleration = 0f
    private var lastAcceleration = 0f

    private lateinit var binding : FragmentProductsBinding
    lateinit var adapter : ProductRVAdapter
    private lateinit var layoutManager: CardStackLayoutManager
    private val mViewModel: CatalogViewModel by viewModels()
    lateinit var mCallback : ItemClickedListener
    private val mCartViewModel : CartViewModel by viewModels ()
    lateinit var lastAppearedItemId : String
    var lastAppearedQty : Int = 1
    private val manager by lazy { CardStackLayoutManager(context, this) }
    var qty = 1;

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        mCallback = activity as ItemClickedListener
        setHasOptionsMenu(true)


        mSensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        Objects.requireNonNull(mSensorManager)!!.registerListener(
            sensorListener, mSensorManager!!
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL
        )
        acceleration = 10f
        currentAcceleration = SensorManager.GRAVITY_EARTH
        lastAcceleration = SensorManager.GRAVITY_EARTH
    }

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]

            lastAcceleration = currentAcceleration
            currentAcceleration = Math.sqrt((x * x + y * y + z * z).toDouble()).toFloat()
            val delta: Float = currentAcceleration - lastAcceleration
            acceleration = acceleration * 0.9f + delta

            if (acceleration > 10) {
                Toast.makeText(context, "Shake event detected", Toast.LENGTH_SHORT).show()
                mCallback.showCartDetails()
            }
        }
        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_showCart){
            mCallback.showCartDetails()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_products, container, false
        )

        binding.loadingProgress.visibility = View.VISIBLE
        mViewModel.fetchCategories()?.observe(viewLifecycleOwner) { categories ->
            binding.loadingProgress.visibility = View.GONE
            binding.categoryListRv.apply {
                layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                adapter = CategoryRVAdapter(categories) { category -> itemClicked(category)
                }

            }
        }

        return binding.root
    }

    private fun itemClicked(category: Category) {
        Log.d("PF", "Item clicked" + category.categoryId);
        binding.qtyChangerLl.apply {
            visibility = View.VISIBLE
        }

        binding.qtyNumber.setText("1")
        qty = 1;

        // '%id":14%'

        mViewModel.fetchProducts(category.categoryId)?.observe(viewLifecycleOwner) { products ->

            layoutManager = CardStackLayoutManager(activity, this).apply {
                setSwipeableMethod(SwipeableMethod.Manual)
                setDirections(listOf(Direction.Right, Direction.Top, Direction.Left))
                setVisibleCount(3)
                setStackFrom(StackFrom.Top)
                setOverlayInterpolator(LinearInterpolator())
            }
            adapter = context?.let { ProductRVAdapter(it, products) }!!
            product_stack_view.layoutManager = layoutManager
            product_stack_view.adapter = adapter
            product_stack_view.itemAnimator.apply {
                if (this is DefaultItemAnimator) {
                    supportsChangeAnimations = false
                }
            }

            adapter.setProducts(products)
        }

    }

    override fun onResume() {
        mSensorManager?.registerListener(
            sensorListener, mSensorManager!!.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER
            ), SensorManager.SENSOR_DELAY_NORMAL
        )
        super.onResume()
    }

    override fun onPause() {
        mSensorManager!!.unregisterListener(sensorListener)
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.decrementQty.setOnClickListener(this)
        binding.incrementQty.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.increment_qty -> increaseQty()
            R.id.decrement_qty -> decreaseQty()
        }
    }

    fun increaseQty(){
        qty++;
        Log.d("PF", qty.toString())

        binding.qtyNumber.apply {
            text = qty.toString()
        }
    }

    fun decreaseQty(){
        if(qty > 1)
            qty--;

        binding.qtyNumber.apply {
            text = qty.toString()
        }
    }



    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        binding.qtyNumber.setText("1")
        qty = 1;

        if(direction == Direction.Top){
            Log.d("OCS", "Card swiped up")
            lifecycleScope.launch(Dispatchers.IO){
                var product: Product = mViewModel.getProductById(lastAppearedItemId)
                addToCart(product, lastAppearedQty)
            }
        }
        else if(direction == Direction.Right){
            Log.d("OCS", "Card swiped right")
            binding.productStackView.apply {
                val settings = RewindAnimationSetting.Builder()
                    .setDirection(Direction.Right)
                    .setDuration(Duration.Normal.duration)
                    .setInterpolator(DecelerateInterpolator())
                    .build()

                manager.setRewindAnimationSetting(settings)
                manager.setDirections(listOf(Direction.Right, Direction.Top, Direction.Left))

                layoutManager = manager
                this.rewind()
            }
        }
        else if(direction == Direction.Left){
            Log.d("OCS", "Card swiped left")
            binding.productStackView.apply {
                swipe()
            }
        }
    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {
        Log.d("OCA", "onCardAppeared")
    }

    override fun onCardDisappeared(view: View?, position: Int) {
        Log.d("OCA", "onCardDisappeared" + view?.tag.toString())
        lastAppearedItemId = view?.tag.toString()
        lastAppearedQty = qty
    }

    fun addToCart(product: Product, qty: Int){
        lifecycleScope.launch {
            mCartViewModel.insertToCart(product, qty)
        }
    }

    interface ItemClickedListener{
        fun showCartDetails()
    }

    companion object {
        fun newInstance(): ProductsFragment = ProductsFragment()
    }
}