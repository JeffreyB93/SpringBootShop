package com.example.springbootshop.web.dto.mapper;


import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.web.dto.pojo.UserFullDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel ="spring", uses = {OrderFullMapper.class, RoleFullMapper.class})
public interface UserFullMapper {

    @Mapping(source = "orderList", target = "orderFullDTOList")
    @Mapping(source = "roleSet", target = "roleFullDTOSet")
    UserFullDTO convertFromEntity(User entity);

    @Mapping(source = "orderFullDTOList", target = "orderList")
    @Mapping(source = "roleFullDTOSet", target = "roleSet")
    User convertFromDTO(UserFullDTO dto);

    @Mapping(source = "orderList", target = "orderFullDTOList")
    @Mapping(source = "roleSet", target = "roleFullDTOSet")
    List<UserFullDTO> convertFromEntityList(List<User> entityList);

    @Mapping(source = "orderFullDTOList", target = "orderList")
    @Mapping(source = "roleFullDTOSet", target = "roleSet")
    List<User> convertFromDTOList(List<UserFullDTO> dtoList);
}