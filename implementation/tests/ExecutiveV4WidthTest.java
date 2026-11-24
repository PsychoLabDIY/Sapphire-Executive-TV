package com.gathin.tvhome;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class ExecutiveV4WidthTest {
    @Test public void widerSixAppDockFitsCell() {
        int original = AppAdapter.tileWidthPx(1696,846,6,0,false,true,2f);
        int wider = AppAdapter.executiveTileWidthPx(1696,846,6,0,false,true,2f);
        assertTrue(wider > original);
        assertTrue(wider + 24 <= 1696 / 6);
        assertTrue(wider <= Math.round(original * 1.10f));
    }
    @Test public void otherLayoutsKeepOriginalWidth() {
        assertEquals(AppAdapter.tileWidthPx(1696,846,4,1,true,true,2f), AppAdapter.executiveTileWidthPx(1696,846,4,1,true,true,2f));
        assertEquals(AppAdapter.tileWidthPx(1696,846,6,1,false,true,2f), AppAdapter.executiveTileWidthPx(1696,846,6,1,false,true,2f));
        assertEquals(AppAdapter.tileWidthPx(1696,846,6,0,false,false,2f), AppAdapter.executiveTileWidthPx(1696,846,6,0,false,false,2f));
    }
}
