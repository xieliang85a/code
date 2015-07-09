package cn.com.xl.other;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class NotePad {

	public NotePad(){
		init();
	}
	private Frame f;
	private MenuBar menuBar;
	private Menu menu_file;
	private MenuItem menuItem_open,menuItem_save,menuItem_exit;
	private FileDialog fileDialog_open,fileDialog_save;
	private TextArea ta;
	
	private File file;
	
	private void init(){
		f = new Frame("记事本");
		menuBar = new MenuBar();
		menu_file = new Menu("文件");
		menuItem_open = new MenuItem("打开");
		menuItem_save = new MenuItem("保存");
		menuItem_exit = new MenuItem("退出");
		ta = new TextArea();
		
		fileDialog_open = new FileDialog(f, "打开",FileDialog.LOAD);
		fileDialog_save = new FileDialog(f, "保存",FileDialog.SAVE);
		
		f.setBounds(200, 100, 500, 250);
		f.setResizable(false);
		f.setLayout(new FlowLayout());
		f.add(ta);
		
		menu_file.add(menuItem_open);
		menu_file.add(menuItem_save);
		menu_file.add(menuItem_exit);
		menuBar.add(menu_file);
		f.setMenuBar(menuBar);
		myEvent();
		f.setVisible(true);
		
	}

	private void myEvent(){
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		menuItem_exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		menuItem_open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				fileDialog_open.setVisible(true);
				String path = fileDialog_open.getDirectory();
				String filename = fileDialog_open.getFile();
				if(path==null||filename==null){
					return;
				}
				ta.setText("");
				file = new File(path,filename);
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(file));
					String str = null;
					while((str = br.readLine())!=null){
						ta.append(str+"\r\n");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		//保存
		menuItem_save.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(file==null){
					fileDialog_save.setVisible(true);
					String path = fileDialog_save.getDirectory();
					String filename = fileDialog_save.getFile();
					if(path==null||filename==null){
						return;
					}
					file = new File(path,filename);
				}
				BufferedWriter bw = null;
				
				try {
					bw = new BufferedWriter(new FileWriter(file));
					System.out.println(ta.getText());
					bw.write(ta.getText());
					bw.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		
	}
	public static void main(String[] args) {
		new NotePad();
	}

}
