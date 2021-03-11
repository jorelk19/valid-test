package com.valid.edson.ui.views.activities.artist

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.valid.edson.R
import com.valid.edson.databinding.ActivityArtistDetailBinding
import com.valid.edson.ui.views.activities.base.BaseFragmentActivity

class ArtistDetailActivity : BaseFragmentActivity() {

    private lateinit var binding : ActivityArtistDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@ArtistDetailActivity, R.layout.activity_artist_detail)
    }
}