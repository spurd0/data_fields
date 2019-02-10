package com.babenko.datafields.screen.feature.images

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.babenko.datafields.application.DataFieldsApplication
import com.babenko.datafields.application.util.applyIoMainThreadSchedulersToSingle
import com.babenko.datafields.model.interactor.ImagesInteractor
import javax.inject.Inject

class ImagesViewModel : ViewModel() {
    @Inject lateinit var imagesInteractor: ImagesInteractor
    val liveData = MutableLiveData<ImagesViewState>()

    init {
        DataFieldsApplication.appComponent.inject(this)

        requestPictures()
    }

    private fun requestPictures() {
        val d = imagesInteractor.requestPicturesList()
            .compose(applyIoMainThreadSchedulersToSingle())
            .doOnSubscribe { liveData.value = ImagesViewState.LoadingStarted }
            .subscribe(
                {
                    liveData.value = ImagesViewState.Loaded(it)
                },
                { liveData.value = ImagesViewState.Error(it) }
            )
    }
}
