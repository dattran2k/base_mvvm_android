package com.base.presentation.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.base.R
import com.base.databinding.CustomTabViewpagerBinding

class CustomTab(context: Context, attributeSet: AttributeSet? = null) :
    LinearLayout(context, attributeSet) {
    var binding: CustomTabViewpagerBinding? = null

    init {
        binding = CustomTabViewpagerBinding.inflate(LayoutInflater.from(context), this, true)
    }

    override fun setSelected(selected: Boolean) {
        super.setSelected(selected)
        val color = if (selected) {
            R.color.active
        } else {
            R.color.gray_666
        }
        binding?.tvTitle?.setTextColor(ContextCompat.getColor(context, color))
        binding?.imIcon?.setColorFilter(
            ContextCompat.getColor(context, color),
            android.graphics.PorterDuff.Mode.SRC_IN
        )
    }
}