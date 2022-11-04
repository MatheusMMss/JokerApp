package co.matheusmartins.presentation

import android.os.Handler
import android.os.Looper
import co.matheusmartins.data.CategoryRemoteDataSource
import co.matheusmartins.data.ListCategoryCallback
import co.matheusmartins.model.Category
import co.matheusmartins.view.CategoryItem
import co.matheusmartins.view.HomeFragment
import java.net.CacheResponse

class HomePresenter(
    private val view: HomeFragment,
    private val dataSource: CategoryRemoteDataSource = CategoryRemoteDataSource()
) : ListCategoryCallback {

    // VIEW <- PRESENTER
    // PRESENTER <- VIEW

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    // Output (SUCESSO | FALHA | COMPLETE)
    override fun onSuccess(response: List<String>) {
//        val categories = mutableListOf<CategoryItem>()
//
//        for (category in response) {
//            categories.add(CategoryItem(category))
//        }
        val categories = response.map { Category(it, 0xFFFF0000) }

        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}