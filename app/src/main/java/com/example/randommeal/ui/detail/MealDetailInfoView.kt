package com.example.randommeal.ui.detail

import android.content.Context
import android.text.Html
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import com.example.domain.Meal

class MealDetailInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    fun setMeal(meal: Meal) = meal.apply {
        text = buildSpannedString {
            bold { append("Summary: ") }
            appendLine( HtmlCompat.fromHtml(summary.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY))

            bold { append("Instructions: ") }
            appendLine(HtmlCompat.fromHtml(instructions.toString(), HtmlCompat.FROM_HTML_MODE_LEGACY))


        }
    }
}