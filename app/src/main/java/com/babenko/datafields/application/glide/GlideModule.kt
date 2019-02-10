package com.babenko.datafields.application.glide

import android.content.Context
import android.util.Log
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.executor.GlideExecutor.newDiskCacheExecutor
import com.bumptech.glide.load.engine.executor.GlideExecutor.newSourceExecutor
import com.bumptech.glide.module.AppGlideModule
import timber.log.Timber

@GlideModule
class GlideModule : AppGlideModule() {

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
        builder.setMemoryCache(LruResourceCache(RESOURCES_CACHE_SIZE))
        builder.setBitmapPool(LruBitmapPool(BITMAP_POOL_SIZE))
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE))
        builder.setLogLevel(Log.DEBUG)

        builder.setDiskCacheExecutor(newDiskCacheExecutor({ this.handleThrowable(it) }))
        builder.setSourceExecutor(newSourceExecutor({ this.handleThrowable(it) }))
    }

    private fun handleThrowable(t: Throwable) {
        Timber.e(t)
    }

    companion object {
        private const val RESOURCES_CACHE_SIZE = 10 * 1024 * 1024L
        private const val BITMAP_POOL_SIZE = 30 * 1024 * 1024L
        private const val DISK_CACHE_SIZE = 40 * 1024 * 1024L

        private val TAG = "GlideModule"
    }
}
