package com.daviperalta.examplexiafra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.daviperalta.examplexiafra.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var binding : ActivityLoginBinding
    private var item : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val carreras : List<String> = listOf("Ing.Sistemas Computacionales", "Ing.Quimica")
        val adapterCombo = ArrayAdapter(this,R.layout.lista_carreras,carreras)
        with(binding.cmbCarrera){
            setAdapter(adapterCombo)
            onItemClickListener = this@LoginActivity
        }

        setup()


    }

    private fun setup() {
        title = "Autenticación"

        binding.btnRegistrar.setOnClickListener {
            if (binding.etCorreo.text.isNotEmpty() && binding.etPassword.text.isNotEmpty() && binding.cmbCarrera.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(binding.etCorreo.text.toString(),
                    binding.etPassword.text.toString()).addOnCompleteListener{
                        if(it.isSuccessful){
                            if(binding.cmbCarrera.text.toString() == "Ing.Sistemas Computacionales"){
                                showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                            }else if(binding.cmbCarrera.text.toString() == "Ing.Quimica"){
                                showQuimica(it.result?.user?.email?:"",ProviderType.BASIC)
                            }else{
                                Toast.makeText(this,"${binding.cmbCarrera.text}",Toast.LENGTH_LONG).show()
                            }

                        }else{
                            showAlert()
                        }
                    }
            }
        }
        binding.btnAcceder.setOnClickListener {
            if (binding.etCorreo.text.isNotEmpty() && binding.etPassword.text.isNotEmpty() && binding.cmbCarrera.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.etCorreo.text.toString(),
                        binding.etPassword.text.toString()).addOnCompleteListener{
                        if(it.isSuccessful){
                            if(binding.cmbCarrera.text.toString() == "Ing.Sistemas Computacionales"){
                                showHome(it.result?.user?.email?:"",ProviderType.BASIC)
                            }else if(binding.cmbCarrera.text.toString() == "Ing.Quimica"){
                                showQuimica(it.result?.user?.email?:"",ProviderType.BASIC)
                            }else{
                                Toast.makeText(this,"${binding.cmbCarrera.text}",Toast.LENGTH_LONG).show()
                            }
                        }else{
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showHome(correo:String, provider : ProviderType){
        val homeIntent = Intent(this,HomeActivity::class.java).apply {
            putExtra("correo",correo)
            putExtra("provider",provider.name)
        }
        startActivity(homeIntent)

    }

    private fun showQuimica(correo:String, provider : ProviderType){
        val QuimicaIntent = Intent(this,QuimicaActivity::class.java).apply {
            putExtra("correo",correo)
            putExtra("provider",provider.name)
        }
        startActivity(QuimicaIntent)
    }

    private fun showAlert(){
        Toast.makeText(this,"Se ha producido un error autenticando el usuario",Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        item = parent?.getItemAtPosition(position).toString()
    }
}