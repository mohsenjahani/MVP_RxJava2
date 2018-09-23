package com.example.app.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.example.app.core.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter> implements IMain.Callback {

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv1);

        mPresenter = new MainPresenter(this);
        mPresenter.loadData();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void showLoading() {
        textView.setText("Loading...");
    }

    @Override
    public void hideLoading() {
        Toast.makeText(this, "hideLoading!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(String data) {
        textView.setText(data);
    }
}
