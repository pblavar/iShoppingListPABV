package com.example.ishoppinglistpabv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.ishoppinglistpabv.R;
import com.example.ishoppinglistpabv.models.Product;

public class DetailProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);
        Switch swPending = findViewById(R.id.swPending);
        Switch swLactose = findViewById(R.id.swLactose);
        Switch swGluten = findViewById(R.id.swGluten);
        Button btnEdit = findViewById(R.id.btnEdit);
        Button btnBack = findViewById(R.id.btnBack);

        // Obtenemos el intent
        Intent intent = getIntent();

        // Recuperamos los datos del intent
        Product product = (Product) intent.getSerializableExtra("product");

        // Asignamos los valores a los campos
        tvName.setText(product.getName());
        tvDescription.setText(product.getDescription());
        swPending.setChecked(product.isState());
        swLactose.setChecked(product.isLactose());
        swGluten.setChecked(product.isGluten());

        // Botón que te envia al main
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Botón que te envia a la activity EditProductActivity
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProductActivity.this, EditProductActivity.class);
                intent.putExtra("productId", product.getId());
                startActivity(intent);
            }
        });
    }
}