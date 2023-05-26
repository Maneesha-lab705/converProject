package bo.custom;

import model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailBO {
    boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
