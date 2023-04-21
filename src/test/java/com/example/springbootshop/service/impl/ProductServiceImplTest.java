package com.example.springbootshop.service.impl;

import com.example.springbootshop.dao.entity.Product;
import com.example.springbootshop.dao.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findAll() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(new Product()));
        List<Product> actual = productService.findAll();
        assertEquals(1, actual.size());
        verify(productRepository, times(1)).findAll();// - для метода void  never()
    }

    @Test
    public void findAllException() {
        doThrow(RuntimeException.class).when(productRepository).findAll();
        assertThrows(RuntimeException.class,()->productService.findAll());
        verify(productRepository, times(1)).findAll();// - для метода void  never()
    }

    @Test
    public void findById() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void update() {
    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }
}