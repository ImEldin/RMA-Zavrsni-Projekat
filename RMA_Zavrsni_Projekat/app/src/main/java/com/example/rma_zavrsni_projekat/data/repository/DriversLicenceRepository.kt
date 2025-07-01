package com.example.rma_zavrsni_projekat.data.repository

import android.content.Context
import com.example.rma_zavrsni_projekat.data.local.DatabaseProvider
import com.example.rma_zavrsni_projekat.data.local.entity.toDriversLicence
import com.example.rma_zavrsni_projekat.data.local.entity.toEntity
import com.example.rma_zavrsni_projekat.data.model.DriversLicence
import com.example.rma_zavrsni_projekat.data.network.RetrofitInstance
import com.example.rma_zavrsni_projekat.data.network.dto.RequestDto
import com.example.rma_zavrsni_projekat.util.isInternetAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DriversLicenceRepository {

    suspend fun fetchDriversLicences(context: Context, request: RequestDto): Result<List<DriversLicence>> {
        return withContext(Dispatchers.IO) {
            val dao = DatabaseProvider.getDatabase(context).driversLicenceDao()

            if (isInternetAvailable(context)) {
                try {
                    val response = RetrofitInstance.api.getDriverLicences(request)
                    if (response.success) {
                        val data = response.result.map { it.toDriversLicence() }

                        // Cache to Room
                        dao.clear()
                        dao.insertAll(data.map { it.toEntity() })

                        Result.success(data)
                    } else {
                        Result.failure(Exception("API call failed: ${response.errors.joinToString()}"))
                    }
                } catch (e: Exception) {
                    val cached = dao.getAll().map { it.toDriversLicence() }
                    if (cached.isNotEmpty()) {
                        Result.success(cached)
                    } else {
                        Result.failure(e)
                    }
                }
            } else {
                val cached = dao.getAll().map { it.toDriversLicence() }
                if (cached.isNotEmpty()) {
                    Result.success(cached)
                } else {
                    Result.failure(Exception("No internet and no cached data available"))
                }
            }
        }
    }
}
