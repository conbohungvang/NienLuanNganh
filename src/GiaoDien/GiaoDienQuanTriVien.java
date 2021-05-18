/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GiaoDien;

import ClassDoiTuong.BaoCao;
import ClassDoiTuong.TaiKhoan;
import ClassDoiTuong.ThietBi;
import ChucNang.Client;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class GiaoDienQuanTriVien extends javax.swing.JFrame {

    /**
     * Creates new form
     */
    DefaultTableModel modelBangThietBi, modelBangTaiKhoan, modelBangBaoCao;
    public Client clienT = new Client();

    public GiaoDienQuanTriVien() {
        initComponents();
        System.out.print("abc----xyz");
        HienThi luong = new HienThi();
        luong.start();
//        napThongTinTaiKhoan();
//        napThongTinThietBi();
//        napThongTinBaoCao();
    }

    class HienThi extends Thread {

        public HienThi() {
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (Client.duLieuTaiKhoan == 1) {
                        napThongTinTaiKhoan();
                        Client.duLieuTaiKhoan = 0;
                    }
                    this.sleep(200);

                    if (Client.duLieuThietBi == 1) {
                        napThongTinThietBi();
                        Client.duLieuThietBi = 0;
                    }
                    this.sleep(200);

                    if (Client.duLieuBaoCao == 1) {
                        napThongTinBaoCao();
                        Client.duLieuBaoCao = 0;
                    }
                    this.sleep(200);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GiaoDienQuanTriVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        public void clear(DefaultTableModel model) {
            int rowCount = model.getRowCount();
            while (rowCount > 0) {
                model.removeRow(0);
                rowCount--;
            }
        }

        public void napThongTinTaiKhoan() {

            /* Khoi tao model bang tai khoan */
            modelBangTaiKhoan = (DefaultTableModel) tbAccount.getModel();
            modelBangTaiKhoan.setColumnIdentifiers(new Object[]{
                "ID", "Tên Người Dùng", "Loại Tài Khoản", "Trạng Thái"
            });
            /* Xoa du lieu cu */
            clear(modelBangTaiKhoan);
            tbAccount.removeAll();
            cbKtv.removeAllItems();
            /* Nap du lieu */
            for (TaiKhoan taiKhoan : Client.danhSachTaiKhoan) {
                modelBangTaiKhoan.addRow(new Object[]{
                    taiKhoan.getId(), taiKhoan.getTenNguoiDung(), taiKhoan.getLoaiTaiKhoan(), taiKhoan.getTrangThai()
                });
                if (taiKhoan.getLoaiTaiKhoan().equals("KTV")){
                    cbKtv.addItem(taiKhoan.getTenNguoiDung());
                }
            }
        }

        public void napThongTinThietBi() {
            /* Khoi tao model bang thiet bi */
            modelBangThietBi = (DefaultTableModel) tbqt.getModel();
            modelBangThietBi.setColumnIdentifiers(new Object[]{
                "ID", "Tên thiết bị", "Vị Trí", "Tình Trạng"
            });

            /* Xoa du lieu cu */
            clear(modelBangThietBi);

            /* Nap du lieu */
            for (ThietBi thietBi : Client.danhSachThietBi) {
                modelBangThietBi.addRow(new Object[]{
                    thietBi.getId(), thietBi.getTen(), thietBi.getViTri(), thietBi.getTrangThai()
                });
            }
        }

        public void napThongTinBaoCao() {
            /* Khoi tao model bang bao cao */
            modelBangBaoCao = (DefaultTableModel) tbql.getModel();
            modelBangBaoCao.setColumnIdentifiers(new Object[]{
                "ID", "Người Báo Cáo", "Tên Thiết Bị", "Loại Sự Cố", "Mô tả sự cố", "Ngày Báo Cáo", "Kỹ thuật viên đảm nhiệm"
            });

            /* Xoa du lieu cu */
            clear(modelBangBaoCao);

            for (BaoCao baoCao : Client.danhSachBaoCao) {
                /* Lay nguoi bao cao */
                TaiKhoan nguoiBaoCao = new TaiKhoan();
                for (TaiKhoan taiKhoan : Client.danhSachTaiKhoan) {
                    if (baoCao.getIdNguoiBaoCao() == taiKhoan.getId()) {
                        nguoiBaoCao = taiKhoan;
                        break;
                    }
                }

                /* Lay thiet bi gap su co */
                System.out.println(baoCao.getIdThietBiGapSuCo() + "-(-)-");
                ThietBi thietBiGapSuCo = new ThietBi();
                for (ThietBi thietBi : Client.danhSachThietBi) {
                    System.out.println(thietBi.getId() + " " + thietBi.getTen());
                }
                for (ThietBi thietBi : Client.danhSachThietBi) {
                    if (thietBi.getId() == baoCao.getIdThietBiGapSuCo()) {
                        System.out.println(baoCao.getIdThietBiGapSuCo() + "-(-)-" + thietBi.getId());
                        thietBiGapSuCo.setTen(thietBi.getTen());
                        break;
                    }
                }

                /* Lay ky thuat vien duoc chi dinh */
                TaiKhoan kyThuatVienDuocChiDinh = new TaiKhoan();
                for (TaiKhoan taiKhoan : Client.danhSachTaiKhoan) { // danhSachTaiKhoan Lấy trực tiếp từ Client
                    if (taiKhoan.getId() == baoCao.getIdKyThuatVienDuocChiDinh()) {
                        kyThuatVienDuocChiDinh = taiKhoan;
                        break;
                    }
                }

                /* Nap du lieu */
                modelBangBaoCao.addRow(new Object[]{
                    baoCao.getId(), nguoiBaoCao.getTenNguoiDung(), thietBiGapSuCo.getTen(), baoCao.getLoaiSuCo(), baoCao.getMoTaSuCo(), baoCao.getThoiGian(), kyThuatVienDuocChiDinh.getTenNguoiDung()
                });
            }
        }
    }

//    public final void napThongTinTaiKhoan() {
//
//        /* Khoi tao model bang tai khoan */
//        Client cl = new Client();
//        cl.guiYeuCauHienThi("TK01");
//
//        modelBangTaiKhoan = (DefaultTableModel) tbAccount.getModel();
//        modelBangTaiKhoan.setColumnIdentifiers(new Object[]{
//            "ID", "Tên Người Dùng", "Loại Tài Khoản", "Trạng Thái"
//        });
//
//        /* Xoa du lieu cu */
//        clear(modelBangTaiKhoan);
//
//        /* Nap du lieu */
//        for (TaiKhoan taiKhoan : Client.danhSachTaiKhoan) {
//            modelBangTaiKhoan.addRow(new Object[]{
//                taiKhoan.getId(), taiKhoan.getTenNguoiDung(), taiKhoan.getLoaiTaiKhoan(), taiKhoan.getTrangThai()
//            });
//            if (taiKhoan.getLoaiTaiKhoan().equals("KTV")) {
//                cbKtv.addItem(taiKhoan.getTenNguoiDung());
//            }
//        }
//    }
//
//    public final void napThongTinThietBi() {
//        /* Khoi tao model bang thiet bi */
////        cl=new Client();
////        cl.guiYeuCauHienThi("TB01");
//        modelBangThietBi = (DefaultTableModel) tbqt.getModel();
//        modelBangThietBi.setColumnIdentifiers(new Object[]{
//            "ID", "Tên thiết bị", "Vị Trí", "Tình Trạng"
//        });
//
//        /* Xoa du lieu cu */
//        clear(modelBangThietBi);
//
//        /* Nap du lieu */
//        for (ThietBi thietBi : Client.danhSachThietBi) {
//            modelBangThietBi.addRow(new Object[]{
//                thietBi.getId(), thietBi.getTen(), thietBi.getViTri(), thietBi.getTrangThai()
//            });
//        }
//    }
//
//    public final void napThongTinBaoCao() {
//        /* Khoi tao model bang bao cao */
//
////        cl=new Client();
////        cl.guiYeuCauHienThi("BC01");
//        modelBangBaoCao = (DefaultTableModel) tbql.getModel();
//        modelBangBaoCao.setColumnIdentifiers(new Object[]{
//            "ID", "Người Báo Cáo", "Thiết Bị Gặp Sự Cố", "Loại Sự Cố", "Mô tả sự cố", "Ngày Báo Cáo", "Kỹ thuật viên đảm nhiệm"
//        });
//
//        /* Xoa du lieu cu */
//        clear(modelBangBaoCao);
//
//        for (BaoCao baoCao : Client.danhSachBaoCao) {
//            /* Lay nguoi bao cao */
//            TaiKhoan nguoiBaoCao = new TaiKhoan();
//            for (TaiKhoan taiKhoan : Client.danhSachTaiKhoan) {
//                if (baoCao.getIdNguoiBaoCao() == taiKhoan.getId()) {
//                    nguoiBaoCao = taiKhoan;
//                    break;
//                }
//            }
//
//            /* Lay thiet bi gap su co */
//            ThietBi thietBiGapSuCo = new ThietBi();
//            for (ThietBi thietBi : Client.danhSachThietBi) {
//                if (thietBi.getId() == baoCao.getIdThietBiGapSuCo()) {
//                    thietBiGapSuCo = thietBi;
//                    break;
//                }
//            }
//
//            /* Lay ky thuat vien duoc chi dinh */
//            TaiKhoan kyThuatVienDuocChiDinh = new TaiKhoan();
//            for (TaiKhoan taiKhoan : Client.danhSachTaiKhoan) { // danhSachTaiKhoan Lấy trực tiếp từ Client
//                if (taiKhoan.getLoaiTaiKhoan().equals("KTV")) {
//                    cbKtv.addItem(taiKhoan.getTenNguoiDung());
//                }
//                if (taiKhoan.getId() == baoCao.getIdKyThuatVienDuocChiDinh()) {
//                    kyThuatVienDuocChiDinh = taiKhoan;
//                    break;
//                }
//            }
//
//            /* Nap du lieu */
//            modelBangBaoCao.addRow(new Object[]{
//                baoCao.getId(), nguoiBaoCao.getTenNguoiDung(), thietBiGapSuCo.getTen(), baoCao.getLoaiSuCo(), baoCao.getMoTaSuCo(), baoCao.getThoiGian(), kyThuatVienDuocChiDinh.getTenNguoiDung()
//            });
//        }
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbql = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        mota = new javax.swing.JTextArea();
        btnAssign = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cbKtv = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbAccount = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btCreateAccount = new javax.swing.JButton();
        txtRePass = new javax.swing.JTextField();
        txtPass = new javax.swing.JTextField();
        txtUser = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btDeleteAccount = new javax.swing.JButton();
        btUpdataAccount = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        rdAdmin = new javax.swing.JRadioButton();
        rdStaff = new javax.swing.JRadioButton();
        rdKtv = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbqt = new javax.swing.JTable();
        btnThemThietBi = new javax.swing.JButton();
        btnUpdataTB = new javax.swing.JButton();
        btnDeleteTB = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tentb = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        vitritb = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbql.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbql.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "null", "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbql.setEditingColumn(25);
        tbql.setRowHeight(25);
        tbql.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbqlMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbql);

        mota.setColumns(20);
        mota.setRows(5);
        jScrollPane1.setViewportView(mota);

        btnAssign.setText("Phân Công");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa Lỗi");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cbKtv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Danh sách Kỹ Thuật Viên" }));
        cbKtv.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbKtvItemStateChanged(evt);
            }
        });
        cbKtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKtvActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Bản mô tả lỗi");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addGap(161, 161, 161))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAssign)
                .addGap(100, 100, 100)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(356, 356, 356)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbKtv, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbKtv, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Quản Lý Phân Công Lỗi", jPanel4);

        tbAccount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "null", "null", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tbAccount);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Mật khẩu:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Tên đăng nhập:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Nhập lại mật khẩu:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Loại tài khoản:");

        btCreateAccount.setText("Tạo tài khoản");
        btCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateAccountActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Thêm Tài Khoản");

        btDeleteAccount.setText("Xóa");
        btDeleteAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteAccountActionPerformed(evt);
            }
        });

        btUpdataAccount.setText("Cập Nhật");
        btUpdataAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdataAccountActionPerformed(evt);
            }
        });

        rdAdmin.setText("Quản Trị");
        rdAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdAdminActionPerformed(evt);
            }
        });

        rdStaff.setSelected(true);
        rdStaff.setText("Nhân Viên");
        rdStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdStaffActionPerformed(evt);
            }
        });

        rdKtv.setText("Kỹ Thuật");
        rdKtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdKtvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdStaff)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rdKtv)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdAdmin)
                .addGap(32, 32, 32))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdAdmin)
                    .addComponent(rdStaff)
                    .addComponent(rdKtv))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btUpdataAccount)
                .addGap(113, 113, 113)
                .addComponent(btDeleteAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 282, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtPass, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                                        .addComponent(txtRePass))
                                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btCreateAccount))
                                .addGap(0, 21, Short.MAX_VALUE))))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRePass, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addComponent(btCreateAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btUpdataAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btDeleteAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 929, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Tài Khoản", jPanel1);

        tbqt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbqt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbqt.setEditingColumn(25);
        tbqt.setRowHeight(25);
        jScrollPane5.setViewportView(tbqt);

        btnThemThietBi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnThemThietBi.setText("Thêm Mới");
        btnThemThietBi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThietBiActionPerformed(evt);
            }
        });

        btnUpdataTB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdataTB.setText("Sửa Thông Tin");
        btnUpdataTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdataTBActionPerformed(evt);
            }
        });

        btnDeleteTB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDeleteTB.setText("Xóa Thiết Bị");
        btnDeleteTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTBActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Tên Thiết Bị");

        tentb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tentb.setText("Nhập tên thiết bị mới");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Vị Trí");

        vitritb.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        vitritb.setText("Nhập vị trí mới");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Thêm Thiết Bị");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(btnUpdataTB)
                .addGap(154, 154, 154)
                .addComponent(btnDeleteTB)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 29, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vitritb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tentb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(btnThemThietBi)
                                .addGap(113, 113, 113))))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tentb, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vitritb, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(btnThemThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdataTB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteTB, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản Lý Thiết Bị", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Xu ly button them thiet bi
     *
     * @param evt
     */
    private void btnThemThietBiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThietBiActionPerformed
        /* Lay gia tri */
        String ten = tentb.getText();
        String viTri = vitritb.getText();

        try {
            tentb.setText("");
            vitritb.setText("");
            tbqt.removeAll();
            /* Gui yeu cau them thiet bi den Server */
            Client.thietBi = new ThietBi(ten, viTri);
            Client.guiYeuCau("TB02");
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Tạo không thành công.");
        }

    }//GEN-LAST:event_btnThemThietBiActionPerformed

    /**
     * Xu ly button phan cong ky thuat vien
     *
     * @param evt
     */
    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        //Lấy ra dòng được chọn của bản để gửi cập nhập    
        try {
        int idBaoCao = (int) tbql.getValueAt(tbql.getSelectedRow(), 0);
        String ktv = cbKtv.getSelectedItem().toString();
        int idKTV=0;
        for(TaiKhoan tk: Client.danhSachTaiKhoan){
            if(ktv.equals(tk.getTenNguoiDung()))idKTV=tk.getId();
        }
        tbql.removeAll();
        /* Gui yeu cau phan cong ky thuat vien */
        Client.baoCao = new BaoCao(idBaoCao, idKTV);
        Client.guiYeuCau("BC05");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAssignActionPerformed

    /**
     * Xu ly button tao tai khoan moi
     *
     * @param evt
     */
    private void btCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateAccountActionPerformed
        /* Lay du lieu */
        String tenNguoiDung = txtUser.getText();
        String matKhau = txtPass.getText();
        String loaiTaiKhoan = "NV";
        if (rdAdmin.isSelected()) {
            loaiTaiKhoan = "QTV";
        }
        if (rdKtv.isSelected()) {
            loaiTaiKhoan = "KTV";
        }

        /* Kiem tra mat khau nhap lai */
        if (txtPass.getText().equals(txtRePass.getText()) && !txtPass.getText().equals("")) {
            /* Mo ket noi */
            txtUser.setText("");
            txtPass.setText("");
            txtRePass.setText("");
            rdStaff.setSelected(true);
            rdAdmin.setSelected(false);
            rdKtv.setSelected(false);
            tbAccount.removeAll();
            /* Gui yeu cau tao tai khoan moi */
            Client.taiKhoan = new TaiKhoan(tenNguoiDung, matKhau, loaiTaiKhoan);
            Client.guiYeuCau("TK02");
        } else {
            JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu không phù hợp!");
        }
    }//GEN-LAST:event_btCreateAccountActionPerformed

    /**
     * Xu ly button xoa tai khoan
     *
     * @param evt
     */
    public void clear(DefaultTableModel model) {
        int rowCount = model.getRowCount();
        while (rowCount > 0) {
            model.removeRow(0);
            rowCount--;
        }
    }
    private void btDeleteAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteAccountActionPerformed
        /* Lấy hàng được chọn để xóa */

        int selectedRow = tbAccount.getSelectedRow();
        System.out.print(selectedRow);
        try {
            int idTaiKhoan = (int) tbAccount.getValueAt(selectedRow, 0);
//            clear(modelBangTaiKhoan);
            tbAccount.removeAll();
            /* Gui yeu cau xoa tai khoan */
            Client.taiKhoan = new TaiKhoan(idTaiKhoan);
            Client.guiYeuCau("TK05");
//            client.guiYeuCauHienThi("TK01");
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Phai chon 1 tai khoan de xoa.");
        }

        /* Nap lai bang tai khoan */
