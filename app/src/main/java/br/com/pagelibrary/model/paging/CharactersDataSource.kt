package br.com.pagelibrary.model.paging

import android.arch.paging.PageKeyedDataSource
import android.util.Log
import br.com.study.model.api.MarvelApi
import br.com.study.model.entity.Character
import io.reactivex.disposables.CompositeDisposable

class CharactersDataSource(
    private val marveLApi: MarvelApi,
    private val compositeDisposable: CompositeDisposable
): PageKeyedDataSource<Int,Character>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>) {
        createObservale(0, 1,  params.requestedLoadSize, callback,null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        val page = params.key
        createObservale(page, page+1,  params.key, null,callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        val page = params.key
        createObservale(page, page-1,  params.key, null,callback)
    }

    private fun createObservale( requestedPage: Int,
                                 nextPage: Int,
                                 quantityItem: Int,
                                 initCallback: LoadInitialCallback<Int, Character>?,
                                 calback:  LoadCallback<Int, Character>?){
        compositeDisposable.add(
            marveLApi.allCharacters( requestedPage + quantityItem)
                .subscribe {
                    Log.d("PJO", "Loading page: $requestedPage")
                    initCallback?.onResult(it.data.results, null, nextPage)
                    calback?.onResult( it.data.results, nextPage)
                }
        )
    }
}





