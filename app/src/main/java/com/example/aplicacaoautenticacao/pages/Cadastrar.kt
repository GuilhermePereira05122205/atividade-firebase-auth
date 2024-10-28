package com.example.aplicacaoautenticacao.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun Cadastrar(db: FirebaseAuth, padding: PaddingValues){

    var email = remember{ mutableStateOf("") }
    var password = remember { mutableStateOf("") }

    var texto = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(padding)
    ) {
        Text("Email:")
        TextField(modifier = Modifier.fillMaxWidth().padding(10.dp), value = email.value, onValueChange = {email.value = it})
        Text("Password:")
        TextField(modifier = Modifier.fillMaxWidth().padding(10.dp), value = password.value, onValueChange = {password.value = it}, visualTransformation = PasswordVisualTransformation(), keyboardOptions= KeyboardOptions(keyboardType= KeyboardType.Password))
        Row(

            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ){
            Button(onClick = {
                db.createUserWithEmailAndPassword(email.value, password.value).addOnSuccessListener {
                    texto.value = "Usuario cadastrado com sucesso"
                }.addOnFailureListener {
                    texto.value = "Erro ao cadastrar usuario"
                }
            }) {
                Text("Cadastrar")
            }
        }
        Text(texto.value)
    }
}