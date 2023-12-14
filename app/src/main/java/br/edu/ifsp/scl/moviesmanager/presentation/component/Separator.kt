package br.edu.ifsp.scl.moviesmanager.presentation.component

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Separator(private val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = padding
    }
}