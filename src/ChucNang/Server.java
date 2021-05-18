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
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Server {

    static ServerSocket serverSocket;
    static Socket socket;
    static ArrayList<TaiKhoan> danhSachTaiKhoan = new ArrayList<>();
    static ArrayList<ThietBi> danhSachThietBi = new ArrayList<>();
    static ArrayList<BaoCao> danhSachBaoCao = new ArrayList<>();
    static TaiKhoan taiKhoan;
    static ThietBi thietBi;
    static BaoCao baoCao;
    static public ChucNangBenServer chucNangBenServer;
    static DataOutputStream dos;
    static DataInputStream dis;

    public Server() {
        try {
            chucNangBenServer = new ChucNangBenServer();
            serverSocket = new ServerSocket(12345);
            System.out.println("Server bắt đầu hoạt động");
            new ChapNhapClient().start();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    class ChapNhapClient extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    socket = serverSocket.accept();
                    Server.dos = new DataOutputStream(socket.getOutputStream());
                    Server.dis = new DataInputStream(socket.getInputStream());
                    if (socket.isConnected()) {
                        System.out.println("Nhận kết nối từ " + socket);
                    }
                    new DocTinNhan().start();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    class DocTinNhan extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    String sms = new DataInputStream(socket.getInputStream()).readUTF();

                    if (!sms.isEmpty()) {
                        String maYeuCau = sms.substring(0, sms.indexOf("5fgHT"));
                        sms = sms.substring(sms.indexOf("5fgHT") + 5, sms.length()).trim();
                        System.out.println(maYeuCau + " " + sms);
                        if (maYeuCau.contains("TK")) {
                            Server.chucNangBenServer.chuyenChuoiThanhDoiTuongTaiKhoan(sms);
                            Server.chucNangBenServer.xuLyYeuCau(maYeuCau);
                            guiThongTin(maYeuCau);
                        }
                        if (maYeuCau.contains("TB")) {
                            Server.chucNangBenServer.chuyenChuoiThanhDoiTuongThietBi(sms);
                            Server.chucNangBenServer.xuLyYeuCau(maYeuCau);
                            guiThongTin(maYeuCau);
                        }
                        if (maYeuCau.contains("BC")) {
                            Server.chucNangBenServer.chuyenChuoiThanhDoiTuongBaoCao(sms);
                            
                            Server.chucNangBenServer.xuLyYeuCau(maYeuCau);
                            System.out.println("chay xlyc");
                            Server.chucNangBenServer.in_Ket_Qua_Bao_Cao(danhSachBaoCao);
                            
                            guiThongTin(maYeuCau);
                        }
                    } else if (sms.equals("0")) {
                        System.exit(0);
                    }
                } catch (SocketException ex) {
                    
                    System.out.print("da ngat ket noi tu socket" + socket);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void guiThongTin(String maYeuCau) {
            try {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                if (maYeuCau.contains("TK")) {
                    dos.writeUTF(maYeuCau + "5fgHTThe START");
                    dos.flush();
                    for (TaiKhoan tk : Server.danhSachTaiKhoan) {
                        Server.taiKhoan = tk;
                        dos.writeUTF(maYeuCau + "5fgHT" + Server.chucNangBenServer.chuyenDoiTuongThanhChuoi(maYeuCau));
                        dos.flush();
                    }
                    dos.writeUTF(maYeuCau + "5fgHTThe END");
                    dos.flush();
                } else if (maYeuCau.contains("TB")) {
                    dos.writeUTF(maYeuCau + "5fgHTThe START");
                    dos.flush();
                    for (ThietBi tb : Server.danhSachThietBi) {
                        Server.thietBi = tb;
                        dos.writeUTF(maYeuCau + "5fgHT" + Server.chucNangBenServer.chuyenDoiTuongThanhChuoi(maYeuCau));
                        dos.flush();
                    }
                    dos.writeUTF(maYeuCau + "5fgHTThe END");
                    dos.flush();
                } else if (maYeuCau.contains("BC")) {
                    dos.writeUTF(maYeuCau + "5fgHTThe START");
                    dos.flush();
                    for (BaoCao bc : Server.danhSachBaoCao) {
                        Server.baoCao = bc;
                        dos.writeUTF(maYeuCau + "5fgHT" + Server.chucNangBenServer.chuyenDoiTuongThanhChuoi(maYeuCau));
                        dos.flush();
                    }
                    dos.writeUTF(maYeuCau + "5fgHTThe END");
                    dos.flush();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
    }
}
