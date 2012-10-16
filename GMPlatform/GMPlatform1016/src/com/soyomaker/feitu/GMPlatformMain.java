package com.soyomaker.feitu;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JLabel;

public class GMPlatformMain extends JFrame implements ILog, ImportListener {

	private static final long serialVersionUID = 482984560166049013L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GMPlatformMain frame = new GMPlatformMain();
					Logger.getInstance().addLog(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;

	private JTextArea textAreaLog;

	private Importer importer = null;

	private String excelFilePath = null;

	private JButton btnImport;

	private JLabel labelExcel;

	/**
	 * Create the frame.
	 */
	public GMPlatformMain() {
		importer = new Importer();
		importer.setListener(this);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnOpenexcel = new JButton("OpenExcel");
		btnOpenexcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(".");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				jfc.showOpenDialog(GMPlatformMain.this);
				File f = jfc.getSelectedFile();
				if (f != null) {
					excelFilePath = f.getAbsolutePath();
					labelExcel.setText(excelFilePath);
				}
			}
		});
		btnOpenexcel.setBounds(6, 6, 117, 29);
		contentPane.add(btnOpenexcel);

		labelExcel = new JLabel("");
		labelExcel.setBounds(135, 11, 454, 16);
		contentPane.add(labelExcel);

		btnImport = new JButton("Export");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (excelFilePath == null) {
					JOptionPane.showMessageDialog(GMPlatformMain.this, "请设置路径");
				} else {
					btnImport.setEnabled(false);
					importer.importFromExcel(excelFilePath);
				}
			}
		});
		btnImport.setBounds(6, 45, 117, 29);
		contentPane.add(btnImport);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 579, 270);
		contentPane.add(scrollPane);

		textAreaLog = new JTextArea();
		textAreaLog.setEditable(false);
		scrollPane.setViewportView(textAreaLog);
	}

	@Override
	public void info(final Object msg) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				textAreaLog.append(msg.toString() + "\n");
			}
		});
	}

	public void onEnd() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				btnImport.setEnabled(true);
			}
		});
	}
}
