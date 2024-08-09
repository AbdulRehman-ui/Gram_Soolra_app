package com.project.gramsoolra.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.gramsoolra.R
import com.project.gramsoolra.adapter.ProductAdapter
import com.project.gramsoolra.api.ApiClient
import com.project.gramsoolra.common.SharedPrefManager
import com.project.gramsoolra.common.Status
import com.project.gramsoolra.dataClass.ProductListItem
import com.project.gramsoolra.databinding.FragmentHomeBinding
import com.project.gramsoolra.di.Utils
import com.project.gramsoolra.repositories.MainRepository
import com.project.gramsoolra.request.ProductListRequest
import com.project.gramsoolra.viewModels.ApiViewModel
import com.project.gramsoolra.viewModels.ViewModelFactory


class HomeFragment : Fragment() {


    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var searchString = ""

    private val apiViewModel : ApiViewModel by viewModels() {
        ViewModelFactory(MainRepository(ApiClient.apiService))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            handleBackPress()
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener {
            handleBackPress()
        }

        sendAPIRequest(searchString)

        binding.etSearch.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendAPIRequest(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                sendAPIRequest(s.toString())
            }

        })

        return binding.root
    }

    private fun sendAPIRequest(searchQuery : String) {
        val appPreference = SharedPrefManager(this@HomeFragment.requireContext())

        val userId = appPreference.USER_ID
        val salt = appPreference.SALT

        val authToken = Utils.md5Hash(salt)

        val productListRequest = ProductListRequest()

        productListRequest.userId = userId
        productListRequest.page = "1"
        productListRequest.searchTerm = searchQuery
        productListRequest.authToken = authToken

        apiViewModel.productList(productListRequest)
        getProductListAPI()
    }


    private fun getProductListAPI() {
        apiViewModel.res_product_list.observe(viewLifecycleOwner) {

            when(it.status) {

                Status.SUCCESS -> {

                    if (it.data?.success == true) {

                        binding.productProgressBar.visibility = View.GONE
                        binding.tvNoProduct.visibility = View.GONE
                        binding.productRecyclerview.visibility = View.VISIBLE

                        val layoutManager = GridLayoutManager(this@HomeFragment.requireContext(), 2)
                        binding.productRecyclerview.layoutManager = layoutManager

                        val productList = ArrayList<ProductListItem>()

                        for( x in 0 until it.data?.parameters?.data?.size!!) {
                            val productName = it.data.parameters.data.get(x)?.narrationName.toString()
                            val productImage = it.data.parameters.data.get(x)?.stockPhotos?.get(0)?.imageUrl.toString()
                            val productPrice = it.data.parameters.data.get(x)?.price.toString()

                            val street = it.data.parameters.data.get(x)?.stockAddress?.get(0)?.street.toString()
                            val district = it.data.parameters.data.get(x)?.stockAddress?.get(0)?.city.toString()
                            val state = it.data.parameters.data.get(x)?.stockAddress?.get(0)?.state.toString()
                            val pincode = it.data.parameters.data.get(x)?.stockAddress?.get(0)?.pincode.toString()

                            val productLocation = street + ", " + district + ", " + state + "-" + pincode

                            productList.add(ProductListItem(productImage, productName, "₹"+productPrice+"/m", productLocation))

                        }

                        val productAdapter = ProductAdapter(productList)
                        binding.productRecyclerview.adapter = productAdapter
                    }
                    else {

                        binding.productProgressBar.visibility = View.GONE
                        binding.productRecyclerview.visibility = View.GONE
                        binding.tvNoProduct.visibility = View.VISIBLE

                    }



                }

                Status.LOADING -> {
                    binding.productProgressBar.visibility = View.VISIBLE
                    binding.productRecyclerview.visibility = View.GONE
                    binding.tvNoProduct.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.productProgressBar.visibility = View.GONE
                    binding.productRecyclerview.visibility = View.GONE
                    binding.tvNoProduct.visibility = View.VISIBLE
                }

            }
        }
    }


    private fun handleBackPress() {
        if (true) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        } else {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

}