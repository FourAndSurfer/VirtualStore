package com.example.victo.virtualstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btItens  = (Button) findViewById(R.id.btItens);
    }

    //Ir para tela iten
    public void telaItem (View V){
        Intent intent = new Intent(MainActivity.this, ItensActivity.class);
        startActivity(intent);
    }
}
