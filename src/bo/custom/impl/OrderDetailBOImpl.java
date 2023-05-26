package bo.custom.impl;

import bo.custom.OrderDetailBO;
import dao.custom.OrderDetailDAO;
import dao.custom.impl.OrderDetailsDAOImpl;
import model.OrderDetailDTO;

import java.sql.SQLException;

public class OrderDetailBOImpl implements OrderDetailBO {
    @Override
    public boolean saveOrderDetails(OrderDetailDTO dto) throws SQLException, ClassNotFoundException {
        OrderDetailDAO orderDetailDAO =new OrderDetailsDAOImpl();
        return orderDetailDAO.add(dto);

    }
}
