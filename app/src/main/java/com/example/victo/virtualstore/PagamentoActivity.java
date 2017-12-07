package com.example.victo.virtualstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PagamentoActivity extends AppCompatActivity {

    private Double total;
    private TextView tvTotal;
    private Button btComprar;
    private Product item = new Product();
    private DBHelper dh;
    private EditText etNome,etNumCartao4,etNumCartao1,etNumCartao2,etNumCartao3,etCvv,etMes,etAno;


    public static final String EXTRA_REPLY = "com.example.victo.virtualstore.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        this.dh = new DBHelper(this);

        btComprar = (Button) findViewById(R.id.btComprar);
        etNome = (EditText) findViewById(R.id.etNome);
        etNumCartao4 = (EditText) findViewById(R.id.etNumCartao4);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        Intent it = getIntent();
        total = (Double) it.getSerializableExtra("total");
        tvTotal.setText(total.toString());


        btComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(etNome.getText().length()>0 && tvTotal.getText().length()>0 && etNumCartao4.getText().length()>0){
                    dh.insert(etNome.getText().toString(), tvTotal.getText().toString(), etNumCartao4.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(PagamentoActivity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Comprado com Sucesso!");
                    adb.show();

                    etNome.setText(" ");
                    etNumCartao1.setText(" ");
                    etNumCartao2.setText(" ");
                    etNumCartao3.setText(" ");
                    etNumCartao4.setText(" ");
                    etCvv.setText(" ");
                    etMes.setText(" ");
                    etAno.setText(" ");
                }
                else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(PagamentoActivity.this);
                    adb.setTitle("Erro");
                    adb.setMessage("Todo os campos devem ser preenchidos!");
                    adb.show();
                }
            }
        });
    }

    public void btnlistar(View v){
        Intent i = new Intent(PagamentoActivity.this, TransacoesActivity.class);
        startActivity(i);
    }
}
