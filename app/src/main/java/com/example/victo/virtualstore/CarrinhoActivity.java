package com.example.victo.virtualstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarrinhoActivity extends AppCompatActivity {

    ArrayList<Produto> carrinho;
    ListView carrinholv;
    Double total;
    TextView totalTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        total = 0.0;
        carrinholv = (ListView) findViewById(R.id.CarrinhoLV);

        Intent it = getIntent();
        carrinho = (ArrayList<Produto>) it.getSerializableExtra("carrinho");

        ListAdapter adapter = new ListAdapter(
                getApplicationContext(), R.layout.custom_carrinho_layout, carrinho
        );
        carrinholv.setAdapter(adapter);
        valorTotal();
    }

    public void valorTotal(){
        totalTV = (TextView) findViewById(R.id.ValorTV);

        for(int i=0; i <= carrinho.size()-1; i++){
            total += Double.parseDouble(carrinho.get(i).getPrice());
        }
        totalTV.setText(total.toString());
    }

    public void telaPagamento (View v){
        Intent i = new Intent(CarrinhoActivity.this, PagamentoActivity.class);
        i.putExtra("total",total);
        startActivity(i);
    }
}
