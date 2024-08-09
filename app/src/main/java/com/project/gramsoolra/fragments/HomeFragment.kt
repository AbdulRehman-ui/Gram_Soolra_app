package com.project.gramsoolra.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.project.gramsoolra.R
import com.project.gramsoolra.api.ApiClient
import com.project.gramsoolra.common.SharedPrefManager
import com.project.gramsoolra.common.Status
import com.project.gramsoolra.databinding.FragmentHomeBinding
import com.project.gramsoolra.repositories.MainRepository
import com.project.gramsoolra.request.ProductListRequest
import com.project.gramsoolra.viewModels.ApiViewModel
import com.project.gramsoolra.viewModels.ViewModelFactory


class HomeFragment : Fragment() {


    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val apiViewModel : ApiViewModel by viewModels() {
        ViewModelFactory(MainRepository(ApiClient.apiService))
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        sendAPIRequest()

        return binding.root
    }

    private fun sendAPIRequest() {
        val appPreference = SharedPrefManager(this@HomeFragment.requireContext())

        val authToken = appPreference.KEY_ACCESS_TOKEN
        val userId = appPreference.USER_ID

        val productListRequest = ProductListRequest()

        productListRequest.userId = userId
        productListRequest.page = "1"
        productListRequest.authToken = authToken

        apiViewModel.productList(productListRequest)
        getProductListAPI()
    }


    private fun getProductListAPI() {
        apiViewModel.res_product_list.observe(viewLifecycleOwner) {

            when(it.status) {

                Status.SUCCESS -> {
                    val price = it.data?.parameters?.data?.get(0)?.price.toString()
                    Toast.makeText(this@HomeFragment.requireContext(), ""+price, Toast.LENGTH_SHORT).show()
                }

                Status.LOADING -> {

                }

                Status.ERROR -> {

                }

            }

        }
    }

}