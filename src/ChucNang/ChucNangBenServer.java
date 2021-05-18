/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChucNang;

import ClassDoiTuong.BaoCao;
import ClassDoiTuong.TaiKhoan;
import ClassDoiTuong.ThietBi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ChucNangBenServer {

    static Connection con;

    public ChucNangBenServer() {
    }

    public void moKetNoiDatabase() {
        try {
            String dbURL = "jdbc:sqlserver://localhost;databaseName=co_so_du_lieu;user=sa;password=sa2008";
            con = DriverManager.getConnection(dbURL);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dongKetNoiDatabase() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<TaiKhoan> layKetQuaTaiKhoan(ResultSet rs) {
        ArrayList<TaiKhoan> taiKhoan = new ArrayList<>();
        try {
            while (rs.next()) {
                taiKhoan.add(new TaiKhoan(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            System.out.print("Loi khi lay tai khoai");
//            ex.printStackTrace();
        }
        return taiKhoan;
    }

    public ArrayList<ThietBi> layKetQuaThietBi(ResultSet rs) {
        ArrayList<ThietBi> thietBi = new ArrayList<>();
        try {
            while (rs.next()) {
                thietBi.add(new ThietBi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return thietBi;
    }

    public ArrayList<BaoCao> layKetQuaBaoCao(ResultSet rs) {
        ArrayList<BaoCao> baoCao = new ArrayList<>();
        try {
            while (rs.next()) {
                baoCao.add(new BaoCao(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getObject(6)), rs.getInt(7)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return baoCao;
    }
public void in_Ket_Qua_Tai_Khoan(ArrayList<TaiKhoan> taiKhoan) {
        for (TaiKhoan tk : taiKhoan) {
            System.out.println(tk.getId() + " " + tk.getTenNguoiDung() + " " + tk.getLoaiTaiKhoan() + " " + tk.getTrangThai());
        }
    }
public void in_Ket_Qua_Bao_Cao(ArrayList<BaoCao> baoCao) {
        for (BaoCao tk : baoCao) {
            System.out.println(tk.getId()+" "+ tk.getIdNguoiBaoCao()+ " " + tk.getIdThietBiGapSuCo());
        }
    }
    /*
     "TK01": SELECT FROM 'tai_khoan';
     "TK02": INSERT INTO 'tai_khoan';
     "TK03": UPDATE 'tai_khoan';
     "TK04": UPDATE 'tai_khoan' SET 'trang_thai';
     "TK05": DELETE FROM 'tai_khoan';
    
     "TB01": SELECT FROM 'thiet_bi';
     "TB02": INSERT INTO 'thiet_bi';
     "TB03": UPDATE 'thiet_bi';
     "TB04": UPDATE 'thiet_bi' SET 'trang_thai';
     "TB05": DELETE FROM 'thiet_bi';
    
     "BC01": SELECT FROM 'bao_cao';
     "BC02": INSERT INTO 'bao_cao';
     "BC03": UPDATE 'bao_cao'
     "BC04": DELETE FROM 'bao_cao';
     */
    public void xuLyYeuCau(String maYeuCau) {
        this.moKetNoiDatabase();
        switch (maYeuCau) {
            case "TK01":
                try {
                    Server.danhSachTaiKhoan.clear();
                    Statement s = con.createStatement();
                    String query = "select * from tai_khoan;";
                    ResultSet rs = s.executeQuery(query);
                    Server.danhSachTaiKhoan = this.layKetQuaTaiKhoan(rs);
//                    in_Ket_Qua_Tai_Khoan(Server.danhSachTaiKhoan);
//                    System.out.print(Server.danhSachTaiKhoan.size());
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TK02":
                try {
                    Statement s = con.createStatement();
                    String query = "insert into tai_khoan(ten_nguoi_dung,mat_khau,loai_tai_khoan,trang_thai) "
                            + "values(N'" + Server.taiKhoan.getTenNguoiDung() + "',"
                            + "'" + Server.taiKhoan.getMatKhau() + "',"
                            + "N'" + Server.taiKhoan.getLoaiTaiKhoan() + "',"
                            + "" + Server.taiKhoan.getTrangThai() + ");";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã thêm " + a + " dòng vào bảng tai_khoan");
                    this.xuLyYeuCau("TK01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TK03":
                try {
                    Statement s = con.createStatement();
                    String query = "update tai_khoan set "
                            + "ten_nguoi_dung=N'" + Server.taiKhoan.getTenNguoiDung()
                            + "',loai_tai_khoan=N'" + Server.taiKhoan.getLoaiTaiKhoan()
                            + "',trang_thai=N'" + Server.taiKhoan.getTrangThai()
                            + "' where id_tai_khoan like('" + Server.taiKhoan.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã cập nhật " + a + " dòng tại bảng tai_khoan");
                    this.xuLyYeuCau("TK01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TK04":
                try {
                    Statement s = con.createStatement();
                    String query = "update tai_khoan set "
                            + "trang_thai=N'" + Server.taiKhoan.getTrangThai()
                            + "' where id_tai_khoan like('" + Server.taiKhoan.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã cập nhật " + a + " dòng tại bảng tai_khoan");
                    this.xuLyYeuCau("TK01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TK05":
                try {
                    Statement s = con.createStatement();
                    String query = "Delete from tai_khoan where id_tai_khoan like('" + Server.taiKhoan.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã xóa " + a + " dòng ra khỏi bảng tai_khoan");
                    this.xuLyYeuCau("TK01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TB01":
                try {
                    Server.danhSachThietBi.clear();
                    Statement s = con.createStatement();
                    String query = "select * from thiet_bi;";
                    ResultSet rs = s.executeQuery(query);
                    Server.danhSachThietBi = this.layKetQuaThietBi(rs);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TB02":
                try {
                    Statement s = con.createStatement();
                    String query = "insert into thiet_bi(ten_thiet_bi,vi_tri,trang_thai) "
                            + "values(N'" + Server.thietBi.getTen() + "'"
                            + ",N'" + Server.thietBi.getViTri() + "',"
                            + "N'" + Server.thietBi.getTrangThai() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã thêm " + a + " dòng vào bảng thiet_bi");
                    this.xuLyYeuCau("TB01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TB03":
                try {
                    Statement s = con.createStatement();
                    String query = "update thiet_bi set ten_thiet_bi=N'" + Server.thietBi.getTen() + "',"
                            + " vi_tri=N'" + Server.thietBi.getViTri() + "',"
                            + "trang_thai=N'" + Server.thietBi.getTrangThai() + "'"
                            + " where id_thiet_bi like('" + Server.thietBi.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã cập nhật " + a + " dòng tại bảng thiet_bi");
                    this.xuLyYeuCau("TB01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TB04":
                try {
                    Statement s = con.createStatement();
                    String query = "update thiet_bi set trang_thai=N'" + Server.thietBi.getTrangThai() + "'"
                            + " where id_thiet_bi like('" + Server.thietBi.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã cập nhật " + a + " dòng tại bảng thiet_bi");
                    this.xuLyYeuCau("TB01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "TB05":
                try {
                    Statement s = con.createStatement();
                    String query = "Delete from thiet_bi where id_thiet_bi like('" + Server.thietBi.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã xóa " + a + " dòng ra khỏi bảng tai_khoan");
                    this.xuLyYeuCau("TB01");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "BC01":
                try {
                    Server.danhSachBaoCao.clear();
                    Statement s = con.createStatement();
                    String query = "select * from bao_cao";
                    ResultSet rs = s.executeQuery(query);
                    System.out.println(maYeuCau);
                    Server.danhSachBaoCao = this.layKetQuaBaoCao(rs);
                    in_Ket_Qua_Bao_Cao(Server.danhSachBaoCao);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "BC02":
                try {
                    Statement s = con.createStatement();
                    String query;
                    if (Server.baoCao.getIdKyThuatVienDuocChiDinh() == -1) {
                        query = "insert into bao_cao(id_nguoi_bao_cao,id_thiet_bi,loai_van_de,mo_ta,thoi_gian) "
                                + "values(" + Server.baoCao.getIdNguoiBaoCao() + ","
                                + "" + Server.baoCao.getIdThietBiGapSuCo() + ","
                                + "N'" + Server.baoCao.getLoaiSuCo() + "',"
                                + "N'" + Server.baoCao.getMoTaSuCo() + "',"
                                + "GETDATE());";
                    } else {
                        query = "insert into bao_cao(id_nguoi_bao_cao,id_thiet_bi,loai_van_de,mo_ta,thoi_gian,ky_thuat_vien_duoc_chi_dinh) "
                                + "values(" + Server.baoCao.getIdNguoiBaoCao() + ","
                                + Server.baoCao.getIdThietBiGapSuCo() + ","
                                + "N'" + Server.baoCao.getLoaiSuCo() + "',"
                                + "N'" + Server.baoCao.getMoTaSuCo() + "',"
                                + "GETDATE()," + Server.baoCao.getIdKyThuatVienDuocChiDinh() + ");";
                        System.out.println(query);
                    }
                    int a = -1;
                    
                    a = s.executeUpdate(query);
                    System.out.println("Đã thêm " + a + " dòng vào bảng bao_cao");
                    this.xuLyYeuCau("BC01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "BC03":
                try {
                    Statement s = con.createStatement();
                    String query = "update bao_cao set "
                            + "id_nguoi_bao_cao=" + Server.baoCao.getIdNguoiBaoCao() + ","
                            + "id_thiet_bi=" + Server.baoCao.getIdThietBiGapSuCo() + ","
                            + "loai_van_de=N'" + Server.baoCao.getLoaiSuCo() + "',"
                            + "mo_ta=N'" + Server.baoCao.getMoTaSuCo() + "',"
                            + "thoi_gian='" + Server.baoCao.getThoiGian() + "',"
                            + "ky_thuat_vien_duoc_chi_dinh=" + Server.baoCao.getIdKyThuatVienDuocChiDinh() + " "
                            + "where id_bao_cao like('" + Server.baoCao.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã cập nhật " + a + " dòng tại bảng bao_cao");
                    this.xuLyYeuCau("BC01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            
            case "BC04":
                try {
                    Statement s = con.createStatement();
                    String query = "Delete from bao_cao where id_bao_cao like('" + Server.baoCao.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã xóa " + a + " dòng ra khỏi bảng bao_cao");
                    this.xuLyYeuCau("BC01");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
            case "BC05":
                try {
                    Statement s = con.createStatement();
                    String query = "update bao_cao set "
                            + "ky_thuat_vien_duoc_chi_dinh=" + Server.baoCao.getIdKyThuatVienDuocChiDinh() + " "
                            + "where id_bao_cao like('" + Server.baoCao.getId() + "');";
                    int a = -1;
                    a = s.executeUpdate(query);
                    System.out.println("Đã cập nhật " + a + " dòng tại bảng bao_cao");
                    this.xuLyYeuCau("BC01");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                break;
        }
        this.dongKetNoiDatabase();
    }

    public String chuyenDoiTuongThanhChuoi(String doiTuong) {
        String ob = "";
        if (doiTuong.contains("TK")) {
            ob = Server.taiKhoan.getId()
                    + "tc0001" + Server.taiKhoan.getTenNguoiDung()
                    + "tc0001" + Server.taiKhoan.getMatKhau()
                    + "tc0001" + Server.taiKhoan.getLoaiTaiKhoan()
                    + "tc0001" + Server.taiKhoan.getTrangThai();
        } else if (doiTuong.contains("TB")) {
            ob = Server.thietBi.getId()
                    + "tc0002" + Server.thietBi.getTen()
                    + "tc0002" + Server.thietBi.getViTri()
                    + "tc0002" + Server.thietBi.getTrangThai();
        } else if (doiTuong.contains("BC")) {
            ob = Server.baoCao.getId()
                    + "tc0003" + Server.baoCao.getIdNguoiBaoCao()
                    + "tc0003" + Server.baoCao.getIdThietBiGapSuCo()
                    + "tc0003" + Server.baoCao.getLoaiSuCo()
                    + "tc0003" + Server.baoCao.getMoTaSuCo()
                    + "tc0003" + Server.baoCao.getThoiGian()
                    + "tc0003" + Server.baoCao.getIdKyThuatVienDuocChiDinh();
        }
        return ob;
    }

    public void chuyenChuoiThanhDoiTuongTaiKhoan(String s) {
        if (!s.equals("null")) {
            String idTaiKhoanString = s.substring(0, s.indexOf("tc0001"));
            s = s.substring(s.indexOf("tc0001") + 6, s.length()).trim();
            int idTaiKhoan = Integer.valueOf(idTaiKhoanString);

            String tenNguoiDung = s.substring(0, s.indexOf("tc0001"));
            s = s.substring(s.indexOf("tc0001") + 6, s.length()).trim();

            String matKhau = s.substring(0, s.indexOf("tc0001"));
            s = s.substring(s.indexOf("tc0001") + 6, s.length()).trim();

            String loaiTaiKhoan = s.substring(0, s.indexOf("tc0001"));
            s = s.substring(s.indexOf("tc0001") + 6, s.length()).trim();

            String trangThai = s;

            Server.taiKhoan = new TaiKhoan(idTaiKhoan, tenNguoiDung, matKhau, loaiTaiKhoan, trangThai);
        }
    }

    public void chuyenChuoiThanhDoiTuongThietBi(String s) {
        if (!s.equals("null")) {
            String idThietBiString = s.substring(0, s.indexOf("tc0002"));
            s = s.substring(s.indexOf("tc0002") + 6, s.length()).trim();
            int idThietBi = Integer.valueOf(idThietBiString);

            String tenThietBi = s.substring(0, s.indexOf("tc0002"));
            s = s.substring(s.indexOf("tc0002") + 6, s.length()).trim();

            String viTri = s.substring(0, s.indexOf("tc0002"));
            s = s.substring(s.indexOf("tc0002") + 6, s.length()).trim();

            String trangThai = s;

            Server.thietBi = new ThietBi(idThietBi, tenThietBi, viTri, trangThai);
        }
    }

    public void chuyenChuoiThanhDoiTuongBaoCao(String s) {
        if (!s.equals("null")) {
            String idBaoCaoString = s.substring(0, s.indexOf("tc0003"));
            s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();
            int idBaoCao = Integer.valueOf(idBaoCaoString);

            String idNguoiBaoCaoString = s.substring(0, s.indexOf("tc0003"));
            s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();
            int idNguoiBaoCao = Integer.valueOf(idNguoiBaoCaoString);

            String idThietBiString = s.substring(0, s.indexOf("tc0003"));
            s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();
            int idThietBi = Integer.valueOf(idThietBiString);
            System.out.println(idThietBi);

            String loaiVanDe = s.substring(0, s.indexOf("tc0003"));
            s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();

            String moTa = s.substring(0, s.indexOf("tc0003"));
            s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();

            String thoiGian = s.substring(0, s.indexOf("tc0003"));
            s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();

            String kyThuatVienDuocChiDinhString = s;
            int kyThuatVienDuocChiDinh = Integer.valueOf(kyThuatVienDuocChiDinhString);

            Server.baoCao = new BaoCao(idBaoCao, idNguoiBaoCao, idThietBi, loaiVanDe, moTa, thoiGian, kyThuatVienDuocChiDinh);
        }
    }
}
