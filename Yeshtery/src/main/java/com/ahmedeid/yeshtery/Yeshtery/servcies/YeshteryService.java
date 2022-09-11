package com.ahmedeid.yeshtery.Yeshtery.servcies;

import com.ahmedeid.yeshtery.Yeshtery.dao.YeshteryDao;
import com.ahmedeid.yeshtery.Yeshtery.entities.Product;
import com.ahmedeid.yeshtery.Yeshtery.entities.User;
import com.ahmedeid.yeshtery.Yeshtery.manager.IntegrationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YeshteryService {

    @Autowired
    private YeshteryDao yeshteryDao;

    public Optional<List<Product>> getProductToGeneralPage(String productImagePath) {
        Optional<List<Product>> products = this.yeshteryDao.getProductToUserToGeneralPage();
        return Optional.ofNullable(products.map(productList -> productList.stream().map(product -> {
            String base64OfImage = IntegrationManager.getImageBase64(product.getPhotoPath(), productImagePath);
            product.setPhotoPath(base64OfImage);
            return product;
        }).collect(Collectors.toList())).orElse(null));
    }

    public Optional<List<Product>> getProductToAdministratorPage(String productImagePath) {
        Optional<List<Product>> products = this.yeshteryDao.getProductToAdministratorPage();
        return Optional.ofNullable(products.map(productList -> productList.stream().map(product -> {
            String base64OfImage = IntegrationManager.getImageBase64(product.getPhotoPath(), productImagePath);
            product.setPhotoPath(base64OfImage);
            return product;
        }).collect(Collectors.toList())).orElse(null));
    }

    public Optional<Product> getProductToShow(int productId, String productImagePath) {
        Optional<Product> product = this.yeshteryDao.getProductToShow(productId);
        if(product.isPresent()) {
            String base64OfImage = IntegrationManager.getImageBase64(product.get().getPhotoPath(), productImagePath);
            product.get().setPhotoPath(base64OfImage);
        }
        return product;
    }

    public Optional<Product> getProductToUpdate(int productId, String productImagePath) {
        Optional<Product> product = this.yeshteryDao.getProductToUpdate(productId);
        if(product.isPresent()) {
            String base64OfImage = IntegrationManager.getImageBase64(product.get().getPhotoPath(), productImagePath);
            product.get().setPhotoPath(base64OfImage);
        }
        return product;
    }

    public boolean productUpdateToAccepted(int productId) {
        return this.yeshteryDao.productUpdateToAccepted(productId);
    }

    public boolean productUpdateToRejected(int productId) {
        return this.yeshteryDao.productUpdateToRejected(productId);
    }

    public Optional<User> loginUser(String username, String password) {
        return this.yeshteryDao.login(username, password);
    }
}
