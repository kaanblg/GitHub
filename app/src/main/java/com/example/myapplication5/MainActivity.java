package com.example.myapplication5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       preferences  = getSharedPreferences("profile", MODE_PRIVATE);

        TextView tvFullName = findViewById(R.id.tvFullName);
        LinearLayout llUserInfo = findViewById(R.id.llUserInfo);
        Button btnLogin = findViewById(R.id.btnLogin);


        EditText etFullName = findViewById(R.id.etFullName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etPassword = findViewById(R.id.etPassword);

        etFullName.setText(preferences.getString("full_name", ""));
        etEmail.setText(preferences.getString("email", ""));

        if(etEmail.getText().toString().length() > 0){
            tvFullName.setText("Welcome" + etFullName.getText().toString() );
            tvFullName.setVisibility(View.VISIBLE);
            llUserInfo.setVisibility(View.GONE);
        }

        //mail adresi medipol.edu.tr ile bitiyorsa
        //şifresi 123456 ise giriş yapabilsin



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etEmail.getText().toString().endsWith("@medipol.edu.tr")){

                    if(etPassword.getText().toString().equals("123456")){
                        // her iki doğrulama geçerli
                        // Kullanıcı bilgilerini kaydet

                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("full_name", etFullName.getText().toString());
                        editor.putString("email", etEmail.getText().toString());
                        editor.apply();



                        // Kullanıcıyı sonraki ekrana gönder
                        startActivity(new Intent(MainActivity.this, SecondActivity.class));

                        //Toast.makeText(MainActivity.this, "Giriş Başarılı", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(MainActivity.this, "Şifreniz hatalı!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Eposta adresini hatalı!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}