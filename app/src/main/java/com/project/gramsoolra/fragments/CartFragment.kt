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
import com.project.gramsoolra.databinding.FragmentCartBinding
import com.project.gramsoolra.di.Utils
import com.project.gramsoolra.repositories.MainRepository
import com.project.gramsoolra.request.ProductListRequest
import com.project.gramsoolra.viewModels.ApiViewModel
import com.project.gramsoolra.viewModels.ViewModelFactory


class CartFragment : Fragment() {

    private var _binding : FragmentCartBinding? = null
    private val binding get() = _binding!!

    private val apiViewModel : ApiViewModel by viewModels() {
        ViewModelFactory(MainRepository(ApiClient.apiService))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        sendRequestAPI()

        return binding.root
    }

    private fun sendRequestAPI() {
        val appPreference = SharedPrefManager(this@CartFragment.requireContext())

        val userId = appPreference.USER_ID
        val salt = appPreference.SALT

        val authToken = Utils.md5Hash(salt + userId)

        val cartListRequest = ProductListRequest()

        cartListRequest.userId = userId
        cartListRequest.authToken = authToken

        apiViewModel.cartList(cartListRequest)
        cartListAPI()
    }

    private fun cartListAPI() {

        apiViewModel.res_cart_list.observe(viewLifecycleOwner) {

            when(it.status) {

                Status.SUCCESS -> {

                    if (it.data?.success == true) {
                        binding.cvBottom.visibility = View.VISIBLE
                        binding.tvNoProduct.visibility = View.GONE
                        binding.cartProgressBar.visibility = View.GONE

                        val totalPrice = it.data?.parameters?.subTotal.toString()
                        binding.totalAmount.text = "Rs.$totalPrice"
                    }
                    else {
                        binding.cvBottom.visibility = View.GONE
                        binding.tvNoProduct.visibility = View.VISIBLE
                        binding.cartProgressBar.visibility = View.GONE
                    }
                }
                Status.LOADING -> {
                    binding.cvBottom.visibility = View.GONE
                    binding.tvNoProduct.visibility = View.GONE
                    binding.cartProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.cvBottom.visibility = View.GONE
                    binding.tvNoProduct.visibility = View.VISIBLE
                    binding.cartProgressBar.visibility = View.GONE
                }

            }

        }

    }

}