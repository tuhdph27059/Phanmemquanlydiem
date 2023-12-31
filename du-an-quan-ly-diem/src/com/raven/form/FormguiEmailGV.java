/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.raven.form;

import com.nhomsau.repository.impl.KyRepository;
import com.nhomsau.repository.impl.LopRepository;
import com.nhomsau.service.IMonService;
import com.nhomsau.service.impl.MonService;
import com.nhomsau.service.impl.SinhVienService;
import com.nhomsau.util.CheckLogin;
import com.nhomsau.viewmodel.LoginModel;
import com.nhomsau.viewmodel.QuanLyKy;
import com.nhomsau.viewmodel.QuanLyLop;
import com.nhomsau.viewmodel.QuanLyMon;
import com.nhomsau.viewmodel.SinhVienView;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.tools.ant.taskdefs.email.Mailer;

/**
 *
 * @author Admin
 */
public class FormguiEmailGV extends javax.swing.JPanel {

    /**
     * Creates new form FormguiEmailGV
     */
    LopRepository lopRepository;
    SinhVienService sinhVienService;
    DefaultTableModel model;
    KyRepository kyRepository;
    IMonService monService;
    LoginModel loginModel;
    ArrayList<String> listEmail;

    public FormguiEmailGV() {
        initComponents();
        if (CheckLogin.isLogin()) {
            loginModel = CheckLogin.loginModel;
        }
        txtUserName.setText(loginModel.getEmail());
        txtPassword.setText("lpzazmilxndfuyae");
        tblEmail.fixTable(jScrollPane1);
        model = (DefaultTableModel) tblEmail.getModel();
        lopRepository = new LopRepository();
        sinhVienService = new SinhVienService();
        kyRepository = new KyRepository();
        monService = new MonService();
        cbxKy.setLabeText("Kỳ");
        cbxMon.setLabeText("Môn");
        cbxLop.setLabeText("Lớp");
        txtTimKiem.setLabelText("Tìm kiếm theo maSV");
        fillAllKy();
        listEmail = new ArrayList<>();
    }

    private void fillAllKy() {
        cbxKy.removeAllItems();
        List<QuanLyKy> lstKy = kyRepository.findAll();
        for (QuanLyKy ky : lstKy) {
            cbxKy.addItem(ky);
        }
    }

