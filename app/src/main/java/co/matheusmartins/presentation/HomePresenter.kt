package co.matheusmartins.presentation

import android.os.Handler
import android.os.Looper
import co.matheusmartins.model.Category
import co.matheusmartins.view.CategoryItem
import co.matheusmartins.view.HomeFragment
import java.net.CacheResponse

class HomePresenter(private val view: HomeFragment) {

    // VIEW <- PRESENTER
    // PRESENTER <- VIEW

    fun findAllCategories() {
        view.showProgress()
        fakeRequest()
    }

    // Output (SUCESSO | FALHA | COMPLETE)
    fun onSuccess(response: List<String>) {
//        val categories = mutableListOf<CategoryItem>()
//
//        for (category in response) {
//            categories.add(CategoryItem(category))
//        }
        val categories = response.map { Category(it, 0xFFFF0000) }

        view.showCategories(categories)
    }

    fun onError(message: String) {
        view.showFailure(message)
    }

    fun onComplete() {
        view.hideProgress()
    }

    // simular uma requisição HTTP
    private fun fakeRequest() {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4"
            )
            // aqui a lista está pronta (response)

            // Devolver sucesso ou falha
            onSuccess(response)
//            onError("FALHA MA CONEXÃO, TENTE NOVAMENTE MAIS TARDE!")
        }, 2000)
    }
}