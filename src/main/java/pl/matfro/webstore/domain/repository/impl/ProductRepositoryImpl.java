package pl.matfro.webstore.domain.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import pl.matfro.webstore.domain.Product;
import pl.matfro.webstore.domain.repository.ProductRepository;
import pl.matfro.webstore.exception.ProductNotFoundException;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public ProductRepositoryImpl() {
    }

    @Override
    @Transactional
    public List<Product> getAllProducts() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        criteriaQuery.from(Product.class);
        return session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    @Transactional
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public void addProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.save(product);
    }

    @Override
    @Transactional
    public Product getProductById(long productId) throws IllegalArgumentException {
        Session session = sessionFactory.getCurrentSession();
        Product p = session.get(Product.class, productId);

        if (p == null) {
            throw new ProductNotFoundException(productId);
        }

        return p;
    }

    @Override
    @Transactional
    public List<Product> getProductsByCategory(String category) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Product> query = session.createQuery("select p from Product p where p.category = :category", Product.class);
        query.setParameter("category", category);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Product> getProductsByManufacturer(String manufacturer) {
        Session session = sessionFactory.getCurrentSession();
        TypedQuery<Product> query = session.createQuery("select p from Product p where p.manufacturer = :manufacturer", Product.class);
        query.setParameter("manufacturer", manufacturer);
        return query.getResultList();
    }

    public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        Set<Product> productsByBrand = new HashSet<Product>();
        Set<Product> productsByCategory = new HashSet<Product>();
        Set<String> criterias = filterParams.keySet();

        if (criterias.contains("brand")) {
            for (String brandName : filterParams.get("brand")) {
                productsByBrand.addAll(this.getProductsByManufacturer(brandName));
            }
        }

        if (criterias.contains("category")) {
            for (String category : filterParams.get("category")) {
                productsByCategory.addAll(this.getProductsByCategory(category));
            }
        }

        productsByCategory.retainAll(productsByBrand);
        return productsByCategory;
    }

    @Override
    @Transactional
    public Set<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams) {

        Set<Product> productsAboveLow = new HashSet<Product>();
        Set<Product> productsBelowHigh = new HashSet<Product>();
        Set<String> criterias = filterParams.keySet();

        Session session = sessionFactory.getCurrentSession();

        if (criterias.contains("low")) {
            for (String lowLimitText : filterParams.get("low")) {
                BigDecimal lowLimit = new BigDecimal(lowLimitText);
                System.out.println("low " + lowLimit);
                TypedQuery<Product> query = session.createQuery("select p from Product p where p.unitPrice > :lowLimit", Product.class);
                query.setParameter("lowLimit", lowLimit);
                productsAboveLow.addAll(query.getResultList());
            }
        }

        if (criterias.contains("high")) {
            for (String highLimitText : filterParams.get("high")) {
                BigDecimal highLimit = new BigDecimal(highLimitText);
                System.out.println("low " + highLimit);
                TypedQuery<Product> query = session.createQuery("select p from Product p where p.unitPrice < :highLimit", Product.class);
                query.setParameter("highLimit", highLimit);
                productsBelowHigh.addAll(query.getResultList());
            }
        }
        productsBelowHigh.retainAll(productsAboveLow);
        return productsBelowHigh;
    }
}
