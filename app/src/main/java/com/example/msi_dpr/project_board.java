package com.example.msi_dpr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class project_board extends AppCompatActivity {

    ImageButton AP,TS,BS,GT,PG,PT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_board);
        AP=(ImageButton)findViewById(R.id.AP);
        TS=(ImageButton)findViewById(R.id.TS);
        BS=(ImageButton)findViewById(R.id.BS);
        GT=(ImageButton)findViewById(R.id.GT);
        PG=(ImageButton)findViewById(R.id.PG);
        PT=(ImageButton)findViewById(R.id.PT);
        AP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(project_board.this,project_1.class);
                startActivity(intent);
            }
        });
    }
}
