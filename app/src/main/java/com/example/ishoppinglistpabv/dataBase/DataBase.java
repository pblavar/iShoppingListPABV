package com.example.ishoppinglistpabv.dataBase;

import android.widget.Toast;

import com.example.ishoppinglistpabv.activities.AddNewProductActivity;
import com.example.ishoppinglistpabv.models.Product;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public static List<Product> productList;

    public static void inicializeList() {
        if (productList == null) {
            productList = new ArrayList<>();
            // Añadiendo 20 productos inventados al ArrayList
            productList.add(new Product(1, "Aspiradora Robótica", "Navegación autónoma y limpieza eficiente", true, true, false));
            productList.add(new Product(2, "Horno de Aire", "Cocina saludable y rápida", false, false, false));
            productList.add(new Product(3, "Bicicleta Eléctrica", "Asistencia de pedaleo y batería recargable", true, false, true));
            productList.add(new Product(4, "Cámara de Seguridad", "Grabación en HD y detección de movimiento", true, true, true));
            productList.add(new Product(5, "Purificador de Aire", "Filtro HEPA y ionización negativa", false, false, false));
            productList.add(new Product(6, "Máquina de Hielo", "Producción rápida de hielo en cubos", true, false, false));
            productList.add(new Product(7, "Sistema de Sonido Inalámbrico", "Conectividad Bluetooth y batería recargable", true, false, false));
            productList.add(new Product(8, "Lavadora de Alta Eficiencia", "Ciclos de lavado personalizados y ahorro de energía", true, false, false));
            productList.add(new Product(9, "Secadora de Ropa", "Ciclos de secado rápidos y eficientes", true, false, false));
            productList.add(new Product(10, "Cortadora de Césped Eléctrica", "Batería recargable y corte ajustable", true, false, false));
            productList.add(new Product(11, "Luz Solar Exterior", "Iluminación LED y paneles solares", false, false, false));
            productList.add(new Product(12, "Termo Eléctrico", "Mantén tus bebidas calientes durante horas", false, false, false));
            productList.add(new Product(13, "Mesa de Juego", "Superficie de juego grande y almacenamiento integrado", false, false, false));
            productList.add(new Product(14, "Silla de Oficina Ergonómica", "Diseño ergonómico y ajustes personalizados", true, false, false));
            productList.add(new Product(15, "Equipo de Gimnasio en Casa", "Peso ajustable y múltiples ejercicios", true, false, false));
            productList.add(new Product(16, "Proyector Portátil", "Resolución HD y conectividad HDMI", true, false, false));
            productList.add(new Product(17, "Altavoz Portátil con Micrófono", "Conectividad Bluetooth y batería recargable", true, false, false));
            productList.add(new Product(18, "Kit de Herramientas Básicas", "Incluye llaves, alicates y destornilladores", false, false, false));
            productList.add(new Product(19, "Cama Inflable para Camping", "Diseño compacto y fácil de inflar", false, false, false));
            productList.add(new Product(20, "Mochila de Viaje con Ruedas", "Capacidad grande y manijas ajustables", true, false, false));
        }
    }

    /**
     * Método para obtener la lista de productos pendientes de compra
     *
     * @return - Lista de productos pendientes de compra
     */
    public static ArrayList<Product> getProductListPending() {
        ArrayList<Product> productsPending = new ArrayList<>();
        for (Product product : productList) {
            if (product.isState()) {
                productsPending.add(product);
            }
        }
        return productsPending;
    }

    /**
     * Método para obtener la lista de productos no pendientes de compra
     *
     * @return - Lista de productos no pendientes
     */
    public static ArrayList<Product> getProductListNotPending() {
        ArrayList<Product> productsNotPending = new ArrayList<>();

        for (Product prod : productList) {
            if (!prod.isState()) {
                productsNotPending.add(prod);
            }
        }

        return productsNotPending;
    }

    /**
     * Método para obtener el último id de la lista
     * @return
     */
    public static int getLastIdByProductList() {
        int id = 1;

        for (Product prod : productList) {
            if (prod.getId() == id) {
                id++;
            } else {
                break;  // Se sale del bucle cuando encuentra el próximo ID disponible.
            }
        }

        return id;
    }

    /**
     * Método para obtener un producto por su id
     *
     * @param id - Id del producto
     * @return - Objeto Product
     */
    public static Product getProductById(int id) {
        for (Product prod : productList) {
            if (prod.getId() == id) {
                return prod;
            }
        }

        return new Product(); // Retorna un producto vacío si no se encuentra.
    }

    /**
     * Función para añadir un producto a la lista
     *
     * @param product - Producto que vamos a añadir
     * @param view    - Vista actual
     */
    public static void addProduct(Product product, AddNewProductActivity view) {
        for (Product p : productList) {
            if (p.getId() == product.getId()) {
                Toast.makeText(view, "There is already a product with this id", Toast.LENGTH_SHORT).show();
                return;
            }

            if (product.getName().equalsIgnoreCase(p.getName())) {
                Toast.makeText(view, "There is already a product with this name", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Toast.makeText(view, "Product successfully added", Toast.LENGTH_SHORT).show();
        productList.add(product);
    }
}
