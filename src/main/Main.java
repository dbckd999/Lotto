package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.MySQLDB;
import dto.MemberDTO;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField IDInputFiled;
	private JTextField PWInputFiled;
	private JTextField loginResult;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel loginLabel = new JLabel("로그인");
		loginLabel.setOpaque(true);
		loginLabel.setBackground(Color.GREEN);
		loginLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setBounds(93, 22, 102, 44);
		contentPane.add(loginLabel);
		
		IDInputFiled = new JTextField();
		IDInputFiled.setBounds(48, 78, 130, 44);
		contentPane.add(IDInputFiled);
		IDInputFiled.setColumns(10);
		
		PWInputFiled = new JTextField();
		PWInputFiled.setColumns(10);
		PWInputFiled.setBounds(48, 116, 130, 44);
		contentPane.add(PWInputFiled);
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//db에서 회원 확인
				MySQLDB db = new MySQLDB();
				if(db.select(IDInputFiled.getText(), PWInputFiled.getText())) {
					MemberDTO dto = new MemberDTO(IDInputFiled.getText(),  PWInputFiled.getText());
					//System.out.println(dto);
					//로그인 시 로또번호 추출 창으로
					setVisible(false);
					LottoFrame lotto = new LottoFrame(dto);
					lotto.setVisible(true);
				}else {
					//존재하지 않는 회원입니다 출력
					loginResult.setText("존재하지 않는 회원입니다.");
				}
				
			}
		});
		loginBtn.setBounds(177, 76, 88, 84);
		contentPane.add(loginBtn);
		
		JButton memBtn = new JButton("회원가입");
		memBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistFrame rf = new RegistFrame();
				rf.setVisible(true);
				setVisible(false);
			}
		});
		
		memBtn.setBounds(93, 184, 117, 29);
		contentPane.add(memBtn);
		
		loginResult = new JTextField();
		loginResult.setBackground(SystemColor.window);
		loginResult.setEnabled(false);
		loginResult.setEditable(false);
		loginResult.setBounds(48, 158, 162, 26);
		contentPane.add(loginResult);
		loginResult.setColumns(10);
		
	}
}
