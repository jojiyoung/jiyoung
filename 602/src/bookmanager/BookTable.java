package bookmanager;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import bookmanager.Book;

public class BookTable extends Frame implements ActionListener {
	String calName[] = { "코드", "책이름", "저자", "가격", "출판년도", "출판사", "인기도" };
	Object data = new Object[7];
	JTable table;
	DefaultTableModel tableModel;
	JScrollPane sp;
	ArrayList<Book> list = new ArrayList<Book>();
	JButton B_add, B_serch, B_update, B_delete, B_save, B_load, B_exit;
	JTextField t_isbn, t_title, t_author, t_price, t_publisher, t_year, t_rate;
	JPanel panel;
	private Component add;
	private Component add2;

	BookTable() {
		setLocation(200, 200);
		setSize(800, 400);
		panel = new JPanel();
		// JTble
		tableModel = new DefaultTableModel(calName, 0);
		table = new JTable(tableModel);
		sp = new JScrollPane(table);
		add(sp, BorderLayout.CENTER);
		// input Panel
		panel.setLayout(new GridLayout(7, 2));
		panel.add(new JLabel("코드"));
		panel.add(t_isbn = new JTextField(6));
		panel.add(B_add = new JButton("추가"));

		panel.add(new JLabel("도서이름"));
		panel.add(t_title = new JTextField(6));
		panel.add(B_serch = new JButton("검색"));

		panel.add(new JLabel("작가"));
		panel.add(t_author = new JTextField(6));
		panel.add(new JLabel(""));

		panel.add(new JLabel("가격"));
		panel.add(t_price = new JTextField(6));
		panel.add(B_delete = new JButton("삭제"));

		panel.add(new JLabel("출판사"));
		panel.add(t_publisher = new JTextField(6));
		panel.add(B_save = new JButton("저장"));

		panel.add(new JLabel("출판년도"));
		panel.add(t_year = new JTextField(6));
		panel.add(B_load = new JButton("열기"));

		panel.add(new JLabel("인기도"));
		panel.add(t_rate = new JTextField(6));
		panel.add(new JLabel(""));
		add(panel, BorderLayout.EAST);

		// window 종료 이벤트
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		// 버튼 이벤트 ㄹ스너
		B_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (t_author.getText().equals("")
						|| t_isbn.getText().equals("")
						|| t_price.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
					return;
				}
				if (t_publisher.getText().equals("")
						|| t_rate.getText().equals("")
						|| t_title.getText().equals("")
						|| t_year.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요");
					return;
				}

				Book book = new Book(t_isbn.getText(), t_title.getText(),
						t_author.getText(), t_price.getText(),
						t_year.getText(), t_publisher.getText(), t_rate
								.getText());
				tableModel.addRow(book.getAll());

				t_isbn.setText("");
				t_author.setText("");
				t_price.setText("");
				t_publisher.setText("");
				t_rate.setText("");
				t_title.setText("");
				t_year.setText("");

			}
		});
		B_serch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for(int i=0;i<tableModel.getDataVector().size();i++){
					if(((Vector)tableModel.getDataVector().elementAt(i)).elementAt(1).toString().substring(0,t_title.getText().length()).equals(t_title.getText())){
						table.setRowSelectionInterval(i, i);
						break;
					}
					if(i>=tableModel.getDataVector().size()-1){
						JOptionPane.showMessageDialog(null, "찾지 못하였습니다");
					}
				}
				
			}
		});
		B_delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tableModel.removeRow(table.getSelectedRow());

			}
		});
		B_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileOutputStream fout = null;
				ObjectOutputStream oos = null;

				try {
					fout = new FileOutputStream("booklist.dat");
					oos = new ObjectOutputStream(fout);
					
					ArrayList<Vector> v = new ArrayList<Vector>();
					
					for(int i=0;i<tableModel.getDataVector().size();i++){
						v.add((Vector)tableModel.getDataVector().elementAt(i));
						
					}
					
					
					oos.writeObject(v);//
					oos.reset();
					JOptionPane.showMessageDialog(null, "저장하였습니다.");

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "저장에 실패하였습니다.");
				} finally {
					try {
						oos.close();
						fout.close();

					} catch (IOException ioe) {
					}
				}
			}
		});
		B_load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FileInputStream fin = null; // 1byte 단위로 파일을 읽오올수 있는입력 String
				ObjectInputStream ois = null;

				try {
					// booklist.dat와 형성
					fin = new FileInputStream("booklist.dat");
					// file의 끝을 만날 떄까지 데이터 읽어드림
					ois = new ObjectInputStream(fin);
				
					
					ArrayList<Vector> v = (ArrayList<Vector>)ois.readObject();
					
					
					for(int i=0;i<v.size();i++){
						
						tableModel.addRow(v.get(i));
						
					}
					
					
					//table = new JTable(tableModel);
					JOptionPane.showMessageDialog(null, "불러왔습니다.");

				} catch (Exception ex) {

					JOptionPane.showMessageDialog(null, "불러오는 데 실패하였습니다.");
				} finally {
					try {

						ois.close();
						fin.close();
					} catch (IOException ioe) {
					}
				}
			}
		});
		setVisible(true);

	}

	/*
	 * FileInputStream fin = null; ObjectInputStream ois = null; Book list;
	 * B_load() {} B_load(JTextField t_isbn,JTextField t_title,JTextField
	 * t_author,JTextField t_price,JTextField t_publisher,JTextField
	 * t_year,JTextField t_rate) Object data[],DefaultTableModel
	 * tableModel,ArrayList<Book>list) {
	 * 
	 * }
	 */

	public static void main(String args[]) {
		new BookTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
