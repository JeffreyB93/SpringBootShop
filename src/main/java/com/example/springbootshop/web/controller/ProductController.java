package com.example.springbootshop.web.controller;

import com.example.springbootshop.dao.entity.Product;
import com.example.springbootshop.exception.ControllerException;
import com.example.springbootshop.exception.ServiceException;
import com.example.springbootshop.service.ProductService;
import com.example.springbootshop.web.dto.mapper.ProductFullMapper;
import com.example.springbootshop.web.dto.pojo.ProductFullDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {

    private final ProductService productService;
    private final ProductFullMapper productFullMapper;

    public ProductController(ProductService productService,
                             ProductFullMapper productFullMapper) {
        this.productFullMapper = productFullMapper;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductFullDTO>> findAll() {
        List<Product> productList = productService.findAll();
        List<ProductFullDTO> productFullDTOList = new ArrayList<>();
        for (Product product : productList) {
            productFullDTOList.add(productFullMapper.convertFromEntity(product));
        }
        return ResponseEntity.ok(productFullDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductFullDTO> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        ProductFullDTO productFullDTO = productFullMapper.convertFromEntity(product);
        return ResponseEntity.ok(productFullDTO);
    }

    @GetMapping("/name")
    public ResponseEntity<ProductFullDTO> findByName(@RequestParam(name = "name") String name) {
        Product product = productService.findByName(name);
        ProductFullDTO productFullDTO = productFullMapper.convertFromEntity(product);
        return ResponseEntity.ok(productFullDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductFullDTO> update(@PathVariable Long id, @RequestBody Product product) throws ControllerException {
        Product productUpdate = null;// TODO: 01.04.2023 также как в юзере
        try {
            productUpdate = productService.update(id, product);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        ProductFullDTO productUpdateFullDTO = productFullMapper.convertFromEntity(productUpdate);
        return ResponseEntity.ok(productUpdateFullDTO);
    }

    @PostMapping
    public ResponseEntity<ProductFullDTO> save(@RequestBody Product product) throws ControllerException {
        try {
            Product productSave = productService.save(product);
            ProductFullDTO productSaveFullDTO = productFullMapper.convertFromEntity(productSave);
            return new ResponseEntity<>(productSaveFullDTO, HttpStatus.CREATED);
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