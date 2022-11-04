package co.matheusmartins.data

import android.os.Handler
import android.os.Looper

class CategoryRemoteDataSource {

    fun findAllCategories(callback: ListCategoryCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            val response = arrayListOf(
                "Categoria 1",
                "Categoria 2",
                "Categoria 3",
                "Categoria 4"
            )
            // aqui a lista está pronta (response)

            // Devolver sucesso ou falha
            callback.onSuccess(response)
//            onError("FALHA MA CONEXÃO, TENTE NOVAMENTE MAIS TARDE!")

            callback.onComplete()
        }, 2000)
    }

}