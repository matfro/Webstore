package pl.matfro.webstore.domain.repository;

import pl.matfro.webstore.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProductRepository {

    List<Product> getAllProducts();

    void addProduct(Product product);

    Product getProductById(long productId);

    List<Product> getProductsByCategory(String category);

    Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);

    List<Product> getProductsByManufacturer(String manufacturer);

    Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams);
}
