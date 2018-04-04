package project.alc.com.med_manager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private ProgressDialog progressDialog;
    private LinearLayout profile_section;
    private Button SignOut, done;
    private SignInButton SignIn;
    private TextView name, email;
    private ImageView passport;
    private GoogleApiClient googleApiClient;
    private static final int REQ_CODE = 9001;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profile_section = (LinearLayout) findViewById(R.id.profile_section);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        SignIn = (SignInButton) findViewById(R.id.sign_in_button);
        SignOut = (Button) findViewById(R.id.sign_out_button);
        done = (Button) findViewById(R.id.done);
        passport = (ImageView) findViewById(R.id.profile_pic);
        done.setOnClickListener(this);
        SignIn.setOnClickListener(this);
        SignOut.setOnClickListener(this);
        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
        profile_section.setVisibility(View.GONE);
        progressDialog = new ProgressDialog(this);
    }


    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    public void signOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateUI(false);
            }
        });
    }

    public void handleResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String Name = account.getDisplayName();
            String Email = account.getEmail();
            try {
                String img_url = account.getPhotoUrl().toString();
                Glide.with(this).load(img_url).into(passport);
            } catch (Exception e) {
                e.printStackTrace();
            }
            name.setText(Name);
            email.setText(Email);
            updateUI(true);
        } else {
            updateUI(false);
        }
    }

    public void updateUI(boolean isLogin) {
        if (isLogin) {
//            progressDialog.dismiss();
            profile_section.setVisibility(View.VISIBLE);
            SignIn.setVisibility(View.GONE);
        } else {
            profile_section.setVisibility(View.GONE);
            SignIn.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
//            progressDialog.setMessage("Logging in");
//            progressDialog.setCancelable(false);
//            progressDialog.setTitle("Processing");
//            progressDialog.show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.done:
                Intent i = new Intent(this, HomeActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}

