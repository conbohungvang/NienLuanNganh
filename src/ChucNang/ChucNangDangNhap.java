/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChucNang;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dinh Nguyen Hai Dang - B1704721
 */
public class ChucNangDangNhap {

    public static String nhanKetQua(String username, String password) {
        /* Khoi tao */
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ChucNangDangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        String url = "jdbc:sqlserver://localhost:1433;databaseName=co_so_du_lieu;username=sa;password=sa2008";
        
        String loaiTaiKhoan = null;
        try (Connection connection = DriverManager.getConnection(url)) {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM tai_khoan WHERE ten_nguoi_dung = '" + username + "'";
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next() == true) {
                if (resultSet.getString("mat_khau").equals(password)) {
                    loaiTaiKhoan = resultSet.getString("loai_tai_khoan");
                }
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ChucNangDangNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loaiTaiKhoan;
    }
}
