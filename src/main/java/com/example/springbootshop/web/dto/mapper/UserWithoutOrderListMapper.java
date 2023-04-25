package com.example.springbootshop.web.dto.mapper;

import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.web.dto.pojo.UserWithoutOrderListDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {RoleFullMapper.class})
public interface UserWithoutOrderListMapper {

    @Mapping(source = "roleSet", target = "roleFullDTOSet")
    UserWithoutOrderListDTO convertFromEntity(User entity);

    @Mapping(source = "roleFullDTOSet", target = "roleSet")
    User convertFromDTO(UserWithoutOrderListDTO dto);
}