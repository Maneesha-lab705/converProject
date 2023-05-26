package bo.custom;

import model.OrderDTO;

import java.sql.SQLException;

public interface OrderBO {

    String generateOID() throws SQLException, ClassNotFoundException ;

     boolean existOrder(String orderId) throws SQLException, ClassNotFoundException;

     boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
}
