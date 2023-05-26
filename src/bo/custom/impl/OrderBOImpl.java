package bo.custom.impl;

import bo.custom.OrderBO;
import dao.custom.OrderDAO;
import dao.custom.impl.OrderDAOImpl;
import model.OrderDTO;

import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO =new OrderDAOImpl();

    @Override
    public String generateOID() throws SQLException, ClassNotFoundException {
        return orderDAO.generate();
    }

    @Override
    public boolean existOrder(String orderId) throws SQLException, ClassNotFoundException {
        return orderDAO.exist(orderId);
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return orderDAO.add(dto);
    }
}
