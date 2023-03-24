package com.example.springbootshop.web.dto.mapper;

import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.web.dto.pojo.UserWithoutOrderListDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = {RoleFullMapper.class})
@Component
public interface UserWithoutOrderListMapper {

    UserWithoutOrderListDTO convertFromEntity(User entity);

    User convertFromDTO(UserWithoutOrderListDTO dto);
}
