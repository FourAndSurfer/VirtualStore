package com.example.victo.virtualstore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class TransacoesActivity extends AppCompatActivity {

    ListView lvtransacoes;
    private DBHelper dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacoes);
        lvtransacoes = (ListView) findViewById(R.id.LVTransacoes);
        this.dh = new DBHelper(this);

        List<ItensBanco> produtos = dh.queryGetAll();
        if(produtos == null){
            Toast.makeText(getApplicationContext(), " Não há registros! " ,Toast.LENGTH_SHORT).show();
            return;
        }else{
            BancoAdapter adapter = new BancoAdapter(
                    getApplicationContext(), R.layout.custom_transacoes_layout, produtos
            );
            lvtransacoes.setAdapter(adapter);

        }




    }
}
