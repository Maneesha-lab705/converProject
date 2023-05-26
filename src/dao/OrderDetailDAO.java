package dao;

import model.OrderDetailDTO;

import java.sql.SQLException;

public interface OrderDetailDAO {
     boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException;
}
