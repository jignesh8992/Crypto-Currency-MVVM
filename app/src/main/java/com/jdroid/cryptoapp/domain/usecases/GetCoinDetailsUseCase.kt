package com.jdroid.cryptoapp.domain.usecases


import com.jdroid.cryptoapp.R
import com.jdroid.cryptoapp.common.Resource
import com.jdroid.cryptoapp.domain.model.CoinDetail
import com.jdroid.cryptoapp.domain.model.toCoinDetail
import com.jdroid.cryptoapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinDetailsUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoin(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.ServerError(R.string.server_error, "HttpException: ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.ServerError(R.string.server_error, "IOException: ${e.localizedMessage}"))
        } catch (e: Exception) {
            emit(Resource.Error(message = "Exception: ${e.localizedMessage}"))
        }

    }
}