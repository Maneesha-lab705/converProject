package dao.custom.impl;

import dao.CrudUtil;
import dao.custom.CustomerDAO;
import model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
             ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");


        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address"));
            allCustomers.add(customerDTO);
        }
        return allCustomers;
    }

    @Override
    public boolean add(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        String sql ="INSERT INTO Customer (id,name, address) VALUES (?,?,?)";
        return  CrudUtil.execute(sql,dto.getId(),dto.getName(),dto.getAddress());
    }

    @Override
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Customer SET name=?, address=? WHERE id=?";
        return CrudUtil.execute(sql,dto.getName(),dto.getAddress(),dto.getId());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        String sql="SELECT id FROM Customer WHERE id=?";
       return CrudUtil.execute(sql,s);
    }

    @Override
    public String generate() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String sql ="DELETE FROM Customer WHERE id=?";
        return CrudUtil.execute(sql,s);
    }

    @Override
    public CustomerDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst =CrudUtil.execute("SELECT * FROM Customer WHERE id=?",s);
        rst.next();
        return new CustomerDTO(s + "", rst.getString("name"), rst.getString("address"));
    }

    @Override
    public void getAllCustomer() {
        System.out.println("hi");
    }


//    @Override
//    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
//
//             ArrayList<CustomerDTO> allCustomers = new ArrayList<>();
//             ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
//
//
//        while (rst.next()) {
//            CustomerDTO customerDTO = new CustomerDTO(rst.getString("id"), rst.getString("name"), rst.getString("address"));
//            allCustomers.add(customerDTO);
//        }
//        return allCustomers;
//    }
//
//    @Override
//    public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
//        String sql ="INSERT INTO Customer (id,name, address) VALUES (?,?,?)";
//        return  CrudUtil.execute(sql,dto.getId(),dto.getName(),dto.getAddress());
//    }
//
//    @Override
//    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
////
//
//        String sql="UPDATE Customer SET name=?, address=? WHERE id=?";
//        return CrudUtil.execute(sql,dto.getName(),dto.getAddress(),dto.getId());
//    }
//
//    @Override
//    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
////
//        String sql="SELECT id FROM Customer WHERE id=?";
//        return CrudUtil.execute(sql,id);
//    }
//
//    @Override
//    public String generateNewID() throws SQLException, ClassNotFoundException {
//
//        ResultSet rst=CrudUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
//        if (rst.next()) {
//            String id = rst.getString("id");
//            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
//            return String.format("C00-%03d", newCustomerId);
//        } else {
//            return "C00-001";
//        }
//    }
//
//    @Override
//    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
////
//
//        String sql ="DELETE FROM Customer WHERE id=?";
//        return CrudUtil.execute(sql,id);
//    }
//
//    @Override
//    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
////
//          ResultSet rst =CrudUtil.execute("SELECT * FROM Customer WHERE id=?",id);
//          rst.next();
//          return new CustomerDTO(id + "", rst.getString("name"), rst.getString("address"));
//
//    }


}