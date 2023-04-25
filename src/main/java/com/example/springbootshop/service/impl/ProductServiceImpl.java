package com.example.springbootshop.service.impl;

import com.example.springbootshop.dao.entity.Product;
import com.example.springbootshop.dao.repository.ProductRepository;
import com.example.springbootshop.exception.ServiceException;
import com.example.springbootshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> findAll() {
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        Product product = productOptional.orElseThrow(RuntimeException::new);
        return product;
    }

    @Override
    public Product findByName(String name) {
        Optional<Product> productOptional = productRepository.findByName(name);
        Product product = productOptional.orElseThrow(RuntimeException::new);
        return product;
    }

    @Override
    public Product update(Long id, Product product) throws ServiceException {
        if (!productRepository.existsById(id)) {
            throw new ServiceException("Такого продукта нет");
        }
        product.setId(id);
        Product updateProduct = productRepository.saveAndFlush(product);
        return updateProduct;
    }

    @Override
    public Product save(Product product) throws ServiceException {
        if (product.getId() != null) {
            throw new ServiceException("У данного продукта не id");
        }
        Product saveProduct = productRepository.saveAndFlush(product);
        return saveProduct;
    }

    @Override
    public Long delete(Long id) throws ServiceException {
        if (!productRepository.existsById(id)) {
            throw new ServiceException("Такого продукта нет");
        }
        productRepository.deleteById(id);
        return id;
    }
}