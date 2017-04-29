package com.ivisoft.salon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ivisoft.salon.model.Visit;
import com.ivisoft.salon.utils.DBUtil;

public class VisitDao {
    
    public static Visit getVisitById(int id) {
        Visit visit = null;
        
        String query = "SELECT * FROM qq_visits WHERE id_visit = " + id;
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            if (rs.next()) {
                visit = getVisitFromResultSet(rs);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return visit;
    }

    public static List<Visit> getAllVisits() {
        
        List<Visit> visits = new ArrayList<>();
        
        String query = "SELECT * FROM qq_visits";
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                visits.add(getVisitFromResultSet(rs));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return visits;
    }
    
    public static List<Visit> getVisitsByClientId(int id) {
        
        List<Visit> visits = new ArrayList<>();
        
        String query = "SELECT * FROM qq_visits WHERE id_client = " + id;
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                visits.add(getVisitFromResultSet(rs));
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return visits;
    }
    
    public static List<Visit> getVisitsByServiceId(int id) {
        
        List<Visit> visits = new ArrayList<>();
        
        String query = "SELECT * FROM qq_visits WHERE id_service = " + id;
        
        try (Connection conn = DBUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                visits.add(getVisitFromResultSet(rs));
            }
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return visits;
    }
    
    public static void createVisit(Visit visit) {
        String query = "INSERT INTO qq_visits(id_client, id_service, visit_price, visit_duration, "
                + "visit_discount, visit_total_price, visit_date, visit_status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, visit.getClient().getId());
            pst.setInt(2, visit.getService().getId());
            pst.setInt(3, visit.getPrice());
            pst.setInt(4, visit.getDuration());
            pst.setInt(5, visit.getDiscount());
            pst.setInt(6, visit.getTotalPrice());
            pst.setTimestamp(7, Timestamp.valueOf(visit.getDateAndTime()));
            pst.setInt(8, visit.getStatus());
            
            pst.executeUpdate(query);
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateVisit(Visit visit) {
        
        String query = "UPDATE qq_visits SET "
                + "id_client = ?, id_service = ?, visit_price = ?, "
                + "visit_duration = ?, visit_discount = ?, visit_total_price = ?, "
                + "visit_date = ?, visit_status = ? WHERE id_client = ?";
        
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query)) {
            
            pst.setInt(1, visit.getClient().getId());
            pst.setInt(2, visit.getService().getId());
            pst.setInt(3, visit.getPrice());
            pst.setInt(4, visit.getDuration());
            pst.setInt(5, visit.getDiscount());
            pst.setInt(6, visit.getTotalPrice());
            pst.setTimestamp(7, Timestamp.valueOf(visit.getDateAndTime()));
            pst.setInt(8, visit.getStatus());
            pst.setInt(9, visit.getId());
            
            pst.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteVisit(Visit visit) {
        String query = "DELETE FROM qq_visits WHERE id_visit = " + visit.getId();
        try (Connection conn = DBUtil.getConnection();
            Statement stat = conn.createStatement()) {
            stat.execute(query);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private static Visit getVisitFromResultSet(ResultSet rs) throws SQLException {
        Visit visit = new Visit();
        
        visit.setId(rs.getInt("id_visit"));
        visit.setPrice(rs.getInt("visit_price"));
        visit.setDuration(rs.getInt("visit_duration"));
        visit.setDiscount(rs.getInt("visit_discount"));
        visit.setDateAndTime(rs.getTimestamp("visit_date").toLocalDateTime());
        visit.setStatus(rs.getInt("visit_status"));
        visit.setTotalPrice(rs.getInt("visit_total_price"));
        visit.setClient(ClientDao.getClientById(rs.getInt("id_client")));
        visit.setService(ServiceDao.getServiceById(rs.getInt("id_service")));
        
        return visit;
    }
}