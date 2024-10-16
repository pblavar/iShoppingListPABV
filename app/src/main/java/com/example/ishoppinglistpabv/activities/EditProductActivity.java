package com.example.ishoppinglistpabv.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class EditProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvId = findViewById(R.id.tvEditId);
        EditText etName = findViewById(R.id.etEditName);
        EditText etDescription = findViewById(R.id.etEditDescription);
        Switch swPending = findViewById(R.id.swPending);
        Switch swLactose = findViewById(R.id.swLactose);
        Switch swGluten = findViewById(R.id.swGluten);
        Button btnBack = findViewById(R.id.btnEditBack);
        Button btnSave = findViewById(R.id.btnEditSave);

        // Obtenemos el intent
        Intent intentDetailProduct = getIntent();
        int idProduct = (int) intentDetailProduct.getSerializableExtra("productId");
        Product currentProduct = DataBase.getProductById(idProduct);

        // Asignamos los valores a los campos
        tvId.setText(String.valueOf(currentProduct.getId()));
        etName.setText(currentProduct.getName());
        etDescription.setText(currentProduct.getDescription());
        swPending.setChecked(currentProduct.isState());
        swLactose.setChecked(currentProduct.isLactose());
        swGluten.setChecked(currentProduct.isGluten());

        // Botón que te envia al main
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Guardar cambios
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etName.getText().toString().isEmpty()) {
                    Toast toastName = new Toast(EditProductActivity.this);
                    toastName.setText("The name cannot be empty");
                    toastName.show();
                    return;
                }
                currentProduct.setName(etName.getText().toString());
                currentProduct.setDescription(etDescription.getText().toString());
                currentProduct.setState(swPending.isChecked());
                currentProduct.setLactose(swLactose.isChecked());
                currentProduct.setGluten(swGluten.isChecked());
                Toast toast = new Toast(EditProductActivity.this);
                toast.setText("Product successfully edited");
                toast.show();

                // Devolvemos al usuario al main
                Intent intent = new Intent(EditProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}