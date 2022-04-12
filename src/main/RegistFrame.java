package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.MySQLDB;
import javax.swing.JPasswordField;

public class RegistFrame extends JFrame{
	
	private JPanel contentPane;
	private JTextField IDInput;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private boolean idCheckFlag = false;
	
	public RegistFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 252, 356);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("돌아가기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main main = new Main();
				main.setVisible(true);
			}
		});
		btnNewButton.setBounds(56, 293, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 29));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(44, 6, 142, 55);
		contentPane.add(lblNewLabel);
		
		JLabel IDLabel = new JLabel("아이디");
		IDLabel.setBounds(56, 62, 61, 16);
		contentPane.add(IDLabel);
		
		JLabel PWLabel = new JLabel("비밀번호");
		PWLabel.setBounds(56, 138, 61, 16);
		contentPane.add(PWLabel);
		
		JLabel PWLabel2 = new JLabel("비밀번호 재확인");
		PWLabel2.setBounds(56, 199, 81, 16);
		contentPane.add(PWLabel2);
		
		
		
		IDInput = new JTextField();
		IDInput.setBounds(56, 78, 130, 26);
		contentPane.add(IDInput);
		IDInput.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(56, 154, 130, 26);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(56, 216, 130, 26);
		contentPane.add(passwordField_1);
		
		JButton registBtn = new JButton("회원가입");
		registBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = IDInput.getText();
				String pw = new String(passwordField.getPassword());
				String pw2 = new String(passwordField_1.getPassword());
				System.out.println(id + ", " + pw + ", " + pw2);
				
				//각 필드 유효성 검사
				if(
					CharacterCheck.effectivenessCheck(id)
					&& CharacterCheck.effectivenessCheck(pw)
					&& CharacterCheck.effectivenessCheck(pw2)
				) {
					if(pw.length() > 8 && pw2.length() > 8) {
						if(pw.equals(pw2)) {
							System.out.println("비번일치");
							MySQLDB db = new MySQLDB();
							db.insert(id, pw);
							setVisible(false);
							Main main = new Main();
							main.setVisible(true);
						}else {
							JOptionPane.showMessageDialog(contentPane, "비밀번호가 일치하지 않습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(contentPane, "비밀번호는 8자리 이상 이어야 합니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "공백, 특수기호는 입력할 수 없습니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		registBtn.setBounds(56, 252, 117, 29);
		contentPane.add(registBtn);
		
		JButton IDHaveCheck = new JButton("중복확인");
		IDHaveCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MySQLDB main = new MySQLDB();
				if(main.alreadyHaveIDCheck(IDInput.getText())) {
					JOptionPane.showMessageDialog(contentPane, "이미있는 ID입니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(contentPane, "가입 가능한 ID입니다.", "정보", JOptionPane.INFORMATION_MESSAGE);
					idCheckFlag = true;
				}
				
			}
		});
		IDHaveCheck.setBounds(115, 102, 71, 29);
		contentPane.add(IDHaveCheck);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistFrame frame = new RegistFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
