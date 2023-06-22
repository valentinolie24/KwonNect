package com.valen.kwonnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.valen.kwonnect.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();

                boolean bolehLogin = true;
                if (TextUtils.isEmpty(username)){
                    bolehLogin = false;
                    binding.etUsername.setError("Username tidak boleh kosong!");
                }

                if (TextUtils.isEmpty(password)){
                    bolehLogin = false;
                    binding.etPassword.setError("Password tidak boleh kosong!");
                }

                if (bolehLogin){
                    login(username, password);
                }
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void login(String username, String password) {
        APIService api = Utilities.getRetrofit().create(APIService.class);
        Call<ValueData<User>> call = api.login(username, password);
        call.enqueue(new Callback<ValueData<User>>() {
            @Override
            public void onResponse(Call<ValueData<User>> call, Response<ValueData<User>> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1){
                        User user = response.body().getData();
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        Utilities.setValue(LoginActivity.this, "xUserId", user.getId());
                        Utilities.setValue(LoginActivity.this, "xUsername", username);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<User>> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}