package com.example.ulybkaradugiapp.other

import kotlinx.coroutines.flow.*

//ResultType - get from db
//RequestType - get from api
//кэширование данных
inline fun <ResultType, RequestType> networkBoundResource(
    //db - получение данных из бд
    crossinline query: () -> Flow<ResultType>,
    //api - получение данных с сервера
    crossinline fetch: suspend () -> RequestType,
    //store to db - сохранение полученных данных с сервера в бд
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    //fetch data from api or not - решение о получении данных с сервера
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}