package forms;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.DomainObject;
import domain.Tehnolog;
import server.StartServerThread;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ServerForm extends JFrame {

	private JPanel contentPane;
	private StartServerThread startThread;
	private JButton btnStartStop;
	private JLabel lblMessage;
	/**
	 * Launch the application.
	 */
	public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="cobtnStartStop desc=" Look and feel setting code (optional) ">
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
                new ServerForm().setVisible(true);
            }
        });
    }

	StartServerThread serverThread;
    List<Tehnolog> connected;
    public ServerForm() {
        initComponents();
        serverOFF();
        connected = new ArrayList<>();
    }
    
    private void btnStartStopActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(!StartServerThread.isOn()){
            try {
                serverThread = new StartServerThread(this);
                serverThread.start();
                serverON();
                StartServerThread.setOn(true);
            } catch (IOException ex) {
               ex.printStackTrace();
            }
           
        }
        else{
            serverThread.stopThread();
            serverOFF();
            StartServerThread.setOn(false);
        }
    } 
    
    private void serverON() {
        btnStartStop.setText("Stop");
        lblMessage.setText("Server turned on");
    }

    private void serverOFF() {
        btnStartStop.setText("Start");
        lblMessage.setText("Server turned off");
    }

    public void addUser(DomainObject dom){
        Tehnolog t=(Tehnolog) dom;
        connected.add(t);
    }
        
    public void removeUser(DomainObject dom){
        Tehnolog t=(Tehnolog) dom;
        for (Tehnolog tehn : connected) {
            if(tehn.getTehnologID()==t.getTehnologID()){
                connected.remove(tehn);
                return;
            }
        }
    }

    public void disconnectUser(DomainObject user) {
        Tehnolog t = (Tehnolog)user;
        for(Tehnolog teh : connected){
            if(teh.getTehnologID() == t.getTehnologID()){
                connected.remove(t);
                return;
            }
        }
    } 
	public void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 213);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(167, 10, 79, 13);
		contentPane.add(lblNewLabel);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMessage.setBounds(37, 56, 373, 22);
		contentPane.add(lblMessage);
		
		btnStartStop = new JButton("Start");
		btnStartStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartStopActionPerformed(e);
			}
		});
		btnStartStop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnStartStop.setBounds(148, 111, 85, 21);
		contentPane.add(btnStartStop);
	}
}
