package com.example.ishoppinglistpabv.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.ishoppinglistpabv.R;
import com.example.ishoppinglistpabv.dataBase.DataBase;
import com.example.ishoppinglistpabv.models.Product;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private List<Product> product;

    public ProductAdapter(@NonNull Context context, int resource, @NonNull List<Product> product) {
        super(context, resource, product);
        this.product = product;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product p = this.product.get(position);

        // En caso de que no se haya creado la vista la creamos nostros
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Obtenemos los componentes
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);

        // Modificamoslos atributos de los componentes
        tvName.setText(p.getName());
        tvDescription.setText("Descripción: " + String.valueOf(p.getDescription()));
        tvId.setText("ID: " + String.valueOf(p.getId()));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = DataBase.getProductListNotPending().get(position);

        // En caso de que no se haya creado la vista la creamos nostros
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_product, parent, false);
        }

        // Obtenemos los componentes
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvId = convertView.findViewById(R.id.tvId);
        TextView tvDescription = convertView.findViewById(R.id.tvDescription);

        // Modificamoslos atributos de los componentes
        tvName.setText(product.getName());
        tvDescription.setText("Descripción: " + String.valueOf(product.getDescription()));
        tvId.setText("ID: " + String.valueOf(product.getId()));
        return convertView;
    }
}
