package com.example.rma_zavrsni_projekat.data.repository

import android.content.Context
import com.example.rma_zavrsni_projekat.data.local.DatabaseProvider
import com.example.rma_zavrsni_projekat.data.local.entity.toEntity
import com.example.rma_zavrsni_projekat.data.local.entity.toIdCard
import com.example.rma_zavrsni_projekat.data.model.IdCard
import com.example.rma_zavrsni_projekat.data.network.RetrofitInstance
import com.example.rma_zavrsni_projekat.data.network.dto.RequestDto
import com.example.rma_zavrsni_projekat.util.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IdCardRepository {

    suspend fun fetchIdCards(context: Context, request: RequestDto): Result<List<IdCard>> {
        return withContext(Dispatchers.IO) {
            val dao = DatabaseProvider.getDatabase(context).idCardDao()

            if (isInternetAvailable(context)) {
                try {
                    val response = RetrofitInstance.api.getIdCards(request)
                    if (response.success) {
                        val data = response.result.map { it.toIdCard() }

                        // Cache result in Room
                        dao.clear()
                        dao.insertAll(data.map { it.toEntity() })

                        Result.success(data)
                    } else {
                        Result.failure(Exception("API call failed: ${response.errors.joinToString()}"))
                    }
                } catch (e: Exception) {
                    // Fallback to Room cache
                    val cached = dao.getAll().map { it.toIdCard() }
                    if (cached.isNotEmpty()) {
                        Result.success(cached)
                    } else {
                        Result.failure(e)
                    }
                }
            } else {
                // Offline: use cache only
                val cached = dao.getAll().map { it.toIdCard() }
                if (cached.isNotEmpty()) {
                    Result.success(cached)
                } else {
                    Result.failure(Exception("No internet and no cached data available"))
                }
            }
        }
    }
}
