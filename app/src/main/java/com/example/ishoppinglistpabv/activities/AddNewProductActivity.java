package com.example.ishoppinglistpabv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.ishoppinglistpabv.R;
import com.example.ishoppinglistpabv.dataBase.DataBase;
import com.example.ishoppinglistpabv.models.Product;

public class AddNewProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvID = findViewById(R.id.tvAddId);
        TextView tvName = findViewById(R.id.etAddName);
        TextView tvDescription = findViewById(R.id.etAddDescription);
        Switch sw = findViewById(R.id.sw);
        Button btnSave = findViewById(R.id.btnAddSave);
        Button btnBack = findViewById(R.id.btnAddBack);

        // Mostramos el id del nuevo producto
        tvID.setText(String.valueOf(DataBase.getLastIdByProductList()));

        // Botón para volver al main
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddNewProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Botón para guardar el nuevo producto
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();

                product.setId(Integer.valueOf(tvID.getText().toString()));
                product.setName(tvName.getText().toString());
                product.setDescription(tvDescription.getText().toString());
                product.setState(sw.isChecked());

                if (product.getName().isEmpty()){
                    Toast toas = new Toast(AddNewProductActivity.this);
                    toas.setText("Product name cannot be empty");
                    toas.show();
                    return;
                }

                DataBase.addProduct(product,AddNewProductActivity.this);
                tvID.setText(String.valueOf(DataBase.getLastIdByProductList()));
                tvName.setText("");
                tvDescription.setText("");
                sw.setChecked(false);

                // Devolvemos al usuario al main
                Intent intent = new Intent(AddNewProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}