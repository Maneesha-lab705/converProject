package dao;

import model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements  CrudDAO<ItemDTO,String>{
    @Override
    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> allItems = new ArrayList<>();
        ResultSet rst =CrudUtil.execute("SELECT * FROM Item");
        while (rst.next()) {
            allItems.add(new ItemDTO(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")));
        }
        return allItems;

    }

    @Override
    public boolean add(ItemDTO dto) throws SQLException, ClassNotFoundException {
        String sql="INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)";
        return CrudUtil.execute(sql,dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
    }

    @Override
    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException {
        String sql="UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?";
        return CrudUtil.execute(sql,dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        String sql="SELECT code FROM Item WHERE code=?";
        return  CrudUtil.execute(sql,s);
    }

    @Override
    public String generate() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        String sql= "DELETE FROM Item WHERE code=?";
        return CrudUtil.execute(sql,s);
    }

    @Override
    public ItemDTO search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT * FROM Item WHERE code=?",s);
        rst.next();
        return new ItemDTO(s + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
    }

//    @Override
//    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
//
//
//        ArrayList<ItemDTO> allItems = new ArrayList<>();
//        ResultSet rst =CrudUtil.execute("SELECT * FROM Item");
//        while (rst.next()) {
//            allItems.add(new ItemDTO(rst.getString("code"), rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")));
//        }
//        return allItems;
//    }
//
//    @Override
//    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
//
//
//        String sql= "DELETE FROM Item WHERE code=?";
//        return CrudUtil.execute(sql,code);
//    }
//
//    @Override
//    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
///
//
//        String sql="INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)";
//        return CrudUtil.execute(sql,dto.getCode(),dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand());
//    }
//
//    @Override
//    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
//
//
//       String sql="UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?";
////        return CrudUtil.execute(sql,dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
//    }
//
//    @Override
//    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
//
//
//        String sql="SELECT code FROM Item WHERE code=?";
//        return  CrudUtil.execute(sql,code);
//    }
//
//    @Override
//    public String generateNewCode() throws SQLException, ClassNotFoundException {
//
//        ResultSet rst = CrudUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
//        if (rst.next()) {
//            String id = rst.getString("code");
//            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
//            return String.format("I00-%03d", newItemId);
//        } else {
//            return "I00-001";
//        }
//    }
//
//    @Override
//    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
//
//        ResultSet rst=CrudUtil.execute("SELECT * FROM Item WHERE code=?",code);
//        rst.next();
//        return new ItemDTO(code + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
//    }
}