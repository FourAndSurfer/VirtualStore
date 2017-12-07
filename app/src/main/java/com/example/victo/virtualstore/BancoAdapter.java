package com.example.victo.virtualstore;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by victo on 06/12/2017.
 */

public class BancoAdapter extends ArrayAdapter<ItensBanco> {


    List<ItensBanco> produtos;
    Context context;
    int resource;


    public BancoAdapter(Context context, int resource, List<ItensBanco> produtos) {
        super(context, resource, produtos);
        this.produtos = produtos;
        this.context = context;
        this. resource = resource;

    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent){

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.custom_transacoes_layout, null, true);
        }


        ItensBanco produto = getItem(position);



        TextView txtTitle = (TextView) convertView.findViewById(R.id.txtNome);
        if (produto.getNome() != null) { txtTitle.setText(produto.getNome()); }

        TextView txtPrice = (TextView) convertView.findViewById(R.id.txtPrice);
        if (produto.getPrice() != null) { txtPrice.setText(produto.getPrice()); }

        TextView txtCartao = (TextView) convertView.findViewById(R.id.txtCartao);
        if (produto.getCartao() != null) { txtCartao.setText("**** **** **** " + produto.getCartao()); }

        return convertView;
    }


}

