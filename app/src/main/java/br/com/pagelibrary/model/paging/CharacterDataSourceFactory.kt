package br.com.pagelibrary.model.paging

import android.arch.paging.DataSource
import br.com.study.model.api.MarvelApi
import br.com.study.model.entity.Character
import io.reactivex.disposables.CompositeDisposable

class CharacterDataSourceFactory (
    private val compositeDisposable: CompositeDisposable,
    private  val marvelApi: MarvelApi
) : DataSource.Factory<Int, Character>() {

    override fun create(): DataSource<Int, Character> {
    return CharactersDataSource(marvelApi, compositeDisposable)
    }
}