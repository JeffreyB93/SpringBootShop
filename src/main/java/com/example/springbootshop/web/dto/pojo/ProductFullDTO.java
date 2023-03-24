package com.example.springbootshop.web.dto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductFullDTO {

    private Long id;

    private String name;

    private Integer totalAmount;

    private BigDecimal price;

    private String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductFullDTO that = (ProductFullDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(totalAmount, that.totalAmount) && Objects.equals(price, that.price) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, totalAmount, price, description);
    }
}
