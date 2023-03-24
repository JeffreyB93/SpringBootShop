package com.example.springbootshop.web.dto.mapper;


import com.example.springbootshop.dao.entity.Order;
import com.example.springbootshop.web.dto.pojo.OrderFullDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel ="spring", uses = {ProductFullMapper.class})
@Component
public interface OrderFullMapper {
    OrderFullDTO convertFromEntity(Order entity);
    Order convertFromDTO(OrderFullDTO dto);
}
