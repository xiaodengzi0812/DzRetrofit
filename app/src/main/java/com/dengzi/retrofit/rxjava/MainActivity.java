package com.dengzi.retrofit.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.dengzi.retrofit.R;
import com.dengzi.retrofit.RetrofitManager;
import com.dengzi.retrofit.api.ServiceApiImpl;
import com.dengzi.retrofit.bean.UserBean;
import com.dengzi.retrofit.custom.DzCall;
import com.dengzi.retrofit.custom.DzCallback;
import com.dengzi.retrofit.custom.DzResponse;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        final GithubApi githubApi = RetrofitManager
                .getInstance()
                .getClient()
                .create(GithubApi.class);

        githubApi
                .getInfo("Guolei1130", "desc")
                .flatMap(new Function<UserBean, ObservableSource<UserBean>>() {
                    @Override
                    public ObservableSource<UserBean> apply(@NonNull UserBean userBean) throws Exception {
                        Log.e("dengzi", "userBean1 = " + userBean.toString());
                        return githubApi.getInfo2("Guolei1130", "desc");
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<UserBean>() {
                    @Override
                    public void onNext(@NonNull UserBean userBean) {
                        Log.e("dengzi", "userBean2 = " + userBean.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("dengzi", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("dengzi", "onComplete");
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
