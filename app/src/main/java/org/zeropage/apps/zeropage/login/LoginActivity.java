package org.zeropage.apps.zeropage.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;

import org.zeropage.apps.zeropage.R;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private Button mLoginButton;
    private EditText mTokenEditText;
    private EditText mUsernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fabric.with(this, new Crashlytics());

        initWidget();
        // TODO : Start Slack Application.
    }

    private void initWidget() {
        mLoginButton = (Button) findViewById(R.id.login_button);
        mTokenEditText = (EditText) findViewById(R.id.token_edit_text);
        mUsernameEditText = (EditText) findViewById(R.id.username_edit_text);
    }
}