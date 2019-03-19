package br.com.study.view.characterslist

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import android.util.Log
import br.com.pagelibrary.model.paging.CharacterDataSourceFactory
import br.com.study.model.api.MarvelApi
import br.com.study.model.entity.Character
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterViewModel : ViewModel(){
    var characterList: Observable<PagedList<Character>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 20
    private val sourceFactory: CharacterDataSourceFactory

    init {
        sourceFactory = CharacterDataSourceFactory(compositeDisposable, MarvelApi.getService())
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 3)
            .setPrefetchDistance(10)
            .setEnablePlaceholders(false)
            .build()

        characterList = RxPagedListBuilder(sourceFactory, config)
            .setNotifyScheduler(Schedulers.io())
            .buildObservable()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}