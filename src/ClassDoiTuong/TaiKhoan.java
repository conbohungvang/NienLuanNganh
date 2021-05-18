/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassDoiTuong;

/**
 *
 * @author Dinh Nguyen Hai Dang - B1704721
 */
public class TaiKhoan {

    private int id;
    private String tenNguoiDung;
    private String matKhau;
    private String loaiTaiKhoan;
    private String trangThai;
    
    public TaiKhoan() {
        
    }
    
    public TaiKhoan(int id) {
        this.id = id;
    }
    
    public TaiKhoan(TaiKhoan taiKhoan) {
        this.id = taiKhoan.id;
        this.tenNguoiDung = taiKhoan.tenNguoiDung;
        this.matKhau = taiKhoan.matKhau;
        this.loaiTaiKhoan = taiKhoan.loaiTaiKhoan;
        this.trangThai = taiKhoan.trangThai;
    }
    
    public TaiKhoan(String tenNguoiDung, String matKhau) {
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
    }
    
    public TaiKhoan(String tenNguoiDung, String matKhau, String loaiTaiKhoan) {
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public TaiKhoan(int id, String tenNguoiDung, String loaiTaiKhoan, String trangThai) {
        this.id = id;
        this.tenNguoiDung = tenNguoiDung;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.trangThai = trangThai;
    }

    public TaiKhoan(int id, String tenNguoiDung, String matKhau, String loaiTaiKhoan, String trangThai) {
        this.id = id;
        this.tenNguoiDung = tenNguoiDung;
        this.matKhau = matKhau;
        this.loaiTaiKhoan = loaiTaiKhoan;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
