package pl.matfro.webstore.exception;

public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -694354952032299587L;
    private long productId;

    public ProductNotFoundException(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }
}
