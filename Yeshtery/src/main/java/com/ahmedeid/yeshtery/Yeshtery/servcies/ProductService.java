package com.ahmedeid.yeshtery.Yeshtery.servcies;

import com.ahmedeid.yeshtery.Yeshtery.entities.Product;
import com.ahmedeid.yeshtery.Yeshtery.repository.ProductRepository;
import com.ahmedeid.yeshtery.Yeshtery.validate.SystemValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> getProductById(int id) {
        return Optional.ofNullable(this.productRepository.findById(id).get());
    }

    public Optional<Product> saveNewProduct(Product product) {
        // validate the product at first ...
        SystemValidation.validateProduct(product);

        // save the new product ...
        return Optional.of(this.productRepository.save(product));
    }
}
