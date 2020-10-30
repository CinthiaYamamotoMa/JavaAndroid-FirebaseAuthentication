package com.google.codelabs.mdc.java.firebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.codelabs.mdc.java.firebaseAuth.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shr_login_fragment);

        findViewById(R.id.textViewSignup).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.textViewSignup:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }
}
