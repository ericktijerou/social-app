package erick.tijerou.socialapp.data.network.di

import erick.tijerou.socialapp.data.network.utils.buildOkHttpClient
import erick.tijerou.socialapp.data.network.utils.buildRetrofit
import erick.tijerou.socialapp.data.network.api.SocialApi
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val NAMED = "SOCIAL"
const val NAMED_CLIENT = "SOCIAL_CLIENT"

internal val networkModule = module {
    single(named(NAMED_CLIENT)) { createHttpClient() }
    single(named(NAMED)) { createRetrofit(get(named(NAMED_CLIENT))) }
    single { provideApi(get(named(NAMED))) }
}

private fun createHttpClient(): OkHttpClient {
    return buildOkHttpClient()
}

private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return buildRetrofit(okHttpClient)
}

private fun provideApi(retrofit: Retrofit): SocialApi {
    return retrofit.create(SocialApi::class.java)
}