package com.example.ishoppinglistpabv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.ishoppinglistpabv.R;
import com.example.ishoppinglistpabv.adapters.ProductAdapter;
import com.example.ishoppinglistpabv.dataBase.DataBase;
import com.example.ishoppinglistpabv.models.Product;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Product> productsPending = new ArrayList<>();
        ListView lvProduct = findViewById(R.id.lvElements);
        Button btnAddPending = findViewById(R.id.btnAddPending);
        Button btnAddSystem = findViewById(R.id.btnAddSystem);

        // inicializamos la lista.
        DataBase.inicializeList();
        ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, 0, DataBase.getProductListPending());

        // Asignamos el adaptador a la lista de los elementos
        lvProduct.setAdapter(productAdapter);

        // Botón que te envia a la activity ProductPendingActivity
        btnAddPending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentPending = new Intent(MainActivity.this, ProductPendingActivity.class);
                startActivity(intentPending);
            }
        });

        // Botón que te envia a la activity AddNewProductActivity
        btnAddSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewProductActivity.class);
                startActivity(intent);
            }
        });

        // Accedemos a los objetos de la lista
        lvProduct.setOnItemClickListener((adapterView, view, i, l) -> {
            Product product = DataBase.getProductListPending().get(i);

            Intent intentDetail = new Intent(MainActivity.this, DetailProductActivity.class);
            intentDetail.putExtra("product", product);
            startActivity(intentDetail);
        });
    }
}