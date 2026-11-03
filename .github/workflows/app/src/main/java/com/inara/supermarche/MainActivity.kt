package com.inara.supermarche

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
 override fun onCreate(b: Bundle?) {
  super.onCreate(b)
  setContent {
   val nav = rememberNavController()
   NavHost(nav, "bienvenue"){
    composable("bienvenue"){
     Column(Modifier.fillMaxSize().background(Color(0xFF00695C)).padding(24.dp), Arrangement.Center){
      Text("L'EQUIPE INARA", color=Color(0xFFFFC107), style=MaterialTheme.typography.displaySmall)
      Text("SUPERMARCHÉ Tit Mellil", color=Color.White)
      Spacer(Modifier.height(20.dp))
      Button({nav.navigate("creer")}, Modifier.fillMaxWidth()){Text("Créer compte +212")}
      OutlinedButton({nav.navigate("admin")}, Modifier.fillMaxWidth()){Text("Login Admin")}
     }
    }
    composable("creer"){
     var phone by remember{mutableStateOf("")}
     Column(Modifier.padding(24.dp)){
      Text("Créer compte +212")
      OutlinedTextField(phone, {phone=it}, label={Text("+212 6XX XX XX XX")}, Modifier.fillMaxWidth())
      Button({nav.navigate("verif/$phone")}, Modifier.fillMaxWidth()){Text("Envoyer SMS")}
     }
    }
    composable("verif/{phone}"){ back->
     var code by remember{mutableStateOf("")}
     Column(Modifier.padding(24.dp)){
      Text("Code SMS 6 chiffres envoyé")
      OutlinedTextField(code, {code=it}, label={Text("Code")}, Modifier.fillMaxWidth())
      Button({nav.navigate("carte")}, Modifier.fillMaxWidth()){Text("Vérifier")}
     }
    }
    composable("admin"){
     var secret by remember{mutableStateOf("")}
     var err by remember{mutableStateOf("")}
     Column(Modifier.padding(24.dp)){
      Text("Code Secret Admin")
      OutlinedTextField(secret, {secret=it}, label={Text("INARA-ADMIN-2026")}, Modifier.fillMaxWidth())
      Text(err, color=Color.Red)
      Button({
       if(secret=="INARA-ADMIN-2026") nav.navigate("carte") else err="Code faux"
      }, Modifier.fillMaxWidth()){Text("Login")}
     }
    }
    composable("carte"){
     Column(Modifier.padding(16.dp)){
      Text("Carte Magasin", style=MaterialTheme.typography.headlineSmall)
      Card(Modifier.fillMaxWidth().padding(8.dp)){ Column(Modifier.padding(16.dp)){
       Text("Rayon: Rayon Laitier")
       Text("Allée: Allée 3")
       Text("Étagère: Étagère 2")
       Text("Tit Mellil - Casablanca")
      }}
      Button({nav.navigate("produit")}, Modifier.fillMaxWidth()){Text("Voir: Lait 1L - 16.50 DH")}
     }
    }
    composable("produit"){
     Column(Modifier.padding(16.dp)){
      Text("Lait Entier 1L", style=MaterialTheme.typography.headlineMedium)
      Text("16.50 DH", color=MaterialTheme.colorScheme.primary)
      Text("Emplacement: Allée 3 | Étagère 2")
     }
    }
   }
  }
 }
}
