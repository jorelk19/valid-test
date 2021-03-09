package com.valid.edson.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.valid.edson.ui.viewModels.base.ViewModelFactory

/**
 * Extension functions for Fragment.
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 */
fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory()
}

fun FragmentActivity.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory()
}