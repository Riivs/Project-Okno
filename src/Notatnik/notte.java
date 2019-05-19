package Notatnik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class notte {
	public static Boolean czyKlawiszXJestWcisniety = false;
	public static int CTRL = 17;
	public static Boolean czyKlawiszCtrlJestWcisniety = false;

	public static void main(String argv[]) {
		JFrame okno = new JFrame();

		okno.setLocationRelativeTo(null);
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setVisible(true);
		JTextArea textArea = new JTextArea(5, 20);
		textArea.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent d) {
				if (d.getKeyChar() == 'x') {
					czyKlawiszXJestWcisniety = false;
				}
				if (d.getKeyCode() == CTRL) {
					czyKlawiszCtrlJestWcisniety = false;
					System.out.println("Ctrl odcisniety");
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == CTRL) {
					czyKlawiszCtrlJestWcisniety = true;
					System.out.println("Keep pressed" + e.getKeyCode());
				}
				if (e.getKeyChar() == 'x') {
					czyKlawiszXJestWcisniety = true;
					System.out.println("Keep pressed" + e.getKeyChar());
				}
				if (czyKlawiszCtrlJestWcisniety && czyKlawiszXJestWcisniety) {
					System.exit(0);
				}
			}
		});

		JMenuBar jmb = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu About = new JMenu("About");

		JMenuItem save = new JMenuItem("Save");
		JMenuItem read = new JMenuItem("Read");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem info = new JMenuItem("Info");

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				Object zrodlo = ex.getSource();
				System.exit(0);

			}
		});

		read.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent rd) {

				JFileChooser wybierz = new JFileChooser();
				wybierz.addChoosableFileFilter(new FileNameExtensionFilter(".txt", "txt"));
				wybierz.setAcceptAllFileFilterUsed(true);
				wybierz.setFileSelectionMode(JFileChooser.FILES_ONLY);
				wybierz.showOpenDialog(null);
				File f = wybierz.getSelectedFile();
				String filename = f.getAbsolutePath();

				try {
					FileReader reader = new FileReader(filename);

					BufferedReader br = new BufferedReader(reader);
					textArea.read(br, null);
					br.close();
					textArea.requestFocus();

				} catch (Exception e) {

					JOptionPane.showMessageDialog(null, e);
				}

			}

		});

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent rd) {

			}
		});

		file.add(save);
		file.add(read);
		file.add(exit);

		About.add(info);

		okno.setJMenuBar(jmb);
		jmb.add(file);
		jmb.add(About);
		okno.add(textArea);

	}

}
