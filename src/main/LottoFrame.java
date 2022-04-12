package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.swing.DefaultListModel;
//import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dto.MemberDTO;
import javax.swing.JScrollPane;

public class LottoFrame extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton toMain;
	private JButton bbobgi;
	private JLabel sessionAccount;

	TreeSet<Integer> arr = new TreeSet<Integer>();
	List<JTextField> fields = new ArrayList<JTextField>();
	DefaultListModel<TreeSet<Integer>> listModel = new DefaultListModel<>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LottoFrame frame = new LottoFrame(new MemberDTO());
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
	//dto는 세션사용을 위해 받습니다.
	public LottoFrame(MemberDTO dto) {
		
		//System.out.println(dto);
		//UI____________________________________________________
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("로또번호 추출");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		lblNewLabel.setBounds(6, 6, 123, 32);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		textField.setEditable(false);
		textField.setBounds(16, 50, 56, 55);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(84, 50, 56, 55);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(152, 50, 56, 55);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(220, 50, 56, 55);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(288, 50, 56, 55);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Lucida Grande", Font.PLAIN, 32));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(356, 50, 56, 55);
		contentPane.add(textField_5);
		
		toMain = new JButton("로그인으로 가기");
		toMain.setBounds(298, 117, 117, 29);
		contentPane.add(toMain);
		
		bbobgi = new JButton("뽑기");
		bbobgi.setBounds(179, 117, 117, 29);
		contentPane.add(bbobgi);
		
		sessionAccount = new JLabel("ID");
		sessionAccount.setBounds(189, 6, 226, 32);
		contentPane.add(sessionAccount);
		sessionAccount.setText("ID: " + dto.getmID());
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 144, 406, 284);
		contentPane.add(scrollPane);

		JList<TreeSet<Integer>> list = new JList<>();
		scrollPane.setViewportView(list);
		
		
//		list.setBounds(6, 146, 406, 280);
//		contentPane.add(list);
		
		fields.add(textField);
		fields.add(textField_1);
		fields.add(textField_2);
		fields.add(textField_3);
		fields.add(textField_4);
		fields.add(textField_5);
		
		
		//Event____________________________________________________
		toMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main main = new Main();
				main.setVisible(true);
			}
		});
		
		bbobgi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//로또번호 뽑기 로직
				//범위는 1~45이고 중복돼선 안된다.
				//순서는 상관없다 -> 트리맵으로 정렬할 것.
				//보너스 번호는 따로 처리할 것.
				arr = new TreeSet<Integer>();
				
				//로또번호 초기화
				while(arr.size() < 6) {
					int number = (int) ((Math.random() * 45) + 1);
					arr.add(number);
				}
				
				//각 텍스트 필드에 번호 삽입
				int index = 0;
				for(int num : arr) {
					fields.get(index++).setText(Integer.toString(num));
				}
				
				//이전에 뽑았던 번호들 기록남기기
				listModel.addElement(arr);
				list.setModel(listModel);
				
				int lastIndex = list.getModel().getSize() - 1;
				if (lastIndex >= 0) {
					list.ensureIndexIsVisible(lastIndex);
				}
				
				
			}
		});
	}
}
