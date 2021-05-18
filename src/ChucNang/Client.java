/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChucNang;

import ClassDoiTuong.BaoCao;
import ClassDoiTuong.TaiKhoan;
import ClassDoiTuong.ThietBi;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Client {

    Socket socket;
    static DataOutputStream dos;
    static DataInputStream dis;
    public static TaiKhoan taiKhoan;
    public static ThietBi thietBi;
    public static BaoCao baoCao;
    public static ChucNangChuyenDoi chucNangChuyenDoi = new ChucNangChuyenDoi();
    public static ArrayList<TaiKhoan> danhSachTaiKhoan = new ArrayList<>();
    public ArrayList<TaiKhoan> danhSachTaiKhoan1 = new ArrayList<>();
    public static ArrayList<ThietBi> danhSachThietBi = new ArrayList<>();
    public static ArrayList<BaoCao> danhSachBaoCao = new ArrayList<>();
    public static int duLieuTaiKhoan = 0;
    public static int duLieuThietBi = 0;
    public static int duLieuBaoCao = 0;

    public Client() {
        try {
            socket = new Socket("localhost", 12345);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            if (socket.isConnected()) {
                System.out.println("Đã kết nối đến " + socket);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        guiYeuCauHienThi("TK01");
        guiYeuCauHienThi("TB01");
        guiYeuCauHienThi("BC01");
        DocTinNhan dtn = new DocTinNhan();
        dtn.start();

//        test1();
    }

    public int getduLieuTaiKhoan() {
        return duLieuTaiKhoan;
    }

    public static void guiYeuCau(String maYeuCau) {
        try {
            String kieuDoiTuong = maYeuCau.substring(0, 2);
            String yeuCau = maYeuCau + "5fgHT" + chucNangChuyenDoi.chuyenDoiTuongThanhChuoi(kieuDoiTuong);
            dos.writeUTF(yeuCau);
            dos.flush();
            if(maYeuCau.contains("TK"))guiYeuCauHienThi("TK01");
            if(maYeuCau.contains("TB"))guiYeuCauHienThi("TB01");
            if(maYeuCau.contains("BC"))guiYeuCauHienThi("BC01");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void guiYeuCauHienThi(String maYeuCau) {
        try {
            if (maYeuCau.contains("TK")) {
                String sms = maYeuCau + "5fgHT";
                dos.writeUTF(sms + null);
            } else if (maYeuCau.contains("TB")) {
                String sms = maYeuCau + "5fgHT";
                dos.writeUTF(sms + null);
            } else if (maYeuCau.contains("BC")) {
                String sms = maYeuCau + "5fgHT";
                dos.writeUTF(sms + null);
            }
            dos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void dongKetNoi() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void test1() {
        for (TaiKhoan tk : danhSachTaiKhoan) {
            System.out.println(tk.getId() + " " + tk.getTenNguoiDung() + " " + tk.getLoaiTaiKhoan() + " " + tk.getTrangThai());
        }
//        for(TaiKhoan tk : danhSachTaiKhoan){
//            System.out.println(tk.getId()+" "+tk.getTenNguoiDung()+" "+tk.getLoaiTaiKhoan()+" "+tk.getTrangThai());
//        }
    }

    class DocTinNhan extends Thread {

        @Override
        public void run() {
            if (socket != null) {
                while (true) {
                    try {
                        String sms = dis.readUTF();
                        System.out.print("Chuoi sms: " + sms + "\n");
                        if (!sms.isEmpty()) {
                            String maYeuCau = sms.substring(0, sms.indexOf("5fgHT"));
                            sms = sms.substring(sms.indexOf("5fgHT") + 5, sms.length());
                            if (maYeuCau.contains("TK")) {
                                if (sms.equals("The START")) {
                                    Client.danhSachTaiKhoan.clear();
                                } else if (sms.equals("The END")) {
                                    duLieuTaiKhoan = 1;
                                } else {
                                    chucNangChuyenDoi.chuyenChuoiThanhDoiTuongTaiKhoan(sms);
                                    Client.danhSachTaiKhoan.add(taiKhoan);
                                }
                            }
                            if (maYeuCau.contains("TB")) {
                                if (sms.equals("The START")) {
                                    Client.danhSachThietBi.clear();
                                } else if (sms.equals("The END")) {
                                    duLieuThietBi = 1;
                                } else {
                                    chucNangChuyenDoi.chuyenChuoiThanhDoiTuongThietBi(sms);
                                    Client.danhSachThietBi.add(thietBi);
                                }
                            }
                            if (maYeuCau.contains("BC")) {
                                if (sms.equals("The START")) {
                                    Client.danhSachBaoCao.clear();
                                } else if (sms.equals("The END")) {
                                    duLieuBaoCao = 1;
                                } else {
                                    chucNangChuyenDoi.chuyenChuoiThanhDoiTuongBaoCao(sms);
                                    danhSachBaoCao.add(baoCao);
                                }
                            }

                        } else if (sms.equals("0")) {

                            System.exit(0);
                        }
                    } catch (SocketException e) {
                        System.out.println("Mat ket noi den server");
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("huynh minh nhat");
            test1();
        }
    }
}
