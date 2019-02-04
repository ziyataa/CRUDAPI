package com.ziyata.crudapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ziyata.crudapi.api.ApiClient;
import com.ziyata.crudapi.api.ApiInterface;
import com.ziyata.crudapi.model.SingelUserResponse;
import com.ziyata.crudapi.model.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//TODO 11
public class DetailActivity extends AppCompatActivity {

    public static final String KEY_ID = "KEY_ID";
    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.txtFirst)
    TextView txtFirst;
    @BindView(R.id.txtLast)
    TextView txtLast;

    // TODO a Membuat variable yang dibutuhkan
    private UserData userData;
    private ProgressDialog progressDialog;
    ApiInterface apiInterface;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        // TODO b Menangkap data dari Intent
        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        // Mengecek terlebih dahulu bundle apakah ada isinya atau null
        if (bundle != null){
            // Memasukkan id yang ada di bundle ke dalam variable id
            id = bundle.getInt(KEY_ID);
        }

        // Menampilkan show progress
        showProgress();

        // TODO c Mengambil data
        getData();
    }

    private void getData() {
        // Membuat Object api interface
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        // Membuat method call untuk menggunakan method getSingleUser
        Call<SingelUserResponse> call = apiInterface.getSingelUser(id);

        // Eksekusi Call
        call.enqueue(new Callback<SingelUserResponse>() {
            @Override
            public void onResponse(Call<SingelUserResponse> call, Response<SingelUserResponse> response) {
                // Menghilangkan progress dialog
                progressDialog.dismiss();

                // Mengambil response api body dan memasukkan ke dalam wadah yang sesuai
                SingelUserResponse singelUserResponse = response.body();

                // Memasukkan data berupa object ke dalam kelas object
                userData = singelUserResponse.getData();

                // Menampilkan data ke layar
                txtFirst.setText(userData.getFirst_name());
                txtLast.setText(userData.getLast_name());

                // Mensetting request option
                RequestOptions options = new RequestOptions().placeholder(R.drawable.ic_broken_image).error(R.drawable.ic_broken_image);

                // Menampilkan gambar dengan glide serta memasukkan option ke dalam apply glide
                Glide.with(DetailActivity.this).load(userData.getAvatar()).apply(options).into(imgAvatar);
            }

            @Override
            public void onFailure(Call<SingelUserResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(DetailActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
