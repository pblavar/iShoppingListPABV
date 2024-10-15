package com.example.ishoppinglistpabv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.ishoppinglistpabv.R;
import com.example.ishoppinglistpabv.adapters.ProductAdapter;
import com.example.ishoppinglistpabv.dataBase.DataBase;
import com.example.ishoppinglistpabv.models.Product;

public class ProductPendingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_pending);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSaveProduct = findViewById(R.id.btnSaveProductPending);
        Button btnCancelProduct = findViewById(R.id.btnCancelPending);
        Spinner sp = findViewById(R.id.sp);

        ProductAdapter productAdapter = new ProductAdapter(ProductPendingActivity.this, 0, DataBase.getProductListNotPending());
        sp.setAdapter(productAdapter);

        // Botón para cancelar (Enviamos al main)
        btnCancelProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCancel = new Intent(ProductPendingActivity.this, MainActivity.class);
                startActivity(intentCancel);
            }
        });

        // Botón para guardar los cambios
        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = (Product) sp.getSelectedItem();
                // Seteamos el estado del producto a pendiente
                product.setState(true);

                Intent intent = new Intent(ProductPendingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}