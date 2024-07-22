package com.cran.lis_health.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.cran.lis_health.R
import com.cran.lis_health.controller.SmsController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Home : Fragment() {

    private lateinit var smsController: SmsController

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        smsController = SmsController(requireContext(), requireActivity())

        val btnAlert: AppCompatImageButton = view.findViewById(R.id.btnAlert)
        btnAlert.setOnClickListener {
            val phoneNumber = "5538999576845"
            val message = "Oi, preciso de ajuda, minha localização:"
            smsController.sendSMS(phoneNumber, message)

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                == PackageManager.PERMISSION_GRANTED
            ) {

                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        location?.let {
                            val latitude = it.latitude
                            val longitude = it.longitude
                            val sms =
                                "https://www.google.com/maps/search/?api=1&query=$latitude,$longitude"
                            Log.d("Local", "$latitude, $longitude")
                            smsController.sendSMS(phoneNumber, sms)


                        }
                    }
                    .addOnFailureListener { e ->
                        // Handle failure
                    }
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION_PERMISSION
                )
            }
        }

        return view
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, tentar obter a localização novamente após enviar o SMS
                // O código para obter a localização já está dentro do `OnClickListener` do botão `btnAlert`
            } else {
                // Permissão negada, informar ao usuário ou tratar de outra forma
            }
        }
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 100
    }
}
