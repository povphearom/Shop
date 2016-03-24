package com.phearom.shop.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.phearom.shop.R;
import com.phearom.shop.api.utils.K;
import com.phearom.shop.api.utils.SessionManager;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {
    private CallbackManager callbackManager;
    private LoginButton mLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        mLoginButton = (LoginButton) findViewById(R.id.login_facebook);
        mLoginButton.registerCallback(callbackManager, this);

        if (SessionManager.init(this).getUserData(K.IS_LOGIN, false)) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        SessionManager.init(this).saveUserData(K.IS_LOGIN, true);
        SessionManager.init(this).saveUserData(K.TOKEN, loginResult.getAccessToken().getToken());
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(FacebookException error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

