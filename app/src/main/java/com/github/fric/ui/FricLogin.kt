package com.github.fric.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.fric.R

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: (username: String, password: String) -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: (username: String, password: String) -> Unit
) {
    var isRegisteringUser by remember { mutableStateOf(false) }
    if (!isRegisteringUser) {
        LoginScreen(
            modifier = modifier,
            onRegisterClick = { isRegisteringUser = true },
            onForgotPasswordClick = onForgotPasswordClick,
            onLoginClick = onLoginClick
        )
    } else {
        RegisterScreen(
            onRegisterClick = onRegisterClick
        )
    }

}

@Composable
fun RegisterScreen(onRegisterClick: (username: String, password: String) -> Unit) {
    var username: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Username TextField
        OutlinedTextField(
            value = username, // Replace with your state variable
            onValueChange = { username = it.trim() },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))// Password TextField
        OutlinedTextField(
            value = password, // Replace with your state variable
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))// Password TextField
        // Register Button
        Button(
            onClick = { onRegisterClick(username, password) },
            modifier = Modifier
                .padding(16.dp),
            colors = ButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            )
        ) {
            Text("Register")
        }

    }
}

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: (username: String, password: String) -> Unit
) {
    var username: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }
    Box(modifier = modifier.fillMaxSize()) {
        // Background image
        Image(
            painter = painterResource(id = R.drawable.fric_bg), // Replace with your image
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Username TextField
            OutlinedTextField(
                value = username, // Replace with your state variable
                onValueChange = { username = it.trim() },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))// Password TextField
            OutlinedTextField(
                value = password, // Replace with your state variable
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot Password Text
            Text(
                text = "Forgot Password?",
                modifier = Modifier
                    .clickable { onForgotPasswordClick() }
                    .align(Alignment.End),
                color = Color.Blue,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            Button(
                onClick = { onLoginClick(username, password) },
                colors = ButtonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                )
            ) {
                Text("Login")
            }
        }

        // Register Button
        Button(
            onClick = { onRegisterClick() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            colors = ButtonColors(
                contentColor = MaterialTheme.colorScheme.primary,
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            )
        ) {
            Text("Register Here")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginScreen(
        onRegisterClick = {},
        onForgotPasswordClick = {},
        onLoginClick = { _, _ -> }
    )
}