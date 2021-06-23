package com.bigstep.assignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigstep.assignment.databinding.VideoFragmentBinding

class VideoFragment : BaseFragment(R.layout.video_fragment), OnItemClickListener {

    private var listSongs = mutableListOf<VideoResult>()
    private lateinit var viewModel: VideoViewModel
    private var binding: VideoFragmentBinding? = null
    private lateinit var songAdapter: VideoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = VideoFragmentBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(VideoViewModel::class.java)
        viewModel.setDB(db)
        initUI()
        setObservers()
        viewModel.getSongs()

    }

    private fun setObservers() {
        viewModel.observerModel.observe(viewLifecycleOwner, {
            if (it != null) {
                listSongs.clear()
                listSongs.addAll(it)
                songAdapter.updateList(listSongs)
                viewModel.observerModel.value = null
            }
        })

        viewModel.progress.observe(viewLifecycleOwner, {

            if (it) {
                binding?.progress?.visibility = View.VISIBLE
            } else {
                binding?.progress?.visibility = View.GONE
            }

        })
    }

    private fun initUI() {
        songAdapter = VideoAdapter(listSongs)
        binding?.rvVideo?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = songAdapter
        }
        songAdapter.setItemSelectedListener(this)


    }

    override fun onClick(position: Int, type: String) {
        val obj = listSongs[position]
        viewModel.insertSong(obj)
        startActivity(Intent(requireActivity(), InfoActivity::class.java).apply {
            putExtra("SONG", obj)

        })
    }

}