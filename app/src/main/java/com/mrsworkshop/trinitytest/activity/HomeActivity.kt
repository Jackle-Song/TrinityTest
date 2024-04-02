package com.mrsworkshop.trinitytest.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrsworkshop.trinitytest.R
import com.mrsworkshop.trinitytest.adapter.AvengersListAdapter
import com.mrsworkshop.trinitytest.databinding.ActivityHomeBinding
import com.mrsworkshop.trinitytest.model.AvengersDataVO

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var avengersListAdapter: AvengersListAdapter

    private var avengersDataList : MutableList<AvengersDataVO>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    private fun initUI() {
        avengersDataList?.add(AvengersDataVO(R.drawable.superman, getString(R.string.home_activity_superman_text), getString(R.string.home_activity_very_good_text), 2))
        avengersDataList?.add(AvengersDataVO(R.drawable.spiderman, getString(R.string.home_activity_spiderman_text), getString(R.string.home_activity_normal_text), 1))
        avengersDataList?.add(AvengersDataVO(R.drawable.hulk, getString(R.string.home_activity_hulk_text), getString(R.string.home_activity_awesome_text), 3))

        avengersListAdapter = AvengersListAdapter(this@HomeActivity, avengersDataList)
        binding.recyclerviewAvengersList.layoutManager = LinearLayoutManager(this@HomeActivity)
        binding.recyclerviewAvengersList.adapter = avengersListAdapter
    }
}