package com.mrsworkshop.trinitytest.activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mrsworkshop.trinitytest.R
import com.mrsworkshop.trinitytest.adapter.AvengersListAdapter
import com.mrsworkshop.trinitytest.databinding.ActivityHomeBinding
import com.mrsworkshop.trinitytest.model.AvengersDataVO

class HomeActivity : BaseActivity(), AvengersListAdapter.AvengersDetailInterface {

    companion object {
        const val INTENT_AVENGERS_DETAILS = "intentAvengersDetails"
    }

    private lateinit var binding: ActivityHomeBinding
    private lateinit var avengersListAdapter: AvengersListAdapter

    private var avengersDataList : MutableList<AvengersDataVO>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }

    override fun viewAvengersDetail(avengersDataVO: AvengersDataVO) {
        val formattedAvengersDetail = Gson().toJson(avengersDataVO)
        val intent = Intent(this@HomeActivity, AvengerDetailsActivity::class.java)
        intent.putExtra(INTENT_AVENGERS_DETAILS, formattedAvengersDetail)
        startActivity(intent)
    }

    private fun initUI() {
        avengersDataList?.add(AvengersDataVO(R.drawable.superman, getString(R.string.home_activity_superman_text), getString(R.string.home_activity_very_good_text), 2f, getString(R.string.avenger_details_activity_superman_description_text)))
        avengersDataList?.add(AvengersDataVO(R.drawable.spiderman, getString(R.string.home_activity_spiderman_text), getString(R.string.home_activity_normal_text), 1f, getString(R.string.avenger_details_activity_spiderman_description_text)))
        avengersDataList?.add(AvengersDataVO(R.drawable.hulk, getString(R.string.home_activity_hulk_text), getString(R.string.home_activity_awesome_text), 3f, getString(R.string.avenger_details_activity_hulk_description_text)))

        avengersListAdapter = AvengersListAdapter(this@HomeActivity, avengersDataList, this@HomeActivity)
        binding.recyclerviewAvengersList.layoutManager = LinearLayoutManager(this@HomeActivity)
        binding.recyclerviewAvengersList.adapter = avengersListAdapter
    }
}