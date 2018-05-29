package pl.matfro.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pl.matfro.webstore.domain.Product;
import pl.matfro.webstore.exception.NoProductsFoundUnderCategoryException;
import pl.matfro.webstore.exception.ProductNotFoundException;
import pl.matfro.webstore.service.ProductService;
import pl.matfro.webstore.validator.ProductValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/webstore/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @InitBinder
    public void initialiseBinder(WebDataBinder binder) {
        binder.setDisallowedFields("unitsInOrder", "discontinued");
        binder.setValidator(productValidator);
    }

    @RequestMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/all")
    public String allProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @RequestMapping("/{category}")
    public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
        List<Product> products = productService.getProductsByCategory(productCategory);
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundUnderCategoryException();
        }
        model.addAttribute("products", products);
        return "products";
    }

    @RequestMapping("/filter/{ByCriteria}")
    public String getProductsByFilter(@MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams, Model model) {
        model.addAttribute("products", productService.getProductsByFilter(filterParams));
        return "products";
    }

    @RequestMapping("/product")
    public String getProductById(@RequestParam("id") long productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "product";
    }

    @RequestMapping("/{category}/{ByCriteria}")
    public String filterProducts(@RequestParam("manufacturer") String manufacturer,
                                 @PathVariable("category") String productCategory, @MatrixVariable(pathVar = "ByCriteria") Map<String, List<String>> filterParams, Model model) {

        List productsByCategory = productService.getProductsByCategory(productCategory);
        List productsByManufacturer = productService.getProductsByManufacturer(manufacturer);
        Set<Product> productsByPriceFilter = productService.getProductsByPriceFilter(filterParams);

        productsByPriceFilter.retainAll(productsByCategory);
        productsByPriceFilter.retainAll(productsByManufacturer);

        model.addAttribute("products", productsByPriceFilter);

        return "products";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public String getAddNewProductForm(Model model) {
        Product newProduct = new Product();
        model.addAttribute("newProduct", newProduct);
        return "addProduct";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')")
    public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product newProduct, BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "addProduct";
        }

        productService.addProduct(newProduct);

        MultipartFile productImage = newProduct.getProductImage();
        MultipartFile productManual = newProduct.getProductManual();

        String rootDirectory = request.getSession().getServletContext().getRealPath("/");

        if (productImage != null && !productImage.isEmpty()) {
            try {
                productImage.transferTo(new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".png"));
            } catch (Exception e) {
                throw new RuntimeException("Niepowodzenie podczas próby zapisu obrazka produktu", e);
            }
        }

        if (productManual != null && !productManual.isEmpty()) {
            try {
                productManual.transferTo(new File(rootDirectory + "resources\\pdf\\" + newProduct.getProductId() + ".pdf"));
            } catch (Exception e) {
                throw new RuntimeException("Niepowodzenie podczas próby zapisu instrukcji obsługi produktu", e);
            }
        }

        String[] suppressedFields = result.getSuppressedFields();

        if (suppressedFields.length > 0) {
            throw new RuntimeException("Próba wiązania niedozwolonych pól:" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
        }

        return "redirect:/webstore/products";
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("invalidProductId", exception.getProductId());
        mav.addObject("exception", exception);
        mav.addObject("url", req.getRequestURL() + "?" + req.getQueryString());
        mav.setViewName("productNotFound");
        return mav;
    }

    @RequestMapping("/invalidPromoCode")
    public String invalidPromoCode() {
        return "invalidPromoCode";
    }
}