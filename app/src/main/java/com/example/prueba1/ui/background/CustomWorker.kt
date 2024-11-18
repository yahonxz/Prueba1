package com.example.prueba1.ui.background

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class CustomWorker constructor(
    context: Context,
    workerParameters: WorkerParameters,
):CoroutineWorker(context,workerParameters){
    override suspend fun doWork(): Result{
        println("Hello from worker!")
        return Result.success()
    }
}