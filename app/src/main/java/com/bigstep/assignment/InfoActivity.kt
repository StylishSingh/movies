package com.bigstep.assignment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bigstep.assignment.databinding.ActivityInfoBinding
import com.bumptech.glide.Glide

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_info)

        initUI()

    }

    @SuppressLint("SetTextI18n")
    private fun initUI() {
        intent?.let {

            val obj = it.getParcelableExtra<VideoResult>("SONG")

            obj?.let { data ->
                with(binding) {
                    tvTitle.text = data.trackName
                    Glide.with(ivThumb.context).load(data.artworkUrl100).into(ivThumb)
                    tvAuthor.text = "Author: ${data.artistName}"
                    tvDate.text = "Date: ${data.releaseDate}"
                    tvPrice.text = "Price: ${data.trackPrice}"
                }
            }


        }
    }
}