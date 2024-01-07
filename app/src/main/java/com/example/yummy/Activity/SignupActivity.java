package com.example.yummy.Activity;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.yummy.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignupActivity extends BaseActivity {
    ActivitySignupBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setVariable();
    }

    private void setVariable() {
        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=binding.userEdt.getText().toString();
                String password=binding.passEdt.getText().toString();

                if(password.length()<6){
                    Toast.makeText(SignupActivity.this,"Your password must be 6 characters long",Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.i(TAG, "onComplete: ");
                            Toast.makeText(SignupActivity.this,"Signup Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignupActivity.this,MainActivity.class));
                        }else{
                            Log.i(TAG, "failure: "+task.getException());
                            Toast.makeText(SignupActivity.this,"Authentication failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}