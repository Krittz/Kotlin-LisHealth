package com.cran.lis_health.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import com.cran.lis_health.R
import com.cran.lis_health.controller.SmsController


class Menu : Fragment() {

    private lateinit var smsController: SmsController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)


        smsController = SmsController(requireContext(), requireActivity())

        val btnAlert: AppCompatImageButton = view.findViewById(R.id.btnAlert)
        btnAlert.setOnClickListener {
            val phoneNumber =
                "5538999576845"
            val message = "Olá, isso é um teste de envio de SMS"
            smsController.sendSMS(phoneNumber, message)
        }

        return view
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        smsController.handlePermissionResult(requestCode, grantResults)
    }
}
