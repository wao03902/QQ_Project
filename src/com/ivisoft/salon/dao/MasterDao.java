package com.ivisoft.salon.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ivisoft.salon.model.Master;
import com.ivisoft.salon.model.Service;
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
            
        } catch (ClassNotFoundException | SQLException | IOException e) {
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
            
        } catch (SQLException | ClassNotFoundException | IOException e) {
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
            
        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        
        return masters;
    }
    
    public static Master createMaster(Master master, List<Service> services) {
        
        String query1 = "INSERT INTO qq_masters(master_name, master_phone, master_phone_extra, master_email, master_status, master_photo) VALUES (?, ?, ?, ?, ?, ?)";
        String query2 = "SELECT * FROM qq_masters WHERE master_phone = ?";
        String query3 = "INSERT INTO qq_masters_services(id_master, id_service) VALUES (?, ?)";
        
        Connection conn = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        ResultSet rs = null;
        
        try {
            conn = DBUtil.getConnection();
            
            conn.setAutoCommit(false);
            
            pst1 = conn.prepareStatement(query1);
            
            FileInputStream fileInputStream = new FileInputStream(master.getPhoto());
            int length = (int) master.getPhoto().length();
            
            pst1.setString(1, master.getName());
            pst1.setString(2, master.getPhone());
            pst1.setString(3, master.getExtraPhone());
            pst1.setString(4, master.getEmail());
            pst1.setInt(5, master.getStatus());
            pst1.setBinaryStream(6, fileInputStream, length);
            
            pst1.executeUpdate();
            
            pst2 = conn.prepareStatement(query2);
            pst2.setString(1, master.getPhone());
            rs = pst2.executeQuery();
            if (rs.next()) {
                master.setId(rs.getInt("id_master"));
            }
            
            pst3 = conn.prepareStatement(query3);
            for (Service service : services) {
                pst3.setInt(1, master.getId().intValue());
                pst3.setInt(2, service.getId().intValue());
                pst3.executeUpdate();
            }
            
            conn.commit();
            
        } catch (SQLException | ClassNotFoundException | IOException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pst1.close();
                pst2.close();
                pst3.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return master;
    }
    
    public static Master updateMaster(Master master, List<Service> services) {
        
        List<Service> currentServices = ServiceDao.getServicesByMasterId(master.getId());
        
        List<Service> forDeleting = new ArrayList<>();
        for(Service service : currentServices) {
            if (!services.contains(service)) {
                forDeleting.add(service);
            }
        }
        
        List<Service> forAdditing = new ArrayList<>();
        for(Service service : services) {
            if (!currentServices.contains(service)) {
                forAdditing.add(service);
            }
        }
        
        String query1 = "UPDATE qq_masters SET master_name = ?, master_phone = ?, master_phone_extra = ?, master_email = ?, master_photo = ?, master_status = ? WHERE id_master = ?";
        String query2 = "INSERT INTO qq_masters_services(id_master, id_service) VALUES (?, ?)";
        String query3 = "DELETE FROM qq_masters_services WHERE id_master = ? AND id_service = ?";
        
        Connection conn = null;
        PreparedStatement pst1 = null;
        PreparedStatement pst2 = null;
        PreparedStatement pst3 = null;
        
        try {
            conn = DBUtil.getConnection();
            
            conn.setAutoCommit(false);
            
            pst1 = conn.prepareStatement(query1);
            pst2 = conn.prepareStatement(query2);
            pst3 = conn.prepareStatement(query3);
            
            FileInputStream fileInputStream = new FileInputStream(master.getPhoto());
            int length = (int) master.getPhoto().length();
            
            pst1.setString(1, master.getName());
            pst1.setString(2, master.getPhone());
            pst1.setString(3, master.getExtraPhone());
            pst1.setString(4, master.getEmail());
            pst1.setBinaryStream(5, fileInputStream, length); 
            pst1.setInt(6, master.getStatus());
            pst1.setInt(7, master.getId());
            
            pst1.executeUpdate();
            
            if (!forAdditing.isEmpty()) {
                for (Service service : forAdditing) {
                    pst2.setInt(1, master.getId().intValue());
                    pst2.setInt(2, service.getId().intValue());
                    pst2.executeUpdate();
                }
            }
            
            if (!forDeleting.isEmpty()) {
                for (Service service : forDeleting) {
                    pst3.setInt(1, master.getId().intValue());
                    pst3.setInt(2, service.getId().intValue());
                    pst3.executeUpdate();
                }
            }
            
            conn.commit();
            
        } catch (SQLException | ClassNotFoundException | IOException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                pst1.close();
                pst2.close();
                pst3.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return master;
    }
    
    public static void updateMaster(Master master) {
        
        String query = "UPDATE qq_masters SET master_name = ?, master_phone = ?, master_phone_extra = ?, master_email = ?, master_photo = ?, master_status = ? WHERE id_master = ?";
        
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            conn.setAutoCommit(false);
            
            FileInputStream fileInputStream = new FileInputStream(master.getPhoto());
            int length = (int) master.getPhoto().length();
            
            pst.setString(1, master.getName());
            pst.setString(2, master.getPhone());
            pst.setString(3, master.getExtraPhone());
            pst.setString(4, master.getEmail());
            pst.setBinaryStream(5, fileInputStream, length); 
            pst.setInt(6, master.getStatus());
            pst.setInt(7, master.getId());
            
            pst.executeUpdate();
            
            conn.commit();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
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
    
    private static Master getMasterFromResultSet(ResultSet rs) throws SQLException, FileNotFoundException, IOException {
        Master master = new Master();
        
        master.setId(rs.getInt("id_master"));
        master.setName(rs.getString("master_name"));
        master.setEmail(rs.getString("master_email"));
        master.setPhone(rs.getString("master_phone"));
        master.setExtraPhone(rs.getString("master_phone_extra"));
        master.setStatus(rs.getInt("master_status"));
        master.setCreateDate(rs.getTimestamp("master_createdate").toLocalDateTime());
        
        InputStream x = rs.getBinaryStream("master_photo");
        if (x == null) 
            return master;
        
        int size = x.available();
        byte b[] = new byte[size];
        x.read(b);
        File cacheDir = new File("src\\imageCache");
        if (cacheDir.exists()) {
            cacheDir.delete();
            cacheDir.mkdir();
        } else {
            cacheDir.mkdir();
        }
        
        try (OutputStream targetFile = new FileOutputStream("src\\imageCache\\" + master.getName())) {
            targetFile.write(b);
        }
        
        master.setPhoto(new File("src\\imageCache\\" + master.getName()));
        
        return master;
    }

    public static int countMasters() {
        String query = "SELECT COUNT(*) FROM qq_masters";
        int mastersAmount = 0;
        try (Connection conn = DBUtil.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            
            if (rs.next()) {
                mastersAmount = rs.getInt(1);
            }
                
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return mastersAmount;
    }
}