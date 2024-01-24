package lk.ijse.poswebbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.poswebbackend.dto.ProductDto;
import lk.ijse.poswebbackend.entity.Category;
import lk.ijse.poswebbackend.entity.Product;
import lk.ijse.poswebbackend.repository.CategoryRepository;
import lk.ijse.poswebbackend.repository.ProductRepository;
import lk.ijse.poswebbackend.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product createProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);

        if (category != null) {
            Product productEntity = new Product();
            productEntity.setName(productDto.getName());
            productEntity.setCategory(category);
            productEntity.setQty(productDto.getQty());
            productEntity.setPrice(productDto.getPrice());

            return productRepository.save(productEntity);

        } else {
            return null;
        }

    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        Category existingCategory = categoryRepository.findById(productDto.getCategoryId()).orElse(null);

        if (existingProduct !=null) {
            existingProduct.setName(productDto.getName());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setQty(productDto.getQty());
            existingProduct.setCategory(existingCategory);

            return productRepository.save(existingProduct);

        } else {
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductByCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category != null) {
            return productRepository.findProductByCategory(category);
        } else {
            return null;
        }
    }
    
}
