package auctionsniper.ui;

import javax.swing.SwingUtilities;

import auctionsniper.SniperListener;
import auctionsniper.SniperSnapshot;

public class SwingThreadSniperListener implements SniperListener {
	private final SniperListener delegate;
	
	public SwingThreadSniperListener(SniperListener delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public void sniperStateChanged(SniperSnapshot sniperSnapshot) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				delegate.sniperStateChanged(sniperSnapshot);
			}
		});
	}
}