//        napThongTinTaiKhoan();
    }//GEN-LAST:event_btDeleteAccountActionPerformed

    /**
     * Xu ly button cap nhat tai khoan
     *
     * @param evt
     */
    private void btUpdataAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdataAccountActionPerformed
        // Lấy hàng được chọn để xóa   

        int selectedRow = tbAccount.getSelectedRow();
        int idTaiKhoan = (int) tbAccount.getValueAt(selectedRow, 0);
        String tenNguoiDung = tbAccount.getValueAt(selectedRow, 1).toString();
        String loaiTaiKhoan = tbAccount.getValueAt(selectedRow, 2).toString();
        String trangThai = tbAccount.getValueAt(selectedRow, 3).toString();
//        clear(modelBangTaiKhoan);
        tbAccount.removeAll();
        /* Mo ket noi */
//        Client client = new Client();

        /* Gui yeu cau cap nhat tai khoan */
        Client.taiKhoan = new TaiKhoan(idTaiKhoan, tenNguoiDung, loaiTaiKhoan, trangThai);
        Client.guiYeuCau("TK03");
//        client.guiYeuCauHienThi("TK01");

        /* Nap lai bang tai khoan */
//        napThongTinTaiKhoan();
    }//GEN-LAST:event_btUpdataAccountActionPerformed


    private void rdAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdAdminActionPerformed
        rdStaff.setSelected(false);
        rdKtv.setSelected(false);
    }//GEN-LAST:event_rdAdminActionPerformed

    private void rdStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdStaffActionPerformed
        rdAdmin.setSelected(false);
        rdKtv.setSelected(false);
    }//GEN-LAST:event_rdStaffActionPerformed

    /**
     * Xu ly button xoa thiet bi
     *
     * @param evt
     */
    private void btnDeleteTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTBActionPerformed
        //Lấy hàng được chọn để xóa   
        int selectedRow = tbqt.getSelectedRow();
        int idThietBi = (int) tbqt.getValueAt(selectedRow, 0);
        try {
            tbqt.removeAll();
            /* Gui yeu cau xoa thiet bi */
            Client.thietBi = new ThietBi(idThietBi);
            Client.guiYeuCau("TB05");
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Phải chọn một thiết bị để xoá!");
        }
    }//GEN-LAST:event_btnDeleteTBActionPerformed

    /**
     * Xu ly button cap nhat thiet bi
     *
     * @param evt
     */
    private void btnUpdataTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdataTBActionPerformed
        int selectedRow = tbqt.getSelectedRow();

        /* Lấy ra từng dòng của bản để gửi cập nhập */
        int id = (int) tbqt.getValueAt(selectedRow, 0);
        String ten = tbqt.getValueAt(selectedRow, 1).toString();
        String vitri = tbqt.getValueAt(selectedRow, 2).toString();
        String trangthai = tbqt.getValueAt(selectedRow, 3).toString();
        try {
            /* Gui yeu cau cap nhat thiet bi */
            Client.thietBi = new ThietBi(id, ten, vitri, trangthai);
            Client.guiYeuCau("TB03");
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Chưa cập nhật được thông tin thiết bị!");
        }
    }//GEN-LAST:event_btnUpdataTBActionPerformed

    /**
     * Xu ly button xoa bao cao
     *
     * @param evt
     */
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        /* Lấy hàng được chọn để xóa */
        int idBaoCao = (int) tbql.getValueAt(tbql.getSelectedRow(), 0);
        tbql.removeAll();
        /* Gui yeu cau xoa bao cao */
        Client.baoCao = new BaoCao(idBaoCao);
        Client.guiYeuCau("BC04");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void rdKtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdKtvActionPerformed
        rdAdmin.setSelected(false);
        rdStaff.setSelected(false);
    }//GEN-LAST:event_rdKtvActionPerformed

    private void cbKtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKtvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKtvActionPerformed

    private void cbKtvItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbKtvItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKtvItemStateChanged

    private void tbqlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbqlMouseClicked
        // TODO add your handling code here:
        int idBaoCao = (int) tbql.getValueAt(tbql.getSelectedRow(), 0);
        for (BaoCao bc : Client.danhSachBaoCao) {
            if (bc.getId() == idBaoCao) {
                mota.setText(bc.getMoTaSuCo());
            }
        }
    }//GEN-LAST:event_tbqlMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQuanTriVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQuanTriVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQuanTriVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GiaoDienQuanTriVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GiaoDienQuanTriVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCreateAccount;
    private javax.swing.JButton btDeleteAccount;
    private javax.swing.JButton btUpdataAccount;
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDeleteTB;
    private javax.swing.JButton btnThemThietBi;
    private javax.swing.JButton btnUpdataTB;
    private javax.swing.JComboBox<String> cbKtv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea mota;
    private javax.swing.JRadioButton rdAdmin;
    private javax.swing.JRadioButton rdKtv;
    private javax.swing.JRadioButton rdStaff;
    private javax.swing.JTable tbAccount;
    private javax.swing.JTable tbql;
    private javax.swing.JTable tbqt;
    private javax.swing.JTextField tentb;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtRePass;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField vitritb;
    // End of variables declaration//GEN-END:variables
}
