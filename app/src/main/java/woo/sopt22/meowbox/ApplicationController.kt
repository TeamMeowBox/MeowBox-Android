package woo.sopt22.meowbox

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import woo.sopt22.meowbox.Network.NetworkService

class ApplicationController : Application() {

    lateinit var networkService : NetworkService
    private val baseUrl = "http://52.79.252.44:3000/"
    companion object {
        lateinit var instance : ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetwork()
    }

    fun buildNetwork(){

        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        networkService = retrofit.create(NetworkService::class.java)
    }
}

