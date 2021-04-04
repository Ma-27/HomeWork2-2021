package com.example.homework22021.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.homework22021.R;
import com.example.homework22021.presenter.IOnClickPresenter;
import com.example.homework22021.presenter.OnClickPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, IDataChangeListener {
    private static IDataChangeListener onDataChangeListener;
    private static String messageStr;
    @SuppressLint("HandlerLeak")
    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            messageStr = (String) msg.obj;
            if (onDataChangeListener != null) {
                onDataChangeListener.onDataChanged(messageStr);
            }
        }
    };
    private TextView tv_content;
    private Button btn_content;
    private IOnClickPresenter onClickPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        findView();
        initListener();
    }

    public void findView() {
        tv_content = findViewById(R.id.tv_content);
        btn_content = findViewById(R.id.btn_content);

        onDataChangeListener = this;
    }

    public void initListener() {
        btn_content.setOnClickListener(this);

        if (onClickPresenter == null) {
            onClickPresenter = new OnClickPresenter();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        onClickPresenter.onButtonClick();
    }

    @Override
    public void onDataChanged(String data) {
        tv_content.setText(data);
    }
}