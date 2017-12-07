package com.example.victo.virtualstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class PagamentoActivity extends AppCompatActivity {

    private Double total;
    private TextView tvTotal;
    private Button btComprar;
    private Product item = new Product();
    private DBHelper dh;
    private EditText etNome, creditCard, cvv, expMonth, expYear;


    public static final String EXTRA_REPLY = "com.example.victo.virtualstore.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        this.dh = new DBHelper(this);

        btComprar = (Button) findViewById(R.id.btComprar);
        etNome = (EditText) findViewById(R.id.etNome);
        creditCard = (EditText) findViewById(R.id.creditCardEditText);
        cvv = findViewById(R.id.etCvv);
        expMonth = findViewById(R.id.etMes);
        expYear = findViewById(R.id.etAno);
        tvTotal = (TextView) findViewById(R.id.tvTotal);

        Intent it = getIntent();
        total = (Double) it.getSerializableExtra("total");
        tvTotal.setText(CurrencyHelper.parseDoubleToCurrency(total));


        btComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String errorMessage = validateFields();
                if (errorMessage == null) {
                    dh.insert(etNome.getText().toString(), tvTotal.getText().toString(), creditCard.getText().toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(PagamentoActivity.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Comprado com Sucesso!");
                    adb.show();

                    etNome.setText(null);
                    creditCard.setText(null);
                    cvv.setText(null);
                    expMonth.setText(null);
                    expYear.setText(null);
                }
                else {
                    AlertDialog.Builder adb = new AlertDialog.Builder(PagamentoActivity.this);
                    adb.setTitle("Erro");
                    adb.setMessage(errorMessage);
                    adb.show();
                }
            }
        });
    }

    @Nullable
    private String validateFields() {
        StringBuilder message = new StringBuilder();

        if (!(this.etNome.getText().length() > 0)) { message.append("Favor preencher o nome.\n"); }
        if (!(this.tvTotal.getText().length() > 0)) { message.append("Valor não pode ser 0!\n"); }
        if (this.creditCard.getText().length() != 16) { message.append("Cartão inválido.\n"); }
        if (this.cvv.getText().length() != 3) { message.append("CVV incorreto.\n"); }

        try {
            if (Integer.parseInt(this.expMonth.getText().toString()) > 31) { message.append("Mês inválido.\n"); }
            if (Integer.parseInt(this.expYear.getText().toString()) < 18) { message.append("Ano inválido.\n"); }
        } catch (Exception ex) {
            message.append("É necessário preencher a data de validade do cartão.\n");
        }

        return message.toString().isEmpty() ? null : message.toString();
    }

    public void btnlistar(View v){
        Intent i = new Intent(PagamentoActivity.this, TransacoesActivity.class);
        startActivity(i);
    }
}
