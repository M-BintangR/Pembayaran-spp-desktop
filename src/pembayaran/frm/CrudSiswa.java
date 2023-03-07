/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaran.frm;
import DBkoneksi.Koneksi;
import Dashboard.DashboardAdmin;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Muhammad bintang
 */
public class CrudSiswa extends javax.swing.JFrame {
     private DefaultTableModel model = null;
     Koneksi k = new Koneksi();
     private PreparedStatement stat;
     private ResultSet rs;
     private int idComboKelas, idComboSpp;

    /**
     * Creates new form CrudSiswa
     */
    public CrudSiswa() {
        initComponents();
        k.connect();
        this.recordTable();
        this.relasiKelas();
        this.relasiSpp();
        this.inputId.setVisible(false);
    }
    
    
    public void relasiKelas(){
       try{
        stat = k.getCon().prepareStatement("SELECT * FROM tbl_kelas");
        rs = stat.executeQuery();
        
        this.rs = this.stat.executeQuery();
        
        while(rs.next()){
            this.inputKelas.addItem(rs.getString("id") + ":" + rs.getString("nama_kelas"));
        } 
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
        
    }
    
    public void getIdCombo(){
        String[]  idRelasiKelas, idRelasiSpp;
        
        idRelasiKelas = this.inputKelas.getSelectedItem().toString().split(":");
        this.idComboKelas = Integer.parseInt(idRelasiKelas[0]);
        idRelasiSpp = this.inputSpp.getSelectedItem().toString().split(":");
        this.idComboSpp = Integer.parseInt(idRelasiSpp[0]);
    }
    
    public void relasiSpp(){
        try{
            stat = k.getCon().prepareStatement("SELECT * FROM tbl_spp");
            rs = stat.executeQuery();
            this.rs = this.stat.executeQuery();
            
            while(rs.next()){
                this.inputSpp.addItem( rs.getString("id") + ":" + rs.getString("nominal"));
            }
            
            this.getIdCombo();
            
        }catch(Exception e){
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
     private void clearComponent(){
        this.inputNIS.setText("");
        this.inputNama.setText("");
        this.inputNoTelp.setText("");
        this.inputAlamat.setText("");
    }
    
    //! RECORD TABEL
    public void recordTable(){
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("NIS");
        model.addColumn("NAMA");
        model.addColumn("KELAS");
        model.addColumn("ALAMAT");
        model.addColumn("NO TELP");
        model.addColumn("SPP");
        this.tblSiswa.setModel(model);
        try {
           stat = k.getCon().prepareStatement("SELECT * FROM view_siswa");
           rs = stat.executeQuery();
           while(rs.next()){
               Object[] data = {
                   rs.getString("id"),
                   rs.getString("nis"),
                   rs.getString("nama"),
                   rs.getString("nama_kelas"),
                   rs.getString("alamat"),
                   rs.getString("no_telp"),
                   rs.getString("nominal"),
               };
            model.addRow(data);
        }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.clearComponent();
    }
    
    //! PROSES REKAM
    
    public void onHandleRekam(){
           try {
            stat = k.getCon().prepareStatement("INSERT INTO tbl_siswa VALUES (?,?,?,?,?,?,?)");
            stat.setInt(1,0);
            stat.setString(2, this.inputNIS.getText());
            stat.setString(3, this.inputNama.getText());
            stat.setString(4, String.valueOf(this.idComboKelas));
            stat.setString(5, this.inputAlamat.getText());
            stat.setString(6, this.inputNoTelp.getText());
            stat.setString(7, String.valueOf(this.idComboSpp));
            stat.executeUpdate();
            this.recordTable();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI TAMBAH !");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void onHandleHapus(){
        try{
            stat = k.getCon().prepareStatement("DELETE FROM tbl_siswa WHERE id= ?");
            stat.setString(1, this.inputId.getText());
            stat.executeUpdate();
            this.recordTable();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI HAPUS !");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    //! CLICK TABEL
    
    public void onHandleClick(){
          this.inputId.setText(model.getValueAt(tblSiswa.getSelectedRow(), 0).toString());
          this.inputNIS.setText(model.getValueAt(tblSiswa.getSelectedRow(), 1).toString());
          this.inputNama.setText(model.getValueAt(tblSiswa.getSelectedRow(), 2).toString());
          this.inputKelas.setSelectedItem(model.getValueAt(tblSiswa.getSelectedRow(), 3).toString());
          this.inputAlamat.setText(model.getValueAt(tblSiswa.getSelectedRow(), 4).toString());
          this.inputNoTelp.setText(model.getValueAt(tblSiswa.getSelectedRow(), 5).toString());
          this.inputSpp.setSelectedItem(model.getValueAt(tblSiswa.getSelectedRow(), 6).toString());
    }
    
    public void onHandleEdit(){
         try {       
             stat = k.getCon().prepareStatement("UPDATE tbl_siswa SET nis=?,"
            + "nama=?, id_kelas=?, alamat=?, no_telp=?, id_spp=? WHERE id=?");
            stat.setString(1, this.inputNIS.getText());
            stat.setString(2, this.inputNama.getText());
            stat.setString(3, String.valueOf(this.idComboKelas));
            stat.setString(4, this.inputAlamat.getText());
            stat.setString(5, this.inputNoTelp.getText());
            stat.setString(6, String.valueOf(this.idComboSpp));
            stat.setString(7, this.inputId.getText());
            stat.executeUpdate();
            this.recordTable();
            JOptionPane.showMessageDialog(null, "DATA BERHASIL DI UBAH !");
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnMenu = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        inputNama = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputNIS = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        inputNoTelp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        inputKelas = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        inputSpp = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputAlamat = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSiswa = new javax.swing.JTable();
        btnHapus = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnRekam = new javax.swing.JButton();
        inputId = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 0, 204));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("KELOLA DATA SISWA");

        btnMenu.setText("OPEN MENU");
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMenu)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMenu)
                .addContainerGap())
        );

        jLabel3.setText("Nama Siswa");

        jLabel5.setText("NIS");

        jLabel7.setText("No Telp");

        jLabel8.setText("Kelas");

        inputKelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputKelasActionPerformed(evt);
            }
        });

        jLabel9.setText("Nominal Spp");

        inputSpp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSppActionPerformed(evt);
            }
        });

        jLabel10.setText("Alamat");

        inputAlamat.setColumns(20);
        inputAlamat.setRows(5);
        jScrollPane1.setViewportView(inputAlamat);

        tblSiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSiswaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSiswa);

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnRekam.setText("REKAM");
        btnRekam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRekamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(inputSpp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(218, 218, 218)
                                                .addComponent(jLabel4))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(222, 222, 222)
                                                .addComponent(jLabel5))))
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel10)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRekam, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(inputId, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(inputNama, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel9)
                                            .addComponent(inputNIS, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 5, Short.MAX_VALUE)))
                        .addContainerGap(15, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(inputNIS, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(inputNama))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputNoTelp, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inputSpp, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputKelas, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(inputId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRekam, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        this.onHandleHapus();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        this.onHandleEdit();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnRekamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRekamActionPerformed
        this.onHandleRekam();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRekamActionPerformed

    private void inputSppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSppActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_inputSppActionPerformed

    private void tblSiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaMouseClicked
        this.onHandleClick();
        // TODO add your handling code here:
    }//GEN-LAST:event_tblSiswaMouseClicked

    private void inputKelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputKelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputKelasActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        DashboardAdmin dashboard = new DashboardAdmin();
        dashboard.setVisible(true);
        this.setVisible(false);
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMenuActionPerformed

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
            java.util.logging.Logger.getLogger(CrudSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrudSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrudSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrudSiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrudSiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnRekam;
    private javax.swing.JTextArea inputAlamat;
    private javax.swing.JTextField inputId;
    private javax.swing.JComboBox<String> inputKelas;
    private javax.swing.JTextField inputNIS;
    private javax.swing.JTextField inputNama;
    private javax.swing.JTextField inputNoTelp;
    private javax.swing.JComboBox<String> inputSpp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tblSiswa;
    // End of variables declaration//GEN-END:variables
}
