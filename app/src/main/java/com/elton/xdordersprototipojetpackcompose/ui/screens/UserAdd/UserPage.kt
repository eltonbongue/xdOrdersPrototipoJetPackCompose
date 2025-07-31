
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.elton.xdordersprototipojetpackcompose.data.local.DAO
import com.elton.xdordersprototipojetpackcompose.data.local.DatabaseHelper
import com.elton.xdordersprototipojetpackcompose.ui.components.TopBarXD


@Composable
fun UserPageScreen(
    navController: NavController,
    dbHelper: DatabaseHelper,
    onSaveClick: (String, String, String) -> Unit = { _, _, _ -> },
    onCancelClick: () -> Unit = {}
) {

    val dao = remember { DAO(dbHelper) }
    val context = LocalContext.current

    // Estados para os campos de entrada
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopBarXD(
                title = "Casdastrar/User",
                navController = navController
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    ,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                Text(

                    text = "Cadastro de Usuário",
                    style = MaterialTheme.typography.headlineMedium

                )

                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nome") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OutlinedButton(onClick = onCancelClick)
                         {
                        Text("Cancelar")
                    }
                    Button(
                        onClick = {

                            val success = dao.insertUser (name, email, password)

                            if( success) {
                                Toast.makeText(context, "Usuário salvo com sucesso!", Toast.LENGTH_SHORT).show()
                                onCancelClick()
                                  }

                            else {
                                Toast.makeText(context, "Erro ao salvar usuário", Toast.LENGTH_SHORT).show()
                            }
                                  },
                        enabled = name.isNotBlank() && email.isNotBlank() && password.isNotBlank()
                    ) {
                        Text("Salvar")
                    }
                }
            }
        }
    )
}
