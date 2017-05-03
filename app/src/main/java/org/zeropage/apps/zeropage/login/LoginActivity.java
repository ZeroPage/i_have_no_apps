package org.zeropage.apps.zeropage.login;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.main.MainActivity;
import org.zeropage.apps.zeropage.network.common.CallbackWrapper;
import org.zeropage.apps.zeropage.network.function.SignUpRequest;
import org.zeropage.apps.zeropage.network.login.LoginRequest;
import org.zeropage.apps.zeropage.utility.Action;

import io.fabric.sdk.android.Fabric;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

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
        launchSlackApplication();
        setListenerToButton();
    }

    private void initWidget() {
        mLoginButton = (Button) findViewById(R.id.login_button);
        mTokenEditText = (EditText) findViewById(R.id.token_edit_text);
        mUsernameEditText = (EditText) findViewById(R.id.username_edit_text);
    }

    private void launchSlackApplication() {
        String slackPackageName = "com.Slack";
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(slackPackageName);
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            Log.i(TAG, "There is no slack application.");
        }
    }

    private void setListenerToButton() {
        mLoginButton.setOnClickListener(v -> sendLoginRequest());
    }

    private void sendLoginRequest() {
        LoginRequest request = makeRequest(LoginRequest.BASE_URL, LoginRequest.class);
        Call<String> queue = request.login(getTextFrom(mUsernameEditText), getTextFrom(mTokenEditText));

        Action onSuccessfulRequest = this::invokeSignUpFunction;
        Action onFailureRequest = () -> {
            notifyRequestFailureToUser(R.string.login_error);
            Log.e(TAG, "Login failed...");
        };

        CallbackWrapper<String> callbackWrapper = new CallbackWrapper<>(
                (call, response) -> respondRequestResult(response, onSuccessfulRequest, onFailureRequest),
                (call, throwable) -> notifyRequestFailureToUser(R.string.network_error));

        queue.enqueue(callbackWrapper.getCallback());
    }

    private <T> T makeRequest(String baseUrl, Class<T> requestInfo) {
        Log.i(TAG, "Making request. BaseUrl = " + baseUrl + ", Class = " + requestInfo.getSimpleName());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(requestInfo);
    }

    private String getTextFrom(EditText editText) {
        return editText.getText().toString();
    }

    private void invokeSignUpFunction() {
        SignUpRequest request = makeRequest(SignUpRequest.BASE_URL, SignUpRequest.class);
        Call<String> queue = request.execute(getTextFrom(mUsernameEditText));

        Action onSuccessfulRequest = () -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        };

        Action onFailureRequest = () -> {
            notifyRequestFailureToUser(R.string.sign_up_error);
            Log.e(TAG, "Sign up failed...");
        };

        CallbackWrapper<String> callbackWrapper = new CallbackWrapper<>(
                (call, response) -> respondRequestResult(response, onSuccessfulRequest, onFailureRequest),
                (call, throwable) -> notifyRequestFailureToUser(R.string.network_error));

        queue.enqueue(callbackWrapper.getCallback());
    }

    private void respondRequestResult(Response<String> response, Action onSuccessful, Action onFailure) {
        if (response.isSuccessful()) {
            onSuccessful.act();
        } else {
            onFailure.act();
        }
    }

    private void notifyRequestFailureToUser(@StringRes int errorMessageId) {
        Log.e(TAG, "Request failure.");
        Toast.makeText(this, errorMessageId, Toast.LENGTH_SHORT).show();
    }
}