package com.firstapp.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private lateinit var dataBase:DatabaseReference
    private var registerValue = 0
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        init()

    }
    private fun init(){
        dataBase = FirebaseDatabase.getInstance().getReference("Users")

        registerOfBuyer.setOnClickListener{
            loginPage.visibility = View.GONE
            registerPage.visibility = View.VISIBLE
            registerValue = 1

        }
        registerOfSeller.setOnClickListener{
            loginPage.visibility = View.GONE
            registerPage.visibility = View.VISIBLE
            registerValue = 2

        }
back.setOnClickListener{
    loginPage.visibility = View.VISIBLE
    registerPage.visibility = View.GONE

}
        auth = FirebaseAuth.getInstance()
        loginButton.setOnClickListener{
            auth.signInWithEmailAndPassword(loginEmail.text.toString(),loginPass.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        startActivity(Intent(this,FlowerViewActivity::class.java))

                    }else{
                        Toast.makeText(this,"login failed",Toast.LENGTH_LONG).show()
                    }
                }

        }
        registerButton.setOnClickListener{
            if(regEmail.text.isNotEmpty()||regPass.text.isNotEmpty()||nickname.text.isNotEmpty()){
                if(validPassword(regPass.text.toString()) &&validEmail(regEmail.text.toString())){
                    auth.createUserWithEmailAndPassword(regEmail.text.toString(),regPass.text.toString())
                        .addOnCompleteListener{
                            if(it.isSuccessful){
                                val user = User(regEmail.text.toString(),regPass.text.toString(),registerValue.toString(),nickname.text.toString())
                                dataBase.child(nickname.text.toString()).setValue(user).addOnSuccessListener {
                                    Toast.makeText(this,"Register successfuly",Toast.LENGTH_LONG).show()
                                    loginPage.visibility = View.VISIBLE
                                    registerPage.visibility = View.GONE


                                }.addOnFailureListener{
                                    Toast.makeText(this,"Register Failed",Toast.LENGTH_LONG).show()

                                }




                            }else{
                                Toast.makeText(this,"Register Failed",Toast.LENGTH_LONG).show()
                            }
                        }

                }else{
                    Toast.makeText(this,"incorect email or password",Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(this,"complite entrie",Toast.LENGTH_LONG).show()

            }
        }

    }
    private fun validPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.contains(' ')) return false
        var hasDigit: Boolean = false
        var hasLetter: Boolean = false
        for (ch in password) {
            if (ch.isDigit()) hasDigit = true
            if (ch.isLetter()) hasLetter = true
        }
        return (hasDigit && hasLetter)
    }

    private fun validEmail(target: CharSequence): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }
}