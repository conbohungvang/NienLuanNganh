/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChucNang;

import ClassDoiTuong.BaoCao;
import ClassDoiTuong.TaiKhoan;
import ClassDoiTuong.ThietBi;

/**
 *
 * @author USER
 */
public class ChucNangChuyenDoi {

    public String chuyenDoiTuongThanhChuoi(String input) {
        String chuoiTraVe = "";
        if (input.contains("TK")) {
            chuoiTraVe = Client.taiKhoan.getId()
                    + "tc0001" + Client.taiKhoan.getTenNguoiDung()
                    + "tc0001" + Client.taiKhoan.getMatKhau()
                    + "tc0001" + Client.taiKhoan.getLoaiTaiKhoan()
                    + "tc0001" + Client.taiKhoan.getTrangThai();
        } else if (input.contains("TB")) {
            chuoiTraVe = Client.thietBi.getId()
                    + "tc0002" + Client.thietBi.getTen()
                    + "tc0002" + Client.thietBi.getViTri()
                    + "tc0002" + Client.thietBi.getTrangThai();
        } else if (input.contains("BC")) {
            chuoiTraVe = Client.baoCao.getId()
                    + "tc0003" + Client.baoCao.getIdNguoiBaoCao()
                    + "tc0003" + Client.baoCao.getIdThietBiGapSuCo()
                    + "tc0003" + Client.baoCao.getLoaiSuCo()
                    + "tc0003" + Client.baoCao.getMoTaSuCo()
                    + "tc0003" + Client.baoCao.getThoiGian()
                    + "tc0003" + Client.baoCao.getIdKyThuatVienDuocChiDinh();
        }
        return chuoiTraVe;
    }

    public void chuyenChuoiThanhDoiTuongTaiKhoan(String s) {
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

        Client.taiKhoan = new TaiKhoan(idTaiKhoan, tenNguoiDung, matKhau, loaiTaiKhoan, trangThai);
//        Client.danhSachTaiKhoan.add(Client.taiKhoan);
    }
//    public void chuyenChuoiThanhDoiTuongTaiKhoanCL(String s) {
//        String idTaiKhoanString = s.substring(0, s.indexOf("tc0001"));
//        s = s.substring(s.indexOf("tc0001") + 6, s.length()).trim();
//        int idTaiKhoan = Integer.valueOf(idTaiKhoanString);
//        
//        String tenNguoiDung = s.substring(0, s.indexOf("tc0001"));
//        s = s.substring(s.indexOf("tc0001") + 6, s.length()).trim();
//
//        String matKhau = s.substring(0, s.indexOf("tc0001"));
//        s = s.substring(s.indexOf("tc0001") + 6, s.length()).trim();
//
//        String loaiTaiKhoan = s.substring(0, s.indexOf("tc0001"));
//        s = s.substring(s.indexOf("tc0001") + 6, s.length()).trim();
//
//        String trangThai = s;
//
//        Client.taiKhoan = new TaiKhoan(idTaiKhoan, tenNguoiDung, matKhau, loaiTaiKhoan, trangThai);
//        Client.danhSachTaiKhoan.add(Client.taiKhoan);
//    }

    public void chuyenChuoiThanhDoiTuongThietBi(String s) {
        String idThietBiString = s.substring(0, s.indexOf("tc0002"));
        s = s.substring(s.indexOf("tc0002") + 6, s.length()).trim();
        int idThietBi = Integer.valueOf(idThietBiString);
        
        String tenThietBi = s.substring(0, s.indexOf("tc0002"));
        s = s.substring(s.indexOf("tc0002") + 6, s.length()).trim();

        String viTri = s.substring(0, s.indexOf("tc0002"));
        s = s.substring(s.indexOf("tc0002") + 6, s.length()).trim();

        String trangThai = s;

        Client.thietBi = new ThietBi(idThietBi, tenThietBi, viTri, trangThai);
    }

    public void chuyenChuoiThanhDoiTuongBaoCao(String s) {
        String idBaoCaoString = s.substring(0, s.indexOf("tc0003"));
        s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();
        int idBaoCao = Integer.valueOf(idBaoCaoString);

        String idNguoiBaoCaoString = s.substring(0, s.indexOf("tc0003"));
        s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();
        int idNguoiBaoCao = Integer.valueOf(idNguoiBaoCaoString);

        String idThietBiString = s.substring(0, s.indexOf("tc0003"));
        s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();
        int idThietBi = Integer.valueOf(idThietBiString);
        
        String loaiVanDe = s.substring(0, s.indexOf("tc0003"));
        s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();

        String moTa = s.substring(0, s.indexOf("tc0003"));
        s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();

        String thoiGian = s.substring(0, s.indexOf("tc0003"));
        s = s.substring(s.indexOf("tc0003") + 6, s.length()).trim();

        String kyThuatVienDuocChiDinhString = s;
        int kyThuatVienDuocChiDinh = Integer.valueOf(kyThuatVienDuocChiDinhString);

        Client.baoCao = new BaoCao(idBaoCao, idNguoiBaoCao, idThietBi, loaiVanDe, moTa, thoiGian, kyThuatVienDuocChiDinh);
    }
}
