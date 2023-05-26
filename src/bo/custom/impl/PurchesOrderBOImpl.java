package bo.custom.impl;

import bo.custom.ItemBO;
import bo.custom.OrderBO;
import bo.custom.OrderDetailBO;
import bo.custom.PurchesOrderBO;
import db.DBConnection;
import model.ItemDTO;
import model.OrderDTO;
import model.OrderDetailDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PurchesOrderBOImpl implements PurchesOrderBO {
    public boolean PurchersOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
        Connection connection = null;
        try {
            connection= DBConnection.getDbConnection().getConnection();
            OrderBO orderBO = new OrderBOImpl();
            //Check order id already exist or not
            boolean b1 = orderBO.existOrder(orderId);
            /*if order id already exist*/
            if (b1) {
                return false;
            }

            connection.setAutoCommit(false);

            //Save the Order to the order table
            boolean b2 = orderBO.saveOrder(new OrderDTO(orderId, orderDate, customerId));

            if (!b2) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            // add data to the Order Details table
            OrderDetailBO orderDetailsBO = new OrderDetailBOImpl();
            for (OrderDetailDTO detail : orderDetails) {
                boolean b3 = orderDetailsBO.saveOrderDetails(detail);
                if (!b3) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                //update item
                ItemBO itemBO = new ItemBOImpl();
                boolean b = itemBO.updateItem(new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));

                if (!b) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    ItemBO itemBO = new ItemBOImpl();

    public ItemDTO findItem(String code) {
        try {

            return itemBO.searchItem(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
