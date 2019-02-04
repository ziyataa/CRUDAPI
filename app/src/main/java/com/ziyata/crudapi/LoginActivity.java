package com.ziyata.crudapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ziyata.crudapi.api.ApiClient;
import com.ziyata.crudapi.api.ApiInterface;
import com.ziyata.crudapi.model.LoginBody;
import com.ziyata.crudapi.model.LoginResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO 6
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    // TODO a membuat variable yang diperlukan
    private LoginBody loginBody;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
        // TODO b Mengambil data dari Inputan user
        getData();

        // TODO c melakukan login
        login();
    }

    private void login() {
        showProgress();

        // Membuat object api Interface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Call untuk operasi yang kita inginkan yaitu login
        Call<LoginResponse> call = apiInterface.postLogin(loginBody);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                LoginResponse loginResponse = response.body();
                Toast.makeText(LoginActivity.this, loginResponse.getToken(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showProgress() {
        // Membuat object progress dialog
        progressDialog = new ProgressDialog(LoginActivity.this);

        // Kita setting progressDialog
        progressDialog.setMessage("Loading...");

        // Setting agar dialoh tidak bisa di cancel
        progressDialog.setCancelable(false);

        // melakukan show progress dialog
        progressDialog.show();
    }

    private void getData() {
        // Membuat object loginBody
        loginBody = new LoginBody();

        // Mengisi loginBody dengan data inputan user dari EditText
        loginBody.setEmail(edtEmail.getText().toString());
        loginBody.setPassword(edtPassword.getText().toString());

    }
}
