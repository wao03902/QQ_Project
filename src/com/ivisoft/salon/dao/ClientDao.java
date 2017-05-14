package com.ivisoft.salon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ivisoft.salon.model.Client;
import com.ivisoft.salon.utils.DBUtil;

public class ClientDao {
    
    public static Client getClientById(int id) {
        
        Client client = null;
        
        String query = "SELECT * FROM qq_clients WHERE id_client = " + id;
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            if (rs.next()) {
                client = getClientFromResultSet(rs);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return client;
    }
    
    public static Client getClientByPhone(String phone) {
        
        Client client = null;
        
        String query = "SELECT * FROM qq_clients WHERE client_phone = '" + phone + "'";
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            if (rs.next()) {
                client = getClientFromResultSet(rs);
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return client;
    }
    
    public static List<Client> getAllClients() {
        
        List<Client> clients = new ArrayList<>();
        
        String query = "SELECT * FROM qq_clients";
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clients;
    }
    
    public static void createClient(Client client) {
        
        String query = "INSERT INTO qq_clients(client_name, id_client_sex, client_phone, client_description, client_status) VALUES (?, ?, ?, ?, ?)";
                        
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, client.getName());
            pst.setInt(2, client.getSex().getId());
            pst.setString(3, client.getPhone());
            pst.setString(4, client.getDescription());
            pst.setInt(5, client.getStatus());
            
            pst.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateClient(Client client) {
        
        String query = "UPDATE qq_clients SET client_name = ?, id_client_sex = ?, client_phone = ?, client_description = ?, client_status = ? WHERE id_client = ?";
        
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, client.getName());
            pst.setInt(2, client.getSex().getId());
            pst.setString(3, client.getPhone());
            pst.setString(4, client.getDescription());
            pst.setInt(5, client.getStatus());
            pst.setInt(6, client.getId());
            
            pst.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteClient(Client client) {
        String query = "DELETE FROM qq_clients WHERE id_client = " + client.getId();
        try (Connection conn = DBUtil.getConnection();
            Statement stat = conn.createStatement()) {
            stat.execute(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static Client getClientFromResultSet(ResultSet rs) throws SQLException {
        Client client = new Client();
        
        client.setId(rs.getInt("id_client"));
        client.setName(rs.getString("client_name"));
        client.setPhone(rs.getString("client_phone"));
        client.setStatus(rs.getInt("client_status"));
        client.setDescription(rs.getString("client_description"));
        client.setCreateDate(rs.getTimestamp("client_createdate").toLocalDateTime());
        client.setSex(DictionaryDao.getDictById(rs.getInt("id_client_sex")));
    
        return client;
    }
}