    private void loadTable(List<SinhVienView> list) {
        model.setRowCount(0);
        int i = 1;
        for (SinhVienView sv : list) {
            model.addRow(new Object[]{i, sv.getMa(), sv.getEmail()});
            i++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiem = new com.raven.swing.textfield.TextField();
        cbxKy = new com.raven.swing.combobox.Combobox();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmail = new com.raven.swing.table.Table();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAMessage = new javax.swing.JTextArea();
        btnGui = new com.raven.swing.button.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtTo = new javax.swing.JTextArea();
        btnSelectAll = new com.raven.swing.button.Button();
        cbxMon = new com.raven.swing.combobox.Combobox();
        cbxLop = new com.raven.swing.combobox.Combobox();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gửi EMail");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        cbxKy.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxKyItemStateChanged(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Người gửi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 0, 255))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Email From:");

        txtUserName.setEditable(false);
        txtUserName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(255, 204, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Password:");

        txtPassword.setEditable(false);
        txtPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(204, 153, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblEmail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã SV", "Email"
            }
        ));
        tblEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmailMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblEmail);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Người nhận", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 51, 255))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Email To:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Subject:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Message:");

        txtAMessage.setColumns(20);
        txtAMessage.setRows(5);
        jScrollPane1.setViewportView(txtAMessage);

        btnGui.setForeground(new java.awt.Color(0, 255, 0));
        btnGui.setText("Gửi");
        btnGui.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiActionPerformed(evt);
            }
        });

        txtTo.setColumns(20);
        txtTo.setRows(5);
        jScrollPane3.setViewportView(txtTo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSubject)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
                .addContainerGap(77, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        btnSelectAll.setForeground(new java.awt.Color(204, 0, 204));
        btnSelectAll.setText("Select ALL");
        btnSelectAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectAllActionPerformed(evt);
            }
        });

        cbxMon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMonItemStateChanged(evt);
            }
        });

        cbxLop.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxLopItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbxKy, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(cbxMon, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbxLop, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxKy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(514, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        // TODO add your handling code here:
        String timKiem = txtTimKiem.getText();
        List<SinhVienView> list = new ArrayList<>();
        List<SinhVienView> listSinhViens = new ArrayList<>();
        QuanLyLop lop = (QuanLyLop) cbxLop.getSelectedItem();
        listSinhViens = sinhVienService.findSinhVienTheoLop(lop.getIdLop());
        for (SinhVienView sv : listSinhViens) {
            if (sv.getMa().contains(timKiem)) {
                list.add(sv);
            }
        }
        loadTable(list);
    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void cbxKyItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxKyItemStateChanged
        // TODO add your handling code here:
        QuanLyKy ky = (QuanLyKy) cbxKy.getSelectedItem();
        if (loginModel != null) {
            List<QuanLyMon> listMon = monService.getMonTheoGV(loginModel.getIdNganh(), ky.getId(), loginModel.getIdUser());
            cbxMon.removeAllItems();
            for (QuanLyMon mon : listMon) {
                cbxMon.addItem(mon);
            }
        }
    }//GEN-LAST:event_cbxKyItemStateChanged

    private void tblEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmailMouseClicked
        // TODO add your handling code here:
        int selectRow = tblEmail.getSelectedRow();
        if (selectRow >= 0) {
            int listSize = listEmail.size();
            if (listSize != 0) {
                for (int i = 0; i < listSize; i++) {
                    if (listEmail.get(i).equals(tblEmail.getValueAt(selectRow, 2).toString())) {
                        listEmail.remove(i);
                        txtTo.setText("");
                        for (int q = 0; q < listEmail.size(); q++) {
                            if (txtTo.getText().isBlank()) {
                                txtTo.setText(listEmail.get(q));
                            } else {
                                txtTo.setText(txtTo.getText() + "\n" + listEmail.get(q));
                            }
                        }
                        System.out.println(listEmail.size());
                        return;
                    }
                }
                listEmail.add(tblEmail.getValueAt(selectRow, 2).toString());
                txtTo.setText(txtTo.getText() + "\n" + tblEmail.getValueAt(selectRow, 2).toString());
                System.out.println(listEmail.size());
            } else {
                listEmail.add(tblEmail.getValueAt(selectRow, 2).toString());
                txtTo.setText(tblEmail.getValueAt(selectRow, 2).toString());
                System.out.println(listEmail.size());
            }
        }
    }//GEN-LAST:event_tblEmailMouseClicked

    private void btnGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiActionPerformed
        // TODO add your handling code here:
        try {
            Properties p = new Properties();
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.auth", true);
            p.put("mail.smtp.starttls.enable", true);
            p.put("mail.smtp.host", "smtp.gmail.com");

            Session s = Session.getDefaultInstance(p,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(txtUserName.getText(), txtPassword.getText());
                }
            });
            Message m = new MimeMessage(s);
            if (txtTo.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Không được để trống người nhận");
                return;
            }
            if (txtSubject.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Không được để tiêu đề rỗng");
                return;
            }
            if (txtAMessage.getText().isBlank()) {
                JOptionPane.showMessageDialog(this, "Không được để nội dung rỗng");
                return;
            }

            for (int i = 0; i < listEmail.size(); i++) {
                m.setFrom(new InternetAddress(txtUserName.getText()));
                m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(listEmail.get(i)));
                m.setSubject(txtSubject.getText());
                m.setText(txtAMessage.getText());

                Transport.send(m);
            }
            listEmail.remove(listEmail);
            txtTo.setText("");
            txtSubject.setText("");
            txtAMessage.setText("");
            JOptionPane.showMessageDialog(this, "Send Email SuccessFully!");
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_btnGuiActionPerformed

    private void btnSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectAllActionPerformed
        // TODO add your handling code here:
        for (int i = 0; i < tblEmail.getRowCount(); i++) {
            if (listEmail.size() != 0) {
                for (int q = 0; q < listEmail.size(); q++) {
                    if (listEmail.get(q).equals(tblEmail.getValueAt(i, 2).toString())) {
                        break;
                    }
                    if (q == listEmail.size() - 1 ) {
                            listEmail.add(tblEmail.getValueAt(i, 2).toString());
                            txtTo.setText(txtTo.getText() + "\n" + tblEmail.getValueAt(i, 2).toString());
                    }
                }
            } else {
                listEmail.add(tblEmail.getValueAt(i, 2).toString());
                txtTo.setText(tblEmail.getValueAt(i, 2).toString());
            }
        }
    }//GEN-LAST:event_btnSelectAllActionPerformed

    private void cbxMonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMonItemStateChanged
        // TODO add your handling code here:
        QuanLyKy ky = (QuanLyKy) cbxKy.getSelectedItem();
        QuanLyMon mon = (QuanLyMon) cbxMon.getSelectedItem();
        cbxLop.removeAllItems();
        if (mon != null) {
            List<QuanLyLop> listLop = this.lopRepository.findIdGV(mon.getId(), loginModel.getIdNganh(), ky.getId(), loginModel.getIdUser());

            for (QuanLyLop lop : listLop) {
                cbxLop.addItem(lop);
            }
        }
    }//GEN-LAST:event_cbxMonItemStateChanged

    private void cbxLopItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxLopItemStateChanged
        // TODO add your handling code here:
        List<SinhVienView> listSinhViens = new ArrayList<>();
        QuanLyLop lop = (QuanLyLop) cbxLop.getSelectedItem();
        if (lop != null) {
            listSinhViens = sinhVienService.findSinhVienTheoLop(lop.getIdLop());
            loadTable(listSinhViens);
        } else {
            loadTable(listSinhViens);
        }
    }//GEN-LAST:event_cbxLopItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.button.Button btnGui;
    private com.raven.swing.button.Button btnSelectAll;
    private com.raven.swing.combobox.Combobox cbxKy;
    private com.raven.swing.combobox.Combobox cbxLop;
    private com.raven.swing.combobox.Combobox cbxMon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.raven.swing.table.Table tblEmail;
    private javax.swing.JTextArea txtAMessage;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSubject;
    private com.raven.swing.textfield.TextField txtTimKiem;
    private javax.swing.JTextArea txtTo;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
