package com.gathin.tvhome;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExecutiveTileFitTest {
    @Test public void sixAppDockUsesSlimArtworkHeight() {
        assertEquals(126, AppAdapter.executiveTileHeightPx(270, 6, 0, true));
        assertEquals(84, AppAdapter.executiveTileHeightPx(180, 6, 0, true));
    }
    @Test public void otherLayoutsRetainUpstreamHeight() {
        assertEquals(152, AppAdapter.tileHeightPx(270));
        assertEquals(152, AppAdapter.executiveTileHeightPx(270, 4, 0, true));
        assertEquals(152, AppAdapter.executiveTileHeightPx(270, 6, 1, true));
        assertEquals(152, AppAdapter.executiveTileHeightPx(270, 6, 0, false));
    }
}
