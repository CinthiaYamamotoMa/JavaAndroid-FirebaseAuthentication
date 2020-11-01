package org.fatec.scs.mdc.java.firebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.codelabs.mdc.java.firebaseAuth.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        setContentView(R.layout.welcome_activity);
        TextView textView = (TextView) findViewById(R.id.acesso);
        textView.setText("Bem-vindo " + user.getEmail() + ". Você acessou sua conta");

        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.registrar).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.registrar:
                finish();
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }
}
