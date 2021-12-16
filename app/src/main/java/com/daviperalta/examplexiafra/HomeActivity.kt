package com.daviperalta.examplexiafra

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.daviperalta.examplexiafra.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType{
    BASIC
}

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val correo:String? = bundle?.getString("correo") ?: ""
        val provider:String? = bundle?.getString("provider") ?: ""

        binding.tvUsuario.text= correo
        title = "Ing.Sistemas"
        binding.btnCerrarSesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        binding.btn2.setOnClickListener {
            validarinput()
        }

        binding.btn1.setOnClickListener {
            mensajeModal()
        }
    }

    private fun validarinput(){
        if(binding.etCampo1.text.isNotEmpty()){
            binding.tvMostrar.text = binding.etCampo1.text
        }else{
            Toast.makeText(this,"Favor de ingresar un mensaje",Toast.LENGTH_LONG).show()
        }
    }

    private fun mensajeModal(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Mensaje")
        builder.setMessage("ISC")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}