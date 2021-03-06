package pl.matfro.webstore.service;

import pl.matfro.webstore.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductService {
    List<Product> getAllProducts();

    void addProduct(Product product);

    Product getProductById(long productId);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByManufacturer(String category);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams);
}
