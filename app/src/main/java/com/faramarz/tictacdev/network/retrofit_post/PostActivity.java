package com.faramarz.tictacdev.network.retrofit_post;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.faramarz.tictacdev.network.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    //https://avasam.ir/blog/113

    TextView mResponseTv;
    APIService mAPIService;
    public static String TAG = "TAG";
    EditText titleEt, bodyEt;
    Button submitBtn;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        dialog = new ProgressDialog(this);
        bind();
        mAPIService = ApiUtils.getAPIService();
        submitBtn.setOnClickListener(this);
    }

    private void bind() {
        titleEt = findViewById(R.id.et_title);
        bodyEt = findViewById(R.id.et_body);
        submitBtn = findViewById(R.id.btn_submit);
        mResponseTv = findViewById(R.id.tv_response);

    }

    public void sendPost(String title, String body) {
        showDialog();
        mAPIService.savePost(title, body, 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    showResponse(response.body().toString());
                    dialog.dismiss();
                    Log.i(TAG, "post submitted to API." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(PostActivity.this, "unable to submit post to API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showResponse(String response) {
        if (mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(response);
    }


    private void showDialog() {
        dialog.setTitle("Getting Data");
        dialog.setMessage("Please wait to load data...");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        String title = titleEt.getText().toString().trim();
        String body = bodyEt.getText().toString().trim();
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)) {
            sendPost(title, body);
        }
    }
}