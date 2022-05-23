package com.felix.ticketin.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.felix.ticketin.R
import com.felix.ticketin.adapter.FavAdapter
import com.felix.ticketin.data.room.FavEntity
import com.felix.ticketin.databinding.FragmentFavBinding
import com.felix.ticketin.databinding.MainMenuFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavFragment : Fragment() {
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllPosts()
        viewModel.dataFavorite.observe(viewLifecycleOwner){
            val adapter = FavAdapter(object : FavAdapter.OnClickListener{
                override fun onClickItem(data: FavEntity) {
                    Toast.makeText(requireContext(), "anda memilih ${data.name}", Toast.LENGTH_SHORT).show()
                }
            })
            adapter.submitData(it)
            binding.rvFav.adapter = adapter
        }
    }

}