package com.raven.main;

import com.nhomsau.util.CheckLogin;
import com.nhomsau.view.Form_Lecturers;
import com.nhomsau.view.Form_Statistical;
import com.nhomsau.viewmodel.LoginModel;
import com.raven.component.Header;
import com.raven.component.Menu;
import com.raven.dialog.Message;
import com.raven.event.EventMenuSelected;
import com.raven.event.EventShowPopupMenu;
import com.raven.form.FormDauDiem;
import com.raven.form.FormDiemTheoLop;
import com.raven.form.FormKy;
import com.raven.form.FormNganh;
import com.raven.form.FormThongTinSv;
import com.raven.form.Form_Home;
import com.raven.form.Form_MonHoc;
import com.raven.form.Form_Mon_DauDiem;
import com.raven.form.Form_ThongKeHocLuc;
import com.raven.form.Frm_ThongKePass;
import com.raven.form.ListStudentPanel;
import com.raven.form.LoginFrame;
import com.raven.form.LopHocPanel;
import com.raven.form.MainForm;
import com.raven.form.ThongKeDiemPanel;
import com.raven.swing.MenuItem;
import com.raven.swing.PopupMenu;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import ru.krlvm.swingacrylic.SwingAcrylic;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private LoginModel loginModel;
    public Main() {
        initComponents();
        if(CheckLogin.isLogin()){
            loginModel = CheckLogin.loginModel;
        }
        init();
    }

    private void init() {
        layout = new MigLayout("fill", "10[]10[100%, fill]10", "10[fill, top]10");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header();
        if (loginModel != null) {
            System.out.println(loginModel.getHoTen());
            header.setNameUser(loginModel);
        }
        main = new MainForm();
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
//                if(CheckLogin.isLogin() && CheckLogin.loginModel.getChucVu().trim().equalsIgnoreCase("Giáo Vụ")){
                if (menuIndex == 0) {
                    if (subMenuIndex == 0) {
                        main.showForm(new ListStudentPanel());
                    } else if (subMenuIndex == 1) {
                        main.showForm(new Form_Statistical());
                    }else if (subMenuIndex == 2) {
                        main.showForm(new Form_ThongKeHocLuc());
                    }
                } else if (menuIndex == 1) {
                    if (subMenuIndex == 0) {
                        main.showForm(new ThongKeDiemPanel());
                    }else if(subMenuIndex == 1){
                        main.showForm(new FormDiemTheoLop());
                    }
                } else if (menuIndex == 2) {
                    if (subMenuIndex == 0) {
                        main.showForm(new FormKy());
                    } else if (subMenuIndex == 1) {
                        main.showForm(new Form_MonHoc());
                    } else if (subMenuIndex == 2) {
                        main.showForm(new FormNganh());
                    } else if(subMenuIndex == 3){
                        main.showForm(new Form_Lecturers());
                    } else if(subMenuIndex == 4){
                        main.showForm(new LopHocPanel());
                    } else if(subMenuIndex == 5){
                        main.showForm(new FormDauDiem());
                    }
                } else if(menuIndex == 3){
                    if(subMenuIndex == 0){
                        main.showForm(new FormThongTinSv());
                    }else if(subMenuIndex == 1){
                        int confirm = JOptionPane.showConfirmDialog(Main.getFrames()[0], "Ban co muon dang xuat khong?");
                        if(confirm == JOptionPane.YES_OPTION){
                            CheckLogin.loginModel = null;
                            
                            Main.getFrames()[0].dispose();
                            new LoginFrame().setVisible(true);
                        }
                        
                    }
                } else if (menuIndex == 4) {
                    int confirm = JOptionPane.showConfirmDialog(bg, "Ban co chac chan muon thoat ?");
                    if(confirm == JOptionPane.YES_OPTION)
                    System.exit(0);
                } 
//                }else{
//                    showMessage("Ban chua dang nhap!");
//                }
            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 62;
                int y = Main.this.getY() + com.getY() + 95;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem();
        bg.add(menu, "w 230!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 95%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
                menu.setEnableMenu(false);
                if (menu.isShowMenu()) {
                    menu.hideallMenu();
                }
            }
        });
        //  Start with this form
        main.showForm(new Form_Home());
    }
    private void showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new com.raven.swing.PanelTransparent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(245, 245, 245));
        bg.setOpaque(true);
        bg.setPreferredSize(new java.awt.Dimension(1280, 760));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 673, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            SwingAcrylic.prepareSwing();
            Main frame = new Main();
            frame.setVisible(true);
            SwingAcrylic.processFrame(frame);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.PanelTransparent bg;
    // End of variables declaration//GEN-END:variables
}
