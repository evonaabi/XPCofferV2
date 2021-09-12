package com.regionlocker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import com.google.inject.Inject;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

import static net.runelite.api.MenuAction.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

public class XPCofferOverlay extends OverlayPanel {
    private final RegionLockerConfig config;
    private final RegionLockerPlugin plugin;

    @Inject
    private XPCofferOverlay(RegionLockerPlugin plugin, RegionLockerConfig config)
    {
        super(plugin);
        setPosition(OverlayPosition.TOP_RIGHT);
        this.plugin = plugin;
        this.config = config;
        getMenuEntries().add(new OverlayMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "XP Coffer overlay"));
    }

    private String FormatXP(long xp)
    {
        String xpString = xp + "";
        String formatted = xp + "";
        for (int i = 3; i < xpString.length(); i += 3)
        {
            formatted = formatted.substring(0, xpString.length()-i) + "," + formatted.substring(xpString.length()-i);
        }
        return formatted;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        boolean testbool = true;

        if (config.DrawXPCoffer())
        {
            String boxtext = "Coffer: " + FormatXP(plugin.cofferXP);

            panelComponent.getChildren().add(TitleComponent.builder()
                    .text(boxtext)
                    .color(Color.WHITE)
                    .build());

            panelComponent.setPreferredSize(new Dimension(
                    graphics.getFontMetrics().stringWidth(boxtext) + 10,
                    0));

            return super.render(graphics);
        }

        return null;
    }
}