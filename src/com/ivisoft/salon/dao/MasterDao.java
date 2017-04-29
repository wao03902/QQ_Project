package com.ivisoft.salon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ivisoft.salon.model.Master;
import com.ivisoft.salon.utils.DBUtil;

public class MasterDao {
    
    public static Master getMasterById(int id) {
        
        Master master = null;
        
        String query = "SELECT * FROM qq_masters WHERE id_master = " + id;
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            if (rs.next()) {
                master = getMasterFromResultSet(rs);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return master;
    }
    
    public static List<Master> getAllMasters() {
        
        List<Master> masters = new ArrayList<>();
        
        String query = "SELECT * FROM qq_masters";
        
        try (Connection conn = DBUtil.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                masters.add(getMasterFromResultSet(rs));
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return masters;
    }
    
    public static List<Master> getMastersByServiceId(int id) {
        
        List<Master> masters = new ArrayList<>();
        
        String query = "SELECT * FROM qq_masters_services WHERE id_service = " + id;
        String preparedQuery = "SELECT * FROM qq_masters WHERE id_master = ?";
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(preparedQuery);
            ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                ps.setInt(1, rs.getInt("id_master"));
                
                ResultSet res = ps.executeQuery();
                if (res.next()) {
                    masters.add(getMasterFromResultSet(res));
                }
                res.close();
                
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return masters;
    }
    
    public static void createMaster(Master master) {
        
        String query = "INSERT INTO qq_masters(master_name, master_phone, master_extra_phone, master_email, master_status, master_photo) VALUES (?, ?, ?, ?, ?, ?)";
            
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, master.getName());
            pst.setString(2, master.getPhone());
            pst.setString(3, master.getExtraPhone());
            pst.setString(4, master.getEmail());
            pst.setInt(5, master.getStatus());
            pst.setBlob(6, master.getPhoto());
            
            pst.executeUpdate(query);
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateMaster(Master master) {
        
        String query = "UPDATE qq_masters SET master_name = ?, master_phone = ?, master_phone_extra = ?, master_email = ?, master_photo = ?, master_status = ? WHERE id_master = ?";
        
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setString(1, master.getName());
            pst.setString(2, master.getPhone());
            pst.setString(3, master.getExtraPhone());
            pst.setString(4, master.getEmail());
            pst.setBlob(5, master.getPhoto());
            pst.setInt(6, master.getStatus());
            pst.setInt(7, master.getId());
            
            pst.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteMaster(Master master) {
        String query = "DELETE FROM qq_masters WHERE id_master = " + master.getId();
        try (Connection conn = DBUtil.getConnection();
            Statement stat = conn.createStatement()) {
            stat.execute(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static Master getMasterFromResultSet(ResultSet rs) throws SQLException {
        Master master = new Master();
        
        master.setId(rs.getInt("id_master"));
        master.setName(rs.getString("master_name"));
        master.setEmail(rs.getString("master_email"));
        master.setPhone(rs.getString("master_phone"));
        master.setExtraPhone(rs.getString("master_phone_extra"));
        master.setPhoto(rs.getBlob("master_photo"));
        master.setStatus(rs.getInt("master_status"));
        master.setCreateDate(rs.getTimestamp("master_createdate").toLocalDateTime());
        
        return master;
    }
}