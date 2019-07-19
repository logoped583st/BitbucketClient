package bushuk.stanislau.bitbucketproject.views

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.transition.Slide
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.loading_view.view.*

const val SUPER_STATE = "SUPER_STATE"

const val VIEW_VISIBILITY = "VIEW_VISIBILITY"


class LoadingView : ConstraintLayout {

    var loadingVisibility: Int = View.GONE

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun show(rootScree: ViewGroup) {
        loadingVisibility = View.VISIBLE

        val transition = Slide(Gravity.TOP)
        transition.duration = 300
        transition.addTarget(waiting_message)

        TransitionManager.beginDelayedTransition(rootScree, transition)
        waiting_message.visibility = View.VISIBLE
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        waiting_message.visibility = loadingVisibility
    }

    override fun onSaveInstanceState(): Parcelable? {
        val bundle = Bundle()
        bundle.putParcelable(SUPER_STATE, super.onSaveInstanceState())
        bundle.putInt(VIEW_VISIBILITY, loadingVisibility)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            loadingVisibility = state.getInt(VIEW_VISIBILITY, View.GONE)
            super.onRestoreInstanceState(state.getParcelable(SUPER_STATE))
        }
    }
}