package com.lance5057.compendium.gui;

import com.lance5057.compendium.Compendium;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class AdjustinatorScreen extends AbstractContainerScreen<AdjustinatorMenu> {

	public AdjustinatorScreen(AdjustinatorMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}

	Vector3Widget loc = new Vector3Widget();

	@Override
	public void init() {
		super.init();

		loc.init(this, 140, 0);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
	}

	protected class Vector3Widget {
		public FloatWidget X = new FloatWidget();
		public FloatWidget Y = new FloatWidget();
		public FloatWidget Z = new FloatWidget();

		public void init(AdjustinatorScreen s, int x, int y) {
			X.init(s, x, y);
			Y.init(s, x, y + 15);
			Z.init(s, x, y + 15 + 15);
		}
	}

	protected class FloatWidget {
		private static final WidgetSprites RIGHT_SMALL_BUTTON = new WidgetSprites(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "arrow"),
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "arrow_disabled"));
		private static final WidgetSprites LEFT_SMALL_BUTTON = new WidgetSprites(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_arrow"),
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_arrow_disabled"));

		public EditBox box;
		public ImageButton button;

		public void init(AdjustinatorScreen screen, int x, int y) {
			box = screen.addRenderableWidget(
					new EditBox(font, screen.leftPos + x, screen.topPos + y, 40, 14, playerInventoryTitle));

			box.setFilter(s -> filter(s));

			button = screen.addRenderableWidget(
					new ImageButton(screen.leftPos + 41 + x, screen.topPos + y+2, 6, 10, RIGHT_SMALL_BUTTON, (button) -> addSmall()));
			button = screen.addRenderableWidget(
					new ImageButton(screen.leftPos + x, screen.topPos + y+2, 6, 10, LEFT_SMALL_BUTTON, (button) -> addSmall()));
		}

		boolean filter(String s) {
			try {
				Float.parseFloat(s);
				return true;
			} catch (Exception e) {
				return false;
			}

		}

		void addSmall() {

		}
	}

}
