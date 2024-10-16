package com.example.ishoppinglistpabv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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

        ListView lvProduct = findViewById(R.id.lvElements);
        Button btnAddPending = findViewById(R.id.btnAddPending);
        Button btnAddSystem = findViewById(R.id.btnAddSystem);
        Spinner sp = findViewById(R.id.sp);

        String[] category = new String[]{"Todos", "Lactosa", "Gluten"};
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        sp.setAdapter(a);

        // Inicializamos la lista.
        DataBase.inicializeList();
        ProductAdapter productAdapter = new ProductAdapter(MainActivity.this, 0, DataBase.getProductListPending());

        // Asignamos el adaptador a la lista de los elementos
        lvProduct.setAdapter(productAdapter);

        // Botón que te envía a la activity ProductPendingActivity
        btnAddPending.setOnClickListener(view -> {
            Intent intentPending = new Intent(MainActivity.this, ProductPendingActivity.class);
            startActivity(intentPending);
        });

        // Botón que te envía a la activity AddNewProductActivity
        btnAddSystem.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddNewProductActivity.class);
            startActivity(intent);
        });

        // Listener para saber que categoria mostrar en la lista.
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = category[position];
                DataBase.filterListView(selectedItem, productAdapter);
            }

            // Si no se selecciona nada, mantengo siempre en "Todos".
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                DataBase.filterListView("Todos", productAdapter);
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