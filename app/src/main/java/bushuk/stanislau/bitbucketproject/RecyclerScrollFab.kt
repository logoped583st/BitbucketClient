package bushuk.stanislau.bitbucketproject

import androidx.recyclerview.widget.RecyclerView
import com.github.clans.fab.FloatingActionMenu

abstract class RecyclerScrollFab : RecyclerView.OnScrollListener() {

    abstract fun getFab(): FloatingActionMenu

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val fab = getFab()
        if (dy > 0 && !fab.isMenuButtonHidden) {
            fab.hideMenuButton(true)
        } else if (dy < 0 && fab.isMenuButtonHidden) {
            fab.showMenuButton(true)
        }
    }
}