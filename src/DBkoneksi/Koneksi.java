/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBkoneksi;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Muhammad bintang
 */
public class Koneksi {
 private final String url="jdbc:mysql://localhost:3306/db_pembayaran_spp";
 private final String username = "root";
 private final String password = "";
 private Connection con;
 
 
 public  void connect(){
     try {
         con = DriverManager.getConnection(url, username, password);
         System.out.println("Databases Connect Success!");
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, e.getMessage());
     }
 
 }
 
  public Connection getCon() {
        return con;
  }
  
  public static void main(String args[]){
    Koneksi k = new Koneksi();
    k.connect();
  }
 
}
