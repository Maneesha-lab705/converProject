package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.OrderDAO;
import model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<OrderDTO> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(OrderDTO dto) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)";
        return CrudUtil.execute(sql,dto.getOrderId(),dto.getOrderDate(),dto.getCustomerId());
    }

    @Override
    public boolean update(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        String sql ="SELECT oid FROM `Orders` WHERE oid=?";
        return CrudUtil.execute(sql,s);
    }

    @Override
    public String generate() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDTO search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

//    @Override
//    public String generateOID() throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = CrudUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
//        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
//    }
//
//    @Override
//    public boolean existOrder(String orderId) throws SQLException, ClassNotFoundException {
//
//        String sql ="SELECT oid FROM `Orders` WHERE oid=?";
//        return CrudUtil.execute(sql,orderId);
//
//    }
//
//    @Override
//    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
//
//
//        String sql ="INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)";
//        return CrudUtil.execute(sql,dto.getOrderId(),dto.getOrderDate(),dto.getCustomerId());
//    }
}
