package com.dashen.ningbaoqi.datasave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SDcardActivity extends AppCompatActivity {

    private EditText et_login;
    private EditText te_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_login = (EditText) findViewById(R.id.et_login);
        te_password = (EditText) findViewById(R.id.et_password);
        try {
            readAccount();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件
     */
    private void readAccount() throws Exception {
        // Environment.getExternalStorageState();
        // Environment.getExternalStorageDirectory();
        File file = new File(getExternalCacheDir(), "password.txt");
        FileInputStream inputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String text = reader.readLine();
        if (!TextUtils.isEmpty(text)) {
            String[] strings = text.split("##");
            et_login.setText(strings[0]);
            te_password.setText(strings[1]);
        }
        reader.close();
    }

    public void login(View view) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(new File(getExternalCacheDir(), "password.txt"));
        String username = et_login.getText().toString();
        String password = te_password.getText().toString();
        outputStream.write((username + "##" + password).getBytes());
        outputStream.close();
    }
}
