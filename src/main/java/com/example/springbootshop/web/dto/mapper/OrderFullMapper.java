package com.example.springbootshop.web.dto.mapper;


import com.example.springbootshop.dao.entity.Order;
import com.example.springbootshop.web.dto.pojo.OrderFullDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel ="spring", uses = {ProductFullMapper.class})
public interface OrderFullMapper {

    @Mapping(source = "productList", target = "productFullDTOList")
    OrderFullDTO convertFromEntity(Order entity);

    @Mapping(source = "productFullDTOList", target = "productList")
    Order convertFromDTO(OrderFullDTO dto);

    @Mapping(source = "productList", target = "productFullDTOList")
    List<OrderFullDTO> convertFromEntityList(List<Order> entityList);

    @Mapping(source = "productFullDTOList", target = "productList")
    List<Order> convertFromDTOList(List<OrderFullDTO> dtoList);
}