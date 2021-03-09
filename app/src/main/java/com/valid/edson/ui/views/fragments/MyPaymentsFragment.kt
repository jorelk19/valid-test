package com.valid.edson.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.valid.edson.R
import com.valid.edson.databinding.LayoutMyPaymentsFragmentBinding
import com.valid.edson.utils.getViewModelFactory
import com.valid.edson.ui.viewModels.MyPaymentsViewModel
import com.valid.edson.ui.views.fragments.adapters.MyPaymentsAdapter
import com.valid.utils.ViewManager

class MyPaymentsFragment : Fragment() {
    private lateinit var myPaymentsFragmentBinding: LayoutMyPaymentsFragmentBinding
    private val viewModel by viewModels<MyPaymentsViewModel> { getViewModelFactory() }
    private lateinit var myPaymentsAdapter: MyPaymentsAdapter

    /**
     * Method to instantiate the view
     * */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myPaymentsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.layout_my_payments_fragment, container, false)
        myPaymentsFragmentBinding.viewModel = viewModel
        myPaymentsFragmentBinding.lifecycleOwner = ViewManager.getInstance.getCurrentActivity()
        setAdapters()
        addSubscriptions()
        viewModel.getMyPaymentsRepository()
        return myPaymentsFragmentBinding.root
    }

    private fun addSubscriptions() {
        viewModel.getMyPayments().observe(viewLifecycleOwner, Observer { payments ->
            payments?.let {
                myPaymentsAdapter.addItems(it)
            }
        })
    }

    private fun setAdapters() {
        myPaymentsAdapter = MyPaymentsAdapter(ViewManager.getInstance.getCurrentActivity(), arrayListOf())
        myPaymentsFragmentBinding.rvMyPayments.layoutManager = LinearLayoutManager(ViewManager.getInstance.getCurrentActivity())
        myPaymentsFragmentBinding.rvMyPayments.adapter = myPaymentsAdapter
    }
}