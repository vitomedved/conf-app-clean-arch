package com.android.template;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import template.android.com.data.DataJavaInvoker;
import template.android.com.device.DeviceJavaInvoker;
import template.android.com.domain.DomainJavaInvoker;

public final class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Hello world toast", Toast.LENGTH_SHORT)
             .show();

        // To ensure all modules are well connected.
        new DataJavaInvoker().invoke();
        new DeviceJavaInvoker().invoke();
        new DomainJavaInvoker().invoke();
    }
}
