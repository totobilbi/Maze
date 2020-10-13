package com.example.maze

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.maze.R.drawable

class ImageAdapter internal constructor(private val mContext: Context?) : PagerAdapter() {
    override fun getCount(): Int {
        return mImageIds.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(mContext)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.setImageResource(mImageIds[position])
        container.addView(imageView, 0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ImageView)
    }

    companion object {
        private var mImageIds = intArrayOf(drawable.grassland_background, drawable.world_locked, drawable.world_locked, drawable.world_locked, drawable.world_locked)
        var images: IntArray
            get() = mImageIds
            set(mImageIds) {
                Companion.mImageIds = mImageIds
            }

    }

}