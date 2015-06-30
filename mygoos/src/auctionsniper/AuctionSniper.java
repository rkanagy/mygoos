package auctionsniper;

public class AuctionSniper implements AuctionEventListener {
	private SniperListener sniperListener;
	private Auction auction;
	
	public AuctionSniper(Auction auction, SniperListener listener) {
		this.sniperListener = listener;
		this.auction = auction;
	}
	
	@Override
	public void auctionClosed() {
		sniperListener.sniperLost();
	}

	@Override
	public void currentPrice(int price, int increment) {
		auction.bid(price + increment);
		sniperListener.sniperBidding();
	}

}
