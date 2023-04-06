package com.example.springbootshop.web.dto.mapper;

import com.example.springbootshop.dao.entity.Product;
import com.example.springbootshop.web.dto.pojo.ProductFullDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel ="spring")
@Component
public interface ProductFullMapper {

    ProductFullDTO convertFromEntity(Product entity);

    Product convertFromDTO(ProductFullDTO dto);
}