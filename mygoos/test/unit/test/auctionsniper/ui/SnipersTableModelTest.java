package test.auctionsniper.ui;

import static org.junit.Assert.*;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.hamcrest.Matcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;

import auctionsniper.SniperSnapshot;
import auctionsniper.SniperState;
import auctionsniper.ui.Column;
import auctionsniper.ui.SnipersTableModel;
import static auctionsniper.ui.SnipersTableModel.textFor;

@RunWith(JMock.class)
public class SnipersTableModelTest {
	private final Mockery context = new Mockery();
	private TableModelListener listener = context.mock(TableModelListener.class);
	private final SnipersTableModel model = new SnipersTableModel();
	
	@Before
	public void attachModelListener() {
		model.addTableModelListener(listener);
	}
	
	@Test
	public void hasEnoughColumns() {
		assertThat(model.getColumnCount(), equalTo(Column.values().length));
	}

	@Test
	public void setsSniperValuesInColumn() {
		context.checking(new Expectations() {{
			one(listener).tableChanged(with(aRowChangedEvent()));
		}});
		
		model.sniperStateChanged(new SniperSnapshot("item id", 555, 888, SniperState.BIDDING));
		
		assertColumnEquals(Column.ITEM_IDENTIFIER, "item id");
		assertColumnEquals(Column.LAST_PRICE, 555);
		assertColumnEquals(Column.LAST_BID, 888);
		assertColumnEquals(Column.SNIPER_STATE, textFor(SniperState.BIDDING));
	}
	
	@Test
	public void setsUpColumnHeadings() {
		for (Column column : Column.values()) {
			assertEquals(column.name, model.getColumnName(column.ordinal()));
		}
	}
	
	private void assertColumnEquals(Column column, Object expected) {
		final int rowIndex = 0;
		final int columnIndex = column.ordinal();
		assertEquals(expected, model.getValueAt(rowIndex, columnIndex));
	}
	
	private Matcher<TableModelEvent> aRowChangedEvent() {
		return samePropertyValuesAs(new TableModelEvent(model, 0));
	}
}
