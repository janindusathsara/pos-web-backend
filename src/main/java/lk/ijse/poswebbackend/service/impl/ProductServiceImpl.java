package lk.ijse.poswebbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.poswebbackend.dto.ProductDeleteDto;
import lk.ijse.poswebbackend.dto.ProductDto;
import lk.ijse.poswebbackend.entity.Category;
import lk.ijse.poswebbackend.entity.Product;
import lk.ijse.poswebbackend.entity.User;
import lk.ijse.poswebbackend.repository.CategoryRepository;
import lk.ijse.poswebbackend.repository.ProductRepository;
import lk.ijse.poswebbackend.repository.UserRepository;
import lk.ijse.poswebbackend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Product createProduct(ProductDto productDto, Long id) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        User user = userRepository.findById(id).orElse(null);
        if (category != null && user != null) {
            Product productEntity = new Product();
            productEntity.setName(productDto.getName());
            productEntity.setCategory(category);
            productEntity.setQty(productDto.getQty());
            productEntity.setPrice(productDto.getPrice());
            productEntity.setUser(user);
            productEntity.setDescription(productDto.getDescription());
            productEntity.setSoldedQty(0);
            productEntity.setStatus(true);
            return productRepository.save(productEntity);

        } else {
            return null;
        }

    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        Category existingCategory = categoryRepository.findById(productDto.getCategoryId()).orElse(null);

        if (existingProduct != null && existingCategory != null) {
            existingProduct.setName(productDto.getName());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setQty(productDto.getQty());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setCategory(existingCategory);

            return productRepository.save(existingProduct);

        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {

        List<Product> allProducts = productRepository.findAll();
        List<Product> activeProducts = new ArrayList<Product>();
        for (Product product : allProducts) {
            if (product.getStatus()) {
                activeProducts.add(product);
            }
        }
        return activeProducts;
        
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null && product.getStatus()) {
            return product;
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getProductByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            List<Product> products = productRepository.findProductByCategory(category);
            List<Product> activeProducts = new ArrayList<Product>();

            for (Product product : products) {
                if (product.getStatus()) {
                    activeProducts.add(product);
                }
            }
            return activeProducts;
            
        } else {
            return null;
        }
    }

    @Override
    public List<Product> getProductByUser(Long id) {

        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            List<Product> products = productRepository.findProductByUser(user);
            List<Product> activeProducts = new ArrayList<Product>();
            
            for (Product product : products) {
                if (product.getStatus()) {
                    activeProducts.add(product);
                }
            }
            return activeProducts;

        } else {
            return null;
        }
    }

    @Override
    public Product deleteProduct(ProductDeleteDto deleteDto, Long id) {
        User user = userRepository.findById(id).orElse(null);
        Product existingProduct = productRepository.findById(deleteDto.getProductId()).orElse(null);

        if (user != null && existingProduct != null && existingProduct.getUser().equals(user) && existingProduct.getStatus()) {
            existingProduct.setStatus(false);
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

}
