package com.mrsworkshop.trinitytest.activity

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.mrsworkshop.trinitytest.activity.HomeActivity.Companion.INTENT_AVENGERS_DETAILS
import com.mrsworkshop.trinitytest.databinding.ActivityAvengersDetailBinding
import com.mrsworkshop.trinitytest.model.AvengersDataVO
import jp.wasabeef.glide.transformations.BlurTransformation

class AvengerDetailsActivity : BaseActivity() {
    private lateinit var binding: ActivityAvengersDetailBinding

    private var avengersDataVO : AvengersDataVO = AvengersDataVO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvengersDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val window = window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window?.statusBarColor = Color.TRANSPARENT
        setStatusBarLightTheme(false)

        val avengersDetailsJson = intent.getStringExtra(INTENT_AVENGERS_DETAILS)
        avengersDataVO = Gson().fromJson(avengersDetailsJson, AvengersDataVO::class.java)

        initUI()
        setupComponentListener()
    }

    private fun initUI() {
        Glide.with(this@AvengerDetailsActivity)
            .load(avengersDataVO.imageUrl)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.imgBlurAvengerDetail)
        Glide.with(this@AvengerDetailsActivity)
            .load(avengersDataVO.imageUrl)
            .into(binding.imgCardAvengerImage)

        binding.txtAvengersDetailTitle.text = avengersDataVO.title
        binding.txtAvengersDetailReviews.text = avengersDataVO.review
        binding.txtAvengersDetailDescription.text = avengersDataVO.description

        binding.ratingBarAvengers.rating = avengersDataVO.rating ?: 0f
    }

    private fun setupComponentListener() {
        binding.imgBackBtn.setOnClickListener {
            finish()
        }
    }
}