package com.felix.ticketin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.felix.ticketin.adapter.ComingSoonAdapter
import com.felix.ticketin.adapter.PlayingNowAdapter
import com.felix.ticketin.api.ApiClient
import com.felix.ticketin.databinding.MainMenuFragmentBinding
import com.felix.ticketin.model.comingsoon.GetAllComingSoonResponse
import com.felix.ticketin.model.comingsoon.ResultComingSoon
import com.felix.ticketin.model.playingnow.GetAllPlayingNowIResponse
import com.felix.ticketin.model.playingnow.Result
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMenuFragment : Fragment() {
//    lateinit var firebaseAuth: FirebaseAuth
    private var _binding: MainMenuFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainMenuViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MainMenuFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainMenuViewModel::class.java)
//        firebaseAuth = FirebaseAuth.getInstance()
//        checkUser()
        fetchAllDataPlayingNow()
        fetchAllDataComingSoon()

        binding.tvUser.setOnClickListener {
            viewModel.uncensored()
        }

        viewModel.email.observe(viewLifecycleOwner,{
            binding.tvUser.text = it
        })
    }

    private fun fetchAllDataPlayingNow(){
        ApiClient.instance.getAllPlayingNow()
            .enqueue(object : Callback<GetAllPlayingNowIResponse> {
                override fun onResponse(
                    call: Call<GetAllPlayingNowIResponse>,
                    response: Response<GetAllPlayingNowIResponse>
                ){
                    val body = response.body()
                    val code = response.code()
                    if (code == 200){
                        showListPlayingNow(body?.results)
                        binding.progressBar.visibility = View.GONE
                    }
                }
                override fun onFailure(call: Call<GetAllPlayingNowIResponse>, t: Throwable){
                    binding.progressBar.visibility = View.GONE
                }
            })
    }

    private fun showListPlayingNow(data: List<Result>?){
        val adapter = PlayingNowAdapter(object : PlayingNowAdapter.OnClickListener{
            override fun onClickItem(data: Result) {
                val id = data.id
            }
        })
        adapter.submitData(data)
        binding.rvPlaying.adapter = adapter
    }

    private fun fetchAllDataComingSoon(){
        ApiClient.instance.getAllComingSoon()
            .enqueue(object : Callback<GetAllComingSoonResponse> {
                override fun onResponse(
                    call: Call<GetAllComingSoonResponse>,
                    response: Response<GetAllComingSoonResponse>
                ){
                    val body = response.body()
                    val code = response.code()
                    if (code == 200){
                        showListComingSoon(body?.resultComingSoons)
                        binding.progressBar.visibility = View.GONE
                    }else{
                        binding.progressBar.visibility = View.GONE
                    }
                }
                override fun onFailure(call: Call<GetAllComingSoonResponse>, t: Throwable){
                    binding.progressBar.visibility = View.GONE
                }
            })
    }

    private fun showListComingSoon(data: List<ResultComingSoon>?){
        val adapter = ComingSoonAdapter(object : ComingSoonAdapter.OnClickListener{
            override fun onClickItem(data: ResultComingSoon) {
                val id = data.id
            }
        })
        adapter.submitData(data)
        binding.rvComingsoon.adapter = adapter
    }

//    private fun checkUser() {
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser != null){
//            //get user info
//            val email = firebaseUser.email
//            binding.tvUser.text = "Hello, \n${email}"
//        }
//    }
}

