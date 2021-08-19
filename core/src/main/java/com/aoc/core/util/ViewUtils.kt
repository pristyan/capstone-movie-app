package com.aoc.core.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.widget.NestedScrollView
import com.aoc.core.R
import com.bumptech.glide.Glide
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart


fun NestedScrollView.onLoadMore(callback: () -> Unit) {
    this.setOnScrollChangeListener { v: NestedScrollView?, _: Int, y: Int, _: Int, oldY: Int ->
        if (v?.getChildAt(v.childCount - 1) != null) {
            if ((y >= (v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight)) && y > oldY) {
                callback.invoke()
            }
        }
    }
}

fun ImageView?.loadImage(
    url: String?,
    @DrawableRes placeholder: Int = R.drawable.img_placeholder_square,
    @DrawableRes errorPlaceholder: Int = R.drawable.img_placeholder_square
) {
    this?.let {
        Glide.with(it)
            .load(url ?: "")
            .placeholder(placeholder)
            .error(errorPlaceholder)
            .into(it)
    }
}

@ExperimentalCoroutinesApi
fun EditText.onTextChange(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                trySend(s)
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}