package co.matheusmartins.presentation

import co.matheusmartins.data.JokeCallback
import co.matheusmartins.data.JokeDayRemoteDataSource
import co.matheusmartins.data.JokeRemoteDataSource
import co.matheusmartins.model.Joke
import co.matheusmartins.view.JokeDayFragment
import co.matheusmartins.view.JokeFragment

class JokeDayPresenter(
    private val view: JokeDayFragment,
    private val dataSource: JokeDayRemoteDataSource = JokeDayRemoteDataSource()
) : JokeCallback {

    fun findRandom() {
        view.showProgress()
        dataSource.findRandom(this)
    }

    override fun onSuccess(response: Joke) {
        view.showJoke(response)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }


}