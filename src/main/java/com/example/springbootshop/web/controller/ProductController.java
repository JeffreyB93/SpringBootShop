package com.example.springbootshop.web.controller;

import com.example.springbootshop.dao.entity.Product;
import com.example.springbootshop.exception.ControllerException;
import com.example.springbootshop.exception.ServiceException;
import com.example.springbootshop.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/name")
    public ResponseEntity<Product> findByName(@RequestParam(name = "name") String name) {
        Product product = productService.findByName(name);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) throws ControllerException {
        Product productUpdate = null;
        try {
            productUpdate = productService.update(id, product);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return ResponseEntity.ok(productUpdate);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) throws ControllerException {
        try {
            Product productSave = productService.save(product);
            return new ResponseEntity<>(productSave, HttpStatus.CREATED);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) throws ControllerException {
        try {
            productService.delete(id);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        return id;
    }

}