package com.project.gramsoolra.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.gramsoolra.R
import com.project.gramsoolra.adapter.CartAdapter
import com.project.gramsoolra.api.ApiClient
import com.project.gramsoolra.common.SharedPrefManager
import com.project.gramsoolra.common.Status
import com.project.gramsoolra.dataClass.CartListItem
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

        binding.backButton.setOnClickListener {
            requireActivity().finish()
        }

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
                        binding.cartRecyclerview.visibility = View.VISIBLE
                        binding.tvNoProduct.visibility = View.GONE
                        binding.cartProgressBar.visibility = View.GONE

                        val layoutManager = LinearLayoutManager(this@CartFragment.requireContext())
                        binding.cartRecyclerview.layoutManager = layoutManager

                        val cartList = ArrayList<CartListItem>()

                        for (x in 0 until it.data.parameters?.data?.size!!) {

                            val productImage = it.data.parameters.data.get(x)?.product?.stockImages?.get(0)?.imageUrl.toString()
                            val productName = it.data.parameters.data.get(x)?.product?.narrationName.toString()
                            val productPrice = it.data.parameters.data.get(x)?.product?.totalPrice.toString()

                           cartList.add(CartListItem(productImage, productName, "Rs.$productPrice"))

                        }

                        val adapter = CartAdapter(cartList) {
                            binding.tvNoProduct.visibility = View.VISIBLE
                            binding.cartRecyclerview.visibility = View.GONE
                            binding.totalAmount.text = "Rs.0"
                        }
                        binding.cartRecyclerview.adapter = adapter

                        val totalPrice = it.data.parameters.subTotal.toString()
                        binding.totalAmount.text = "Rs.$totalPrice"
                    }
                    else {
                        binding.cvBottom.visibility = View.GONE
                        binding.cartRecyclerview.visibility = View.GONE
                        binding.tvNoProduct.visibility = View.VISIBLE
                        binding.cartProgressBar.visibility = View.GONE
                    }
                }
                Status.LOADING -> {
                    binding.cvBottom.visibility = View.GONE
                    binding.cartRecyclerview.visibility = View.GONE
                    binding.tvNoProduct.visibility = View.GONE
                    binding.cartProgressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.cvBottom.visibility = View.GONE
                    binding.cartRecyclerview.visibility = View.GONE
                    binding.tvNoProduct.visibility = View.VISIBLE
                    binding.cartProgressBar.visibility = View.GONE
                }

            }

        }

    }

}