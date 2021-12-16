package com.daviperalta.examplexiafra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.daviperalta.examplexiafra.databinding.ActivityQuimicaBinding
import com.google.firebase.auth.FirebaseAuth

class QuimicaActivity : AppCompatActivity() {
    private lateinit var binding : ActivityQuimicaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuimicaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recibo los datos del login
        val bundle: Bundle? = intent.extras
        val correo:String? = bundle?.getString("correo") ?: ""
        val provider:String? = bundle?.getString("provider") ?: ""

        title = "Ing.Quimica"
        binding.tvCorreo.text = correo

        //onclick Boton1
        binding.btn1Qui.setOnClickListener {
            val campo1 = binding.etQCampo1.text.toString()
            if (campo1.isNotEmpty()){
                mensajeModal(campo1)
            }else{
                mensajeToast("Agregar Mensaje en el Campo1")
            }
        }

        binding.etQCampo2.addTextChangedListener {
            binding.tvMensajeQ.text = it.toString()
        }

        //onclick boton cerrar session
        binding.btnCerrarSessionQuimica.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

    }

    //funcion que muestra un modal con un mensaje
    private fun mensajeModal(campo1 :String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Mensaje")
        builder.setMessage("Ing.Quimica $campo1")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //funcion de mensaje toast personalizado
    private fun mensajeToast(mensaje:String){
        Toast.makeText(this,"$mensaje",Toast.LENGTH_SHORT).show()
    }
}