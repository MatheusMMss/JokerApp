package co.matheusmartins.presentation

import android.graphics.Color
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
        val start = 40 // H - matiz
        val end = 190 // H - matiz
        val diff = (end - start) / response.size

        val categories = response.mapIndexed { index, s ->
            val hsv = floatArrayOf(
                start + (diff * index).toFloat(),
                100.0f,
                100.0f,
            )
            Category(s, Color.HSVToColor(hsv).toLong())
        }

        view.showCategories(categories)
    }

    override fun onError(response: String) {
        view.showFailure(response)
    }

    override fun onComplete() {
        view.hideProgress()
    }

}