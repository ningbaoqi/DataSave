package com.dashen.ningbaoqi.datasave;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity1 extends AppCompatActivity {

    private EditText et_login;
    private EditText te_password;
    private static final String PATH = "/data/data/ningbaoqi.com.androidfilesystem/password.txt";

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
        File file = new File(PATH);
        FileInputStream inputStream = new FileInputStream(file);
        /**
         * 将字节流转换成字符流
         * */
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String text = reader.readLine();
        String[] strs = text.split("##");
        et_login.setText(strs[0]);
        te_password.setText(strs[1]);
    }

    public void login(View view) throws IOException {
        String name = et_login.getText().toString();
        String password = te_password.getText().toString();
        CheckBox cb = (CheckBox) findViewById(R.id.remember);
        if (cb.isChecked()) {
            File file = new File(PATH);
            FileOutputStream output = new FileOutputStream(file);
            output.write((name + "##" + password).getBytes());
            output.close();
        }
    }
}
