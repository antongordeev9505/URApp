package com.example.ulybkaradugiapp.di

import com.example.ulybkaradugiapp.api.GetDocumentsApi
import com.example.ulybkaradugiapp.api.GetDocumentsApi.Companion.login
import com.example.ulybkaradugiapp.api.GetDocumentsApi.Companion.password
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(buildAuthorizationInterceptor())
            .build()

    private fun buildAuthorizationInterceptor() = object : Interceptor {

        private var credentials: String = Credentials.basic(login, password)

        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            val new = request.newBuilder()
                .addHeader("Authorization", credentials)
                .build()
            return chain.proceed(new)
        }
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .client(provideClient())
            .baseUrl(GetDocumentsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideGetDocumentsApi(retrofit: Retrofit) : GetDocumentsApi =
        retrofit.create(GetDocumentsApi::class.java)
}