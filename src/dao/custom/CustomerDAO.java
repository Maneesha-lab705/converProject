package dao.custom;

import model.CustomerDTO;

public interface CustomerDAO extends CrudDAO<CustomerDTO,String> {

    public void getAllCustomer();
//     public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
//
//
//     public boolean addCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
//
//     public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
//
//     public boolean existCustomer(String id) throws SQLException, ClassNotFoundException ;
//
//     public String generateNewID() throws SQLException, ClassNotFoundException ;
//
//
//     public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
//
//
//     public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException ;
}
