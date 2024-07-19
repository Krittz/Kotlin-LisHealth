package com.cran.lis_health

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cran.lis_health.adapter.RegisterAdapter
import com.cran.lis_health.model.Register
import com.cran.lis_health.model.User
import java.time.LocalDateTime

class Registers : Fragment() {
    private lateinit var registerList: RecyclerView
    private lateinit var registerAdapter: RegisterAdapter
    private var registerItems = listOf<Register>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            registerItems = listOf(
                Register(1, "Titulo 1", "Descrição 1", LocalDateTime.now()),
                Register(2, "Titulo 2", "Descrição 2", LocalDateTime.now()),
                Register(2, "Titulo 2", "Descrição 2", LocalDateTime.now()),
                Register(2, "Titulo 2", "Descrição 2", LocalDateTime.now()),
                Register(2, "Titulo 2", "Descrição 2", LocalDateTime.now()),
                Register(2, "Titulo 2", "Descrição 2", LocalDateTime.now()),
                Register(2, "Titulo 2", "Descrição 2", LocalDateTime.now()),
                Register(2, "Titulo 2", "Descrição 2", LocalDateTime.now()),
                Register(2, "Titulo 2", "Descrição 2", LocalDateTime.now()),
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registers, container, false)
        registerList = view.findViewById(R.id.registerList)
        registerList.layoutManager = LinearLayoutManager(context)
        registerAdapter = RegisterAdapter(registerItems)
        registerList.adapter = registerAdapter

        return view
    }
}
