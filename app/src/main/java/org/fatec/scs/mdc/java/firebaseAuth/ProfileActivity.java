package com.google.codelabs.mdc.java.firebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);

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
