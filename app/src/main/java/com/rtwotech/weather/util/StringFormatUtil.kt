package com.rtwotech.weather.util

import android.os.Build
import android.text.Html
import android.text.Spanned

class StringFormatUtil {

    companion object{
        fun toHtmlSpannable(input : String) : Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(input, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(input)
            }
        }
    }
}