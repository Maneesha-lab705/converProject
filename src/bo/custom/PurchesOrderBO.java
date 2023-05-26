package bo.custom;

import model.OrderDetailDTO;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public interface PurchesOrderBO {
    public boolean PurchersOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails);
}
