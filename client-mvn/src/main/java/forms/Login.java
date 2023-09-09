package forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import communication.ServerCommunication;
import controller.ClientController;
import domain.Component;
import domain.Tehnolog;
import json.JsonReport;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	public Tehnolog tehnolog;
	/**
	 * Launch the application.
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
           ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

	public Login() {
		initComponents();
	}
	
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 406, 253);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(40, 53, 70, 24);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(40, 100, 70, 24);
		contentPane.add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(110, 50, 162, 33);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(110, 97, 162, 33);
		contentPane.add(txtPassword);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.setBounds(143, 158, 85, 21);
		contentPane.add(btnNewButton);
	}
	
	private void getConnection() {
        if(ServerCommunication.getInstance().getSocket() == null){
            try {
                Socket socket = new Socket("localhost", 9000);
                ServerCommunication.getInstance().setSocket(socket);
                System.out.println("User connected");
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Failed at connecting on server");
            }
            
        }
    }
	
	public void btnLoginActionPerformed(java.awt.event.ActionEvent e) {
		try {
            getConnection();
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            if(username.isEmpty()){
                JOptionPane.showMessageDialog(null, "Username is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(password.isEmpty()){
                JOptionPane.showMessageDialog(null, "Password is empty", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(username.length() < 6){
                JOptionPane.showMessageDialog(null, "Username must have at least 6 characters", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if(password.length() < 6){
                JOptionPane.showMessageDialog(null, "Password must have at least 6 characters", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            tehnolog = (Tehnolog)ClientController.getInstance().login(username, password);
            
            if(tehnolog == null){
                JOptionPane.showMessageDialog(rootPane, "Unsuccessfull login. Try again!", "Info", JOptionPane.INFORMATION_MESSAGE);

            }
            else{
                MainForm mf = new MainForm(tehnolog);
                JOptionPane.showMessageDialog(rootPane, "Successfull login. Welcome!", "Info", JOptionPane.INFORMATION_MESSAGE);
                Map<Tehnolog, LocalDateTime> addTehn = new HashMap<>();
                addTehn.put(tehnolog, LocalDateTime.now());
                JsonReport.setLoginTehn(addTehn);
                JsonReport.generateReportT();
                mf.setVisible(true);
                this.dispose();
            }
                
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
}
