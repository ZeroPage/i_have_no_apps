package org.zeropage.apps.zeropage.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.crashlytics.android.Crashlytics;

import org.zeropage.apps.zeropage.R;
import org.zeropage.apps.zeropage.network.NetworkQueue;
import org.zeropage.apps.zeropage.network.NetworkUrl;
import org.zeropage.apps.zeropage.network.RequestInfo;
import org.zeropage.apps.zeropage.network.RequestParameter;
import org.zeropage.apps.zeropage.network.NetworkRequest;
import org.zeropage.apps.zeropage.network.function.utils.FunctionInfoBuilder;
import org.zeropage.apps.zeropage.network.function.FunctionType;
import org.zeropage.apps.zeropage.network.utils.RequestInfoBuilder;

import io.fabric.sdk.android.Fabric;

public class LoginActivity extends AppCompatActivity
    implements Response.ErrorListener {

    private static final String TAG = "LoginActivity";

    private Button mLoginButton;
    private EditText mTokenEditText;
    private EditText mUsernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Fabric.with(this, new Crashlytics());
        // TODO : Start Slack Application.

        initWidget();
        setListenerToButton();
    }

    private void initWidget() {
        mLoginButton = (Button) findViewById(R.id.login_button);
        mTokenEditText = (EditText) findViewById(R.id.token_edit_text);
        mUsernameEditText = (EditText) findViewById(R.id.username_edit_text);
    }

    private void setListenerToButton() {
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendLoginRequest();
            }
        });
    }

    private void sendLoginRequest() {
        sendRequest(buildLoginRequestInfo(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                invokeMemberSetFunction();
            }
        });
    }

    private void sendRequest(RequestInfo requestInfo, Response.Listener<String> requestListener) {
        Log.e(TAG, "Request Sending Started, Info :\n" + requestInfo.toString());
        NetworkRequest networkRequest = new NetworkRequest(requestInfo,
                requestListener, this);

        NetworkQueue.getInstance(this).addRequestToQueue(networkRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, "Error happened while sending request. response code = " + error.networkResponse);
        Toast.makeText(this, "Error Login!", Toast.LENGTH_SHORT).show();
    }

    private RequestInfo buildLoginRequestInfo() {
        return new RequestInfoBuilder()
                .setBaseUrl(NetworkUrl.LOGIN)
                .setAdditionalUrl(mUsernameEditText.getText().toString())
                .addData(RequestParameter.TOKEN, mTokenEditText.getText().toString())
                .createInformation();
    }

    private void invokeMemberSetFunction() {
        sendRequest(buildSetMemberFunctionInfo(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // TODO : Start MainActivity.
            }
        });
    }

    private RequestInfo buildSetMemberFunctionInfo() {
        return new FunctionInfoBuilder()
                .setFunctionType(FunctionType.SET_MEMBER)
                .addData(RequestParameter.USERNAME, mUsernameEditText.getText().toString())
                .createInformation();
    }
}