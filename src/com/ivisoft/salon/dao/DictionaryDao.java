package com.ivisoft.salon.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ivisoft.salon.model.Dictionary;
import com.ivisoft.salon.utils.DBUtil;

public class DictionaryDao {
    
    public static Dictionary getDictById(int id) {
        
        Dictionary dict = new Dictionary();
        
        String query = "SELECT * FROM qq_dictionary WHERE id = " + id;
        
        try (Connection conn = DBUtil.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query)) {
            
            if (rs.next()) {
                getDictFromResultSet(rs);
            }
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dict;
    }
    
    private static Dictionary getDictFromResultSet(ResultSet rs) throws SQLException {
        Dictionary dict = new Dictionary();
        
        dict.setId(rs.getInt("id"));
        dict.setCaption(rs.getString("caption"));
        dict.setType(rs.getString("type"));
        dict.setStatus(rs.getInt("status"));
        dict.setCreateDate(rs.getTimestamp("createdate").toLocalDateTime());
        
        return dict;
    }
}
