package com.dengzi.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dengzi.retrofit.api.ServiceApiImpl;
import com.dengzi.retrofit.bean.UserBean;
import com.dengzi.retrofit.custom.DzCall;
import com.dengzi.retrofit.custom.DzCallback;
import com.dengzi.retrofit.custom.DzResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        ServiceApiImpl
                .creatApi()
                .getInfo("Guolei1130", "desc")
                .enqueue(new Callback<UserBean>() {
                    @Override
                    public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                        UserBean userBean = response.body();
                        Log.e("dengzi", "userBean = " + userBean.toString());
                    }

                    @Override
                    public void onFailure(Call<UserBean> call, Throwable t) {
                        Log.e("dengzi", "onFailure");
                    }
                });
    }

    public void click2(View view) {
        ServiceApiImpl
                .creatApi2()
                .getInfo2("Guolei1130", "desc")
                .enqueue(new DzCallback<UserBean>() {
                             @Override
                             public void onFailure(DzCall<UserBean> call, Throwable t) {
                                 Log.e("dengzi", "onFailure");
                             }

                             @Override
                             public void onResponse(DzCall<UserBean> call, DzResponse<UserBean> response) {
                                 UserBean userBean = response.body();
                                 Log.e("dengzi", "userBean = " + userBean.toString());
                             }
                         }

                );
    }
}
