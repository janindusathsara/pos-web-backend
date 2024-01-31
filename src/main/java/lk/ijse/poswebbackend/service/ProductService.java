package lk.ijse.poswebbackend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.poswebbackend.dto.ProductDeleteDto;
import lk.ijse.poswebbackend.dto.ProductDto;
import lk.ijse.poswebbackend.entity.Product;

@Service
public interface ProductService {

    Product createProduct(ProductDto productDto, Long id);
    Product updateProduct(Long id, ProductDto productDto);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    List<Product> getProductByCategory(Long id); 
    List<Product> getProductByUser(Long id);
    Product deleteProduct(ProductDeleteDto deleteDto, Long id);
}
