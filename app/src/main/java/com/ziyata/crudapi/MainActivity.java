package com.ziyata.crudapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ziyata.crudapi.adapter.MainAdapter;
import com.ziyata.crudapi.api.ApiClient;
import com.ziyata.crudapi.api.ApiInterface;
import com.ziyata.crudapi.model.UserData;
import com.ziyata.crudapi.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO 9
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_user)
    RecyclerView rvUser;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    // TODO a Membuat variable yang dibutuhkan
    private List<UserData> userDataList;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // TODO b Membuat atau menginisiasikan variable yang sudah kita buat
        userDataList = new ArrayList<>();

        // Menampilkan progress dialog
        showProgress();

        // TODO c Mengambil data dari Internet API
        getData();

        // TODO d Mensetting swipe refresh listener
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    private void getData() {
        // Membuat object API Interface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Membuat object call untuk menggunakan method getUser
        Call<UserResponse> call = apiInterface.getUser(12);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                // Menghilangkan progress dialog
                progressDialog.dismiss();

                // Menghilangkan icon refresh
                swipeRefresh.setRefreshing(false);

                // Mengambil Data response
                UserResponse userResponse = response.body();

                // Mengambil data json array dimasukkan dalam list
                userDataList = userResponse.getData();

                // Mensetting layout recycle vew
                rvUser.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                // Memasukkan context dan data list ke adapter
                rvUser.setAdapter(new MainAdapter(MainActivity.this, userDataList));

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                swipeRefresh.setRefreshing(false);

            }
        });
    }

    private void showProgress() {
        // Membuat object progrees Dialog
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
