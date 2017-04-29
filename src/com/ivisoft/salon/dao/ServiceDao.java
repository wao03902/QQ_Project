package com.ivisoft.salon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ivisoft.salon.model.Service;
import com.ivisoft.salon.utils.DBUtil;

public class ServiceDao {
    
    public static Service getServiceById(int id) {
        
        Service service = null;
        
        String query = "SELECT * FROM qq_services WHERE id_service = " + id;
        
        try (Connection conn = DBUtil.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            
            if (rs.next()) {
                service = getServiceFromResultSet(rs);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return service;
    }
    
    public static List<Service> getAllServices() {
        
        List<Service> services = new ArrayList<>();
        
        String query = "SELECT * FROM qq_services";
        
        try (Connection conn = DBUtil.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                services.add(getServiceFromResultSet(rs));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
    
    public static List<Service> getServicesByMasterId(int id) {
        
        List<Service> services = new ArrayList<>();
        
        String query = "SELECT * FROM qq_masters_services WHERE id_master = " + id;
        String preparedQuery = "SELECT * FROM qq_services WHERE id_master = ?";
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(preparedQuery);
            ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                
                ps.setInt(1, rs.getInt("id_service"));
                
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    services.add(getServiceFromResultSet(res));
                }
                res.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return services;
    }
    
    public static void createService(Service service) {
        
        String query = "INSERT INTO qq_services(service_name, service_price, service_duration, id_service_type, service_description, service_status) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, service.getName());
            pst.setInt(2, service.getPrice());
            pst.setInt(3, service.getDuration());
            pst.setInt(4, service.getType().getId());
            pst.setString(5, service.getDescription());
            pst.setInt(6, service.getStatus());
            
            pst.executeUpdate(query);
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateService(Service service) {
        
        String query = "UPDATE qq_services SET service_name = ?, service_price = ?, service_duration = ?, "
                + "id_service_type = ?, service_description = ?, service_status = ? WHERE id_service = ?";
        
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, service.getName());
            pst.setInt(2, service.getPrice());
            pst.setInt(3, service.getDuration());
            pst.setInt(4, service.getType().getId());
            pst.setString(5, service.getDescription());
            pst.setInt(6, service.getStatus());
            pst.setInt(7, service.getId());
            
            pst.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteService(Service service) {
        String query = "DELETE FROM qq_services WHERE id_service = " + service.getId();
        try (Connection conn = DBUtil.getConnection();
            Statement stat = conn.createStatement()) {
            stat.execute(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static Service getServiceFromResultSet(ResultSet rs) throws SQLException {
        Service service = new Service();
        
        service.setId(rs.getInt("id_service"));
        service.setName(rs.getString("service_name"));
        service.setPrice(rs.getInt("service_price"));
        service.setDuration(rs.getInt("service_duration"));
        service.setDescription(rs.getString("service_description"));
        service.setStatus(rs.getInt("service_status"));
        service.setCreateDate(rs.getTimestamp("service_createdate").toLocalDateTime());
        service.setType(DictionaryDao.getDictById(rs.getInt("id_service_type")));
        
        return service;
    }
}
