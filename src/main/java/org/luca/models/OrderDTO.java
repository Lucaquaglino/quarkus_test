package org.luca.models;

import lombok.Data;

@Data
public class OrderDTO {
    private Long order_id;
    private Long user_id;
    private int quantity;
    private String product;
}
