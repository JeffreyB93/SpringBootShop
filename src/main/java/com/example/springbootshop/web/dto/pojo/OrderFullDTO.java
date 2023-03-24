package com.example.springbootshop.web.dto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderFullDTO {

    private Long id;

    private Integer amount;

    private String description;

    private LocalDateTime createDateTime;

    private List<ProductFullDTO> productFullDTOList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderFullDTO that = (OrderFullDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(createDateTime, that.createDateTime) && Objects.equals(productFullDTOList, that.productFullDTOList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, description, createDateTime, productFullDTOList);
    }
}
