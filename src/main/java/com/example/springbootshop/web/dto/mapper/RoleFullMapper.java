package com.example.springbootshop.web.dto.mapper;

import com.example.springbootshop.dao.entity.Role;
import com.example.springbootshop.web.dto.pojo.RoleFullDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel ="spring")
@Component
public interface RoleFullMapper {

    RoleFullDTO convertFromEntity(Role entity);

    Role convertFromDTO(RoleFullDTO dto);

    List<RoleFullDTO> convertFromEntityList(List<Role> entityList);

    List<Role> convertFromDTOList(List<RoleFullDTO> dtoList);
}
