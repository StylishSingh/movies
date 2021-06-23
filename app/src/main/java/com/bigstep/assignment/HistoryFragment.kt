package com.bigstep.assignment

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.bigstep.assignment.databinding.HistoryFragmentBinding
import com.bigstep.assignment.databinding.VideoFragmentBinding
import com.bigstep.assignment.room.AppDatabase

class HistoryFragment : BaseFragment(R.layout.history_fragment), OnItemClickListener {

    private var listSongs = mutableListOf<VideoResult>()
    private lateinit var viewModel: VideoViewModel
    private var binding: HistoryFragmentBinding? = null
    private lateinit var songAdapter : VideoAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HistoryFragmentBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(VideoViewModel::class.java)
        initUI()
        setObservers()
    }

    private fun initUI() {
        songAdapter=VideoAdapter(listSongs)
        binding?.rvHistory?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = songAdapter
        }
        songAdapter.setItemSelectedListener(this)
    }

    private fun setObservers() {
        viewModel.observerModelDB.observe(viewLifecycleOwner, {
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

    override fun onClick(position: Int, type: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("HistoryFragment.onCreate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("HistoryFragment.onAttach")
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLocalSongs()
    }

}