package auctionsniper;

public class AuctionSniper implements AuctionEventListener {
	private SniperListener sniperListener;
	private Auction auction;
	private SniperSnapshot snapshot;
	
	public AuctionSniper(Auction auction, SniperListener listener, String itemId) {
		this.sniperListener = listener;
		this.auction = auction;
		this.snapshot = SniperSnapshot.joining(itemId);
	}
	
	@Override
	public void auctionClosed() {
		snapshot = snapshot.closed();
		notifyChange();
	}

	@Override
	public void currentPrice(int price, int increment, PriceSource priceSource) {
		switch(priceSource) {
		case FromSniper:
			snapshot = snapshot.winning(price);
			break;
		case FromOtherBidder:
			final int bid = price + increment;
			auction.bid(bid);
			snapshot = snapshot.bidding(price, bid);
			break;
		}
		notifyChange();
	}
	
	private void notifyChange() {
		sniperListener.sniperStateChanged(snapshot);
	}
}
