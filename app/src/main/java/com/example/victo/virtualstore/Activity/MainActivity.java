package com.example.victo.virtualstore.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.victo.virtualstore.HELPER.DBHelper;
import com.example.victo.virtualstore.R;

public class MainActivity extends AppCompatActivity {

    Button btItens;
    DBHelper dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.dh = new DBHelper(this);
        //dh.deleteAll();

        btItens  = (Button) findViewById(R.id.btItens);
    }

    public void btnlistar(View v){
        Intent i = new Intent(MainActivity.this, TransacoesActivity.class);
        startActivity(i);
    }

    //Ir para tela iten
    public void telaItem (View V){
        Intent intent = new Intent(MainActivity.this, ItensActivity.class);
        startActivity(intent);
    }
}
