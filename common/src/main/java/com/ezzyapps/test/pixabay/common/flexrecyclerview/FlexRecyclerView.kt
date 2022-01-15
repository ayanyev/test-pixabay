package com.ezzyapps.test.pixabay.common.flexrecyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexboxLayoutManager


class FlexRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RecyclerView(context, attrs, defStyleAttr) {

    init {

        layoutManager = FlexboxLayoutManager(context, attrs, defStyleAttr, 0)

    }

}