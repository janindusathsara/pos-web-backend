package lk.ijse.poswebbackend.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderDto {
    private List<Long> products;
}
