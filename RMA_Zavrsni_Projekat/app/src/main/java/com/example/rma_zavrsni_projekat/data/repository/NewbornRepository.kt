package com.example.rma_zavrsni_projekat.data.repository

import com.example.rma_zavrsni_projekat.data.local.DatabaseProvider
import com.example.rma_zavrsni_projekat.data.model.Newborn
import com.example.rma_zavrsni_projekat.data.network.RetrofitInstance
import com.example.rma_zavrsni_projekat.data.network.dto.RequestDto
import com.example.rma_zavrsni_projekat.data.local.entity.toEntity
import com.example.rma_zavrsni_projekat.data.local.entity.toNewborn
import com.example.rma_zavrsni_projekat.util.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.content.Context

class NewbornRepository {
    // Suspending functions can be paused/resumed == async
    suspend fun fetchNewborns(context: Context, request: RequestDto): Result<List<Newborn>> {
        return withContext(Dispatchers.IO) {
            val dao = DatabaseProvider.getDatabase(context).newbornDao()

            if (isInternetAvailable(context)) {
                try {
                    val response = RetrofitInstance.api.getNewborns(request)
                    if (response.success) {
                        val data = response.result.map { it.toNewborn() }

                        // Cache to Room
                        dao.clear()
                        dao.insertAll(data.map { it.toEntity() })

                        Result.success(data)
                    } else {
                        Result.failure(Exception("API call failed: ${response.errors.joinToString()}"))
                    }
                } catch (e: Exception) {
                    // fallback to cache
                    val cached = dao.getAll().map { it.toNewborn() }
                    if (cached.isNotEmpty()) {
                        Result.success(cached)
                    } else {
                        Result.failure(e)
                    }
                }
            } else {
                // offline: return cached if exists
                val cached = dao.getAll().map { it.toNewborn() }
                if (cached.isNotEmpty()) {
                    Result.success(cached)
                } else {
                    Result.failure(Exception("No internet and no cached data."))
                }
            }
        }
    }
}




