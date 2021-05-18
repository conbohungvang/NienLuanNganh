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
public class ThietBi {

    private int id;
    private String ten;
    private String viTri;
    private String trangThai = "Bình thường";

    public ThietBi() {

    }

    public ThietBi(int id) {
        this.id = id;
    }

    public ThietBi(ThietBi thietBi) {
        this.id = thietBi.id;
        this.ten = thietBi.ten;
        this.viTri = thietBi.viTri;
        this.trangThai = thietBi.trangThai;
    }
    
    public ThietBi(String ten, String viTri) {
        this.ten = ten;
        this.viTri = viTri;
    }

    public ThietBi(String ten, String viTri, String trangThai) {
        this.ten = ten;
        this.viTri = viTri;
        this.trangThai = trangThai;
    }

    public ThietBi(int id, String ten, String viTri, String trangThai) {
        this.id = id;
        this.ten = ten;
        this.viTri = viTri;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
