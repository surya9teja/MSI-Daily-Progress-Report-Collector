package com.example.msi_dpr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.javiersantos.appupdater.AppUpdater;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    ProgressDialog dialog;
    Button login,signup,forget;
    EditText email,password;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth =FirebaseAuth.getInstance();
        login=(Button) findViewById(R.id.login);
        forget=(Button)findViewById(R.id.forget);
        signup=(Button)findViewById(R.id.signup);
        email=(EditText)findViewById(R.id.in_email);
        password=(EditText)findViewById(R.id.in_password);
        Intent intent=new Intent(login.this,data_retrieval.class);
        startActivity(intent);
        dialog=new ProgressDialog(login.this);
        dialog.setMessage("Logging in");
        new AppUpdater(this)
                .setUpdateFrom(UpdateFrom.GITHUB)
                .setGitHubUserAndRepo("surya9teja", "MSI_DPR")
                .start();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user !=null)
                {
                    Toast.makeText(login.this,"User Logged in",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(login.this,MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(login.this,"Login to continue",Toast.LENGTH_SHORT).show();
                }
            }
        };
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email=email.getText().toString();
                String Password=password.getText().toString();
                if(Email.isEmpty()){
                    email.setError("Please provide Email");
                }
                else if(Password.isEmpty())
                {
                    password.setError("Please enter Password");
                }
                else if(!(Email.isEmpty()) && !(Password.isEmpty()))
                {
                    dialog.show();
                    firebaseAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!(task.isSuccessful()))
                            {
                                dialog.dismiss();
                                Toast.makeText(login.this,"Login Failed",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(login.this,"Login Success",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                Intent intent=new Intent(login.this,data_post.class);
                                startActivity(intent);
                                login.this.finish();
                            }
                        }
                    });

                }
                else {
                    Toast.makeText(login.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,signup.class);
                startActivity(intent);
            }
        });
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,Forget_password.class);
                startActivity(intent);
                login.this.finish();
            }
        });

    }
}
