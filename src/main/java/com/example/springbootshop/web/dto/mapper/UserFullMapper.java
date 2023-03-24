package com.example.springbootshop.web.dto.mapper;


import com.example.springbootshop.dao.entity.User;
import com.example.springbootshop.web.dto.pojo.UserFullDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel ="spring", uses = {OrderFullMapper.class, RoleFullMapper.class})
@Component
public interface UserFullMapper {

    UserFullDTO convertFromEntity(User entity);

    User convertFromDTO(UserFullDTO dto);

    List<UserFullDTO> convertFromEntityList(List<User> entityList);

    List<User> convertFromDTOList(List<UserFullDTO> dtoList);
}
