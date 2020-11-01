package com.google.codelabs.mdc.java.firebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ContentLoadingProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.codelabs.mdc.java.firebaseAuth.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText editTextEmail, editTextPassword;
//    ContentLoadingProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        editTextEmail = (TextInputEditText) findViewById(R.id.email_edit_text);
        editTextPassword = (TextInputEditText) findViewById(R.id.password_edit_text);
//        progressBar = (ContentLoadingProgressBar) findViewById(R.id.progress_bar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.registrar_button).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()) {
            editTextEmail.setError("Preencha o e-mail");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Digite um e-mail válido");
            editTextEmail.requestFocus();
            return;
        }

        if(password.isEmpty()) {
            editTextPassword.setError("Preencha a senha");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() < 6) {
            editTextPassword.setError("A senha deve conter no mínimo 6 caracteres");
            editTextPassword.requestFocus();
            return;
        }

//        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
//                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        finish();
                        startActivity(new Intent(SignUpActivity.this, ProfileActivity.class));
                    } else {
                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                            Toast.makeText(getApplicationContext(), "Registro realizado com sucesso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Algum erro ocorreu", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
    }

    @Override
    public void onClick(View view) {
        Log.d("INFO", "Switch");
        switch(view.getId()) {

            case R.id.registrar_button:
                registerUser();
                break;
            case R.id.textViewLogin:
                finish();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
