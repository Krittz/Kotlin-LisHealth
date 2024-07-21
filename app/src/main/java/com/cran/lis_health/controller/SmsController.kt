package com.cran.lis_health.controller

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SmsController(private val context: Context, private val activity: Activity) {
    private val SMS_PERMISSION_CODE = 100

    fun sendSMS(phoneNumber: String, message: String) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.SEND_SMS),
                SMS_PERMISSION_CODE
            )
        } else {
            try {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                Toast.makeText(
                    context,
                    "SMS Enviado $message",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Falha ao enviar SMS",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun handlePermissionResult(requestCode: Int, grantResults: IntArray) {
        if (requestCode == SMS_PERMISSION_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // Permissão concedida
            } else {
                // Permissão negada
            }
        }
    }
}