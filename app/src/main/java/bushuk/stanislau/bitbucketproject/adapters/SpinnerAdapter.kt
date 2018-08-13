package bushuk.stanislau.bitbucketproject.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class SpinnerAdapter(context: Context, layout: Int, listString: List<String>) : ArrayAdapter<String>(context, layout, listString) {

    @SuppressLint("RtlHardcoded")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v = super.getView(position, convertView, parent)
        (v as TextView).gravity = Gravity.RIGHT
        return v
    }

}