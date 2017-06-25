package org.zeropage.apps.zeropage.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.log.ZpLog;
import org.zeropage.apps.zeropage.main.MainActivity;
import org.zeropage.apps.zeropage.network.common.CallbackWrapper;
import org.zeropage.apps.zeropage.network.common.RequestSender;
import org.zeropage.apps.zeropage.network.function.SignUpRequest;
import org.zeropage.apps.zeropage.network.login.LoginRequest;
import org.zeropage.apps.zeropage.utility.Action;
import org.zeropage.apps.zeropage.utility.UserInfoPreferences;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private Button mLoginButton;
    private EditText mTokenEditText;
    private EditText mUsernameEditText;
    private ProgressBar mLoginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Fabric.with(this, new Crashlytics());

        initWidget();
        launchSlackApplication();
        setListenerToButton();
    }

    private void initWidget() {
        mLoginButton = (Button) findViewById(R.id.login_button);
        mTokenEditText = (EditText) findViewById(R.id.token_edit_text);
        mUsernameEditText = (EditText) findViewById(R.id.username_edit_text);
        mLoginProgressBar = (ProgressBar) findViewById(R.id.login_progress_bar);
    }

    private void launchSlackApplication() {
        String slackPackageName = "com.Slack";
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(slackPackageName);
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            ZpLog.i(TAG, "There is no slack application.");
        }
    }

    private void setListenerToButton() {
        mLoginButton.setOnClickListener(v -> new LoginTask().execute());
    }

    private String getTextFrom(EditText editText) {
        return editText.getText().toString();
    }

    private void notifyRequestFailureToUser(@StringRes int errorMessageId) {
        ZpLog.e(TAG, "Request failure.");
        mLoginProgressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, errorMessageId, Toast.LENGTH_SHORT).show();
    }

    private void switchToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private class LoginTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected void onPreExecute() {
            mLoginProgressBar.setVisibility(View.VISIBLE);
            mLoginProgressBar.setProgress(0);
        }

        @Override
        protected Void doInBackground(Void... params) {
            sendLoginRequest();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mLoginProgressBar.setProgress(values[0]);
        }

        private void sendLoginRequest() {
            publishProgress(mLoginProgressBar.getMax() / 3);

            RequestSender<LoginRequest> loginSender = new RequestSender<>(LoginRequest.class);
            loginSender.sendRequest(makeCallbackForLogin(), getTextFrom(mUsernameEditText), getTextFrom(mTokenEditText));
        }

        private CallbackWrapper<String> makeCallbackForLogin() {
            Action onSuccessfulRequest = this::sendSignUpRequest;
            Action onFailureRequest = () -> notifyRequestFailureToUser(R.string.login_error);

            return new CallbackWrapper<>(
                    (call, response) -> respondRequestResult(response, onSuccessfulRequest, onFailureRequest),
                    (call, throwable) -> notifyRequestFailureToUser(R.string.network_error)
            );
        }

        private void respondRequestResult(Response<String> response, Action onSuccessful, Action onFailure) {
            if (response.isSuccessful()) {
                onSuccessful.act();
            } else {
                onFailure.act();
            }
        }

        private void sendSignUpRequest() {
            publishProgress((mLoginProgressBar.getMax() / 3) * 2);

            RequestSender<SignUpRequest> signUpSender = new RequestSender<>(SignUpRequest.class);
            signUpSender.sendRequest(makeCallbackForSignUp(), getTextFrom(mUsernameEditText), FirebaseInstanceId.getInstance().getToken());
        }

        private CallbackWrapper<String> makeCallbackForSignUp() {
            Action onSuccessfulRequest = () -> {
                switchToMainActivity();
                UserInfoPreferences.putUserName(LoginActivity.this, getTextFrom(mUsernameEditText));
                mLoginProgressBar.setVisibility(View.INVISIBLE);
            };

            Action onFailureRequest = () -> notifyRequestFailureToUser(R.string.sign_up_error);

            return new CallbackWrapper<>(
                    (call, response) -> respondRequestResult(response, onSuccessfulRequest, onFailureRequest),
                    (call, throwable) -> notifyRequestFailureToUser(R.string.network_error)
            );
        }
    }
}