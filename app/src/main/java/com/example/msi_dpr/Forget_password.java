package com.example.msi_dpr;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forget_password extends AppCompatActivity {

    Button forget;
    ProgressDialog dialog;
    EditText email_1,email_2;
    FirebaseAuth auth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        forget=findViewById(R.id.f_password);
        email_1=findViewById(R.id.in_email_1);
        email_2=findViewById(R.id.in_email_2);
        dialog=new ProgressDialog(Forget_password.this);
        dialog.setMessage("Hold On");
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email_1=email_1.getText().toString();
                String Email_2=email_2.getText().toString();
                if(Email_1.equals(Email_2)){
                    dialog.show();
                    String email=email_1.getText().toString();
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Forget_password.this,"check your mail to reset the password",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(Forget_password.this,login.class);
                                        startActivity(intent);
                                        dialog.dismiss();
                                        Forget_password.this.finish();
                                    }
                                    else {
                                        dialog.dismiss();
                                        Toast.makeText(Forget_password.this,"Email not registered",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(Forget_password.this, "Entered emails doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
