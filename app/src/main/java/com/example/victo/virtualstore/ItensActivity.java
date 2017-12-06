package com.example.victo.virtualstore;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class ItensActivity extends AppCompatActivity {


    public static final int TEXT_REQUEST = 1;
    ArrayList<Produto> produtosSelecionados;


    ArrayList<Produto> arrayList;
    ArrayList<Produto> carrinho;
    ListView lv;
    TextView tvVoltar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens);

        arrayList = new ArrayList<>();
        carrinho = new ArrayList<>();

        lv = (ListView) findViewById(R.id.listItens);

        //Código para Url que vem o Json usado
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJson().execute("https://raw.githubusercontent.com/stone-pagamentos/desafio-mobile/master/products.json");
            }
        });



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto produto = (Produto) parent.getItemAtPosition(position);


                //ao clicar no produto, adiciona ou remove o produto do carrinho
                if(produtoEstaNoCarrinho(produto)){
                    carrinho.remove(produto);
                    Mensagem(produto.getTitle().toString() +" removido do carrinho");
                //    parent.getChildAt(position).setBackgroundColor(Color.WHITE);
                }else{
                    carrinho.add(produto);
                    Mensagem(produto.getTitle().toString() +" adicionado ao carrinho");
                 //   parent.getChildAt(position).setBackgroundColor(Color.LTGRAY);
                }
                


     //           Intent it = new Intent();
   //             it.putExtra("produto",produto);
  //              it.setClass(getApplicationContext(),CarrinhoActivity.class);
//                startActivity(it);

            }
        });



    }
    //método para verificar se o produto esta no carrinho
    public Boolean produtoEstaNoCarrinho(Produto produto){
        for(int i = 0;  i <= carrinho.size()-1 ; i++){

            if (carrinho.get(i).getTitle().toString().equals(produto.getTitle().toString())){
                return true;
            }
        }
        return false;
    }
    // metodo para exibir uma mensagem no Toast
    public void Mensagem(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }

    class ReadJson extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            return readUrl(params[0]);
        }

        @Override
        protected void onPostExecute(String content) {

            //Passando o Json para Objeto
            try {
                JSONArray arrayjson = new JSONArray(content);

                for (int i = 0; i < arrayjson.length(); i++) {
                    JSONObject productObject = arrayjson.getJSONObject(i);
                    Produto produto = new Produto();

                    produto.setTitle(productObject.getString("title"));
                    produto.setPrice(productObject.getString("price"));
                    produto.setZipcode(productObject.getString("zipcode"));
                    produto.setSeller(productObject.getString("seller"));
                    produto.setImage(productObject.getString("thumbnailHd"));
                    produto.setDate(productObject.getString("date"));

                    arrayList.add(produto);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ListAdapter adapter = new ListAdapter(
                    getApplicationContext(), R.layout.custom_list_layout, arrayList
            );
            lv.setAdapter(adapter);
        }
    }

    private static String readUrl(String theUrl){
        StringBuilder content = new StringBuilder();
        try{
            URL url = new URL(theUrl);

            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return content.toString();
    }

    public void irCarrinho(View v){
        Intent i = new Intent(ItensActivity.this, CarrinhoActivity.class);
        i.putExtra("carrinho",carrinho);
        startActivity(i);
    }

}
