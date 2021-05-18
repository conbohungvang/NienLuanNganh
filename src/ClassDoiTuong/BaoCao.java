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
public class BaoCao {

    private int id;
    private int idNguoiBaoCao;
    private int idThietBiGapSuCo;
    private String loaiSuCo;
    private String moTaSuCo;
    private String thoiGian;
    private int idKyThuatVienDuocChiDinh = -1;
    
    public BaoCao() {
        
    }
    
    public BaoCao(int id) {
        this.id = id;
    }
    
    public BaoCao(BaoCao baoCao) {
        this.id = baoCao.id;
        this.idNguoiBaoCao = baoCao.idNguoiBaoCao;
        this.idThietBiGapSuCo = baoCao.idThietBiGapSuCo;
        this.loaiSuCo = baoCao.loaiSuCo;
        this.moTaSuCo = baoCao.moTaSuCo;
        this.thoiGian = baoCao.thoiGian;
        this.idKyThuatVienDuocChiDinh = baoCao.idKyThuatVienDuocChiDinh;
    }

    public BaoCao(int id, int idKyThuatVienDuocChiDinh) {
        this.id = id;
        this.idKyThuatVienDuocChiDinh = idKyThuatVienDuocChiDinh;
    }

    public BaoCao(int idNguoiBaoCao, int idThietBiGapSuCo, String loaiSuCo, String moTaSuCo) {
        this.idNguoiBaoCao = idNguoiBaoCao;
        this.idThietBiGapSuCo = idThietBiGapSuCo;
        this.loaiSuCo = loaiSuCo;
        this.moTaSuCo = moTaSuCo;
    }
    
    public BaoCao(int idNguoiBaoCao, int idThietBiGapSuCo, String loaiSuCo, String moTaSuCo, String thoiGian) {
        this.idNguoiBaoCao = idNguoiBaoCao;
        this.idThietBiGapSuCo = idThietBiGapSuCo;
        this.loaiSuCo = loaiSuCo;
        this.moTaSuCo = moTaSuCo;
        this.thoiGian = thoiGian;
    }

    public BaoCao(int id, int idNguoiBaoCao, int idThietBiGapSuCo, String loaiSuCo, String moTaSuCo, String thoiGian, int idKyThuatVienDuocChiDinh) {
        this.id = id;
        this.idNguoiBaoCao = idNguoiBaoCao;
        this.idThietBiGapSuCo = idThietBiGapSuCo;
        this.loaiSuCo = loaiSuCo;
        this.moTaSuCo = moTaSuCo;
        this.thoiGian = thoiGian;
        this.idKyThuatVienDuocChiDinh = idKyThuatVienDuocChiDinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNguoiBaoCao() {
        return idNguoiBaoCao;
    }

    public void setIdNguoiBaoCao(int idNguoiBaoCao) {
        this.idNguoiBaoCao = idNguoiBaoCao;
    }

    public int getIdThietBiGapSuCo() {
        return idThietBiGapSuCo;
    }

    public void setIdThietBiGapSuCo(int idThietBiGapSuCo) {
        this.idThietBiGapSuCo = idThietBiGapSuCo;
    }

    public String getLoaiSuCo() {
        return loaiSuCo;
    }

    public void setLoaiSuCo(String loaiSuCo) {
        this.loaiSuCo = loaiSuCo;
    }

    public String getMoTaSuCo() {
        return moTaSuCo;
    }

    public void setMoTaSuCo(String moTaSuCo) {
        this.moTaSuCo = moTaSuCo;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public int getIdKyThuatVienDuocChiDinh() {
        return idKyThuatVienDuocChiDinh;
    }

    public void setIdKyThuatVienDuocChiDinh(int idKyThuatVienDuocChiDinh) {
        this.idKyThuatVienDuocChiDinh = idKyThuatVienDuocChiDinh;
    }
}
