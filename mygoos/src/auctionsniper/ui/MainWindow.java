package auctionsniper.ui;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import auctionsniper.SniperSnapshot;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 8163690418345170568L;
	private final SnipersTableModel snipers;
	
	public static final String APPLICATION_TITLE = "Auction Sniper";
	public static final String MAIN_WINDOW_NAME = "Auction Sniper Main";
	public static final String SNIPER_STATUS_NAME = "sniper status";
	private static final String SNIPERS_TABLE_NAME = "Auction Sniper Table";
	
	public MainWindow(SnipersTableModel snipers) {
		super(APPLICATION_TITLE);
		this.snipers = snipers;
		setName(MainWindow.MAIN_WINDOW_NAME);
		fillContentPane(makeSnipersTable());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void fillContentPane(JTable snipersTable) {
		final Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(new JScrollPane(snipersTable), BorderLayout.CENTER);
	}
	
	private JTable makeSnipersTable() {
		final JTable snipersTable = new JTable(snipers);
		snipersTable.setName(SNIPERS_TABLE_NAME);
		return snipersTable;
	}
	
	public void sniperStateChanged(SniperSnapshot sniperSnapshot) {
		snipers.sniperStateChanged(sniperSnapshot);
	}
}
