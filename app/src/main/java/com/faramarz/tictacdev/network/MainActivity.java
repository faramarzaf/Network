package com.faramarz.tictacdev.network;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.faramarz.tictacdev.network.Model.IPModel;
import com.faramarz.tictacdev.network.network.ApiClient;
import com.faramarz.tictacdev.network.network.ApiInterface;
import com.faramarz.tictacdev.network.retrofit_post.PostActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView flag;
    TextView txtCountry, txtIp, txtLocalTime;
    Button btnCall, btnPostPage;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
        changeStatusBarColor();
        dialog = new ProgressDialog(this);
        clickEvents();
    }

    private void clickEvents() {
        btnCall.setOnClickListener(this);
        btnPostPage.setOnClickListener(this);
    }

    private void bind() {
        flag = findViewById(R.id.flag);
        txtCountry = findViewById(R.id.txtCountry);
        txtIp = findViewById(R.id.txtIp);
        txtLocalTime = findViewById(R.id.txtLocalTime);
        btnCall = findViewById(R.id.btnCall);
        btnPostPage = findViewById(R.id.btnPostPage);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnCall:
                getIp();
                break;
            case R.id.btnPostPage:
                startActivity(new Intent(MainActivity.this, PostActivity.class));
                break;
        }

    }

    private void getIp() {
        ApiInterface apiService = ApiClient.getFlag().create(ApiInterface.class);
        Call<IPModel> call = apiService.getFlag("69a19e7f47d06441547a1b0fed5d2a7880a4b36c7108444d7ac30250");
        showDialog();
        call.enqueue(new Callback<IPModel>() {

            @Override
            public void onResponse(Call<IPModel> call, Response<IPModel> response) {
                txtCountry.setText(response.body().getCountryName());
                txtIp.setText(response.body().getIp());
                txtLocalTime.setText(response.body().getTimeZone().getCurrentTime());
                String url = response.body().getFlag();
                Glide.with(getApplicationContext()).load(url).into(flag);
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<IPModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Unable to fetch json: " + t.getMessage(), Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }

        });
    }

    private void showDialog() {
        dialog.setTitle("Getting Data");
        dialog.setMessage("Please wait to load data...");
        dialog.show();
    }


    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


}
