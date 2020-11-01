package com.google.codelabs.mdc.java.firebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.codelabs.mdc.java.firebaseAuth.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText editTextEmail, editTextPassword;
//    ContentLoadingProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shr_login_fragment);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (TextInputEditText) findViewById(R.id.email_edit_text);
        editTextPassword = (TextInputEditText) findViewById(R.id.password_edit_text);
//        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        TextView textViewLogin = (TextView) findViewById((R.id.textViewSignup));
        SpannableString content = new SpannableString("Ainda não possui uma conta? Registre-se");
        content.setSpan(new UnderlineSpan(), 28, content.length(), 0);
        textViewLogin.setText(content);

        findViewById(R.id.entrar_button).setOnClickListener(this);
        findViewById(R.id.textViewSignup).setOnClickListener(this);
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Digite o e-mail");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Digite um e-mail válido");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Digite a senha");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("A senha contém, no mínimo, 6 caracteres");
            editTextPassword.requestFocus();
            return;
        }

//        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
//                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

//        if (mAuth.getCurrentUser() != null) {
//            finish();
//            startActivity(new Intent(this, SignUpActivity.class));
//        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.textViewSignup:
                finish();
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case R.id.entrar_button:
                userLogin();
                break;
        }
    }
}
