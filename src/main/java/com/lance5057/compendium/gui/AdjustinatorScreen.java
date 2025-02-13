package com.lance5057.compendium.gui;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AdjustinatorScreen extends AbstractContainerScreen<AdjustinatorMenu> {

	public AdjustinatorScreen(AdjustinatorMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}

	Vector3Widget sloc = new Vector3Widget();
	Vector3Widget srot = new Vector3Widget();
	Vector3Widget sscale = new Vector3Widget();
	Vector3Widget spivot = new Vector3Widget();

	Vector3Widget eloc = new Vector3Widget();
	Vector3Widget erot = new Vector3Widget();
	Vector3Widget escale = new Vector3Widget();
	Vector3Widget epivot = new Vector3Widget();

	private BlockPos pos = BlockPos.ZERO;
	private MultiToolRecipeStation<?> station;

	@Override
	public void init() {
		super.init();

		BlockEntity e = this.minecraft.level.getBlockEntity(pos);
		if (e != null && e instanceof MultiToolRecipeStation)
			station = (MultiToolRecipeStation<?>) e;
		
		if (station.getCurrentTool() != null) {
			AnimationFloatTransform aft = station.getCurrentTool().model().get(0).transform();

			sloc.init(this, aft.getLocation(), 180, -25, false);
			srot.init(this, aft.getRotation(), 180, 30, false);
			sscale.init(this, aft.getScale(), 180, 85, false);
			spivot.init(this, aft.getPivot(), 180, 140, false);

			eloc.init(this, aft.getLocation(), 240, -25, true);
			erot.init(this, aft.getRotation(), 240, 30, true);
			escale.init(this, aft.getScale(), 240, 85, true);
			epivot.init(this, aft.getPivot(), 240, 140, true);
		}

		

	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {

		sloc.render(this, guiGraphics, 178, -34, partialTick, "Loc Start");
		srot.render(this, guiGraphics, 178, 21, partialTick, "Rot Start");
		sscale.render(this, guiGraphics, 174, 76, partialTick, "Scale Start");
		spivot.render(this, guiGraphics, 174, 131, partialTick, "Pivot Start");

		eloc.render(this, guiGraphics, 240, -34, partialTick, "Loc End");
		erot.render(this, guiGraphics, 241, 21, partialTick, "Rot End");
		escale.render(this, guiGraphics, 236, 76, partialTick, "Scale End");
		epivot.render(this, guiGraphics, 238, 131, partialTick, "Pivot End");
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	protected class Vector3Widget {
		public FloatWidget X = new FloatWidget();
		public FloatWidget Y = new FloatWidget();
		public FloatWidget Z = new FloatWidget();

		public void init(AdjustinatorScreen s, AnimatedFloatVector3 v, int x, int y, boolean isMax) {
			X.init(s, v.getX(), x, y, isMax);
			Y.init(s, v.getY(), x, y + 15, isMax);
			Z.init(s, v.getZ(), x, y + 15 + 15, isMax);
		}

		public void set(AnimatedFloatVector3 v, boolean isMax) {
			X.set(v.getX(), isMax);
			Y.set(v.getY(), isMax);
			Z.set(v.getZ(), isMax);
		}

		public void render(AdjustinatorScreen s, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick,
				String name) {
			guiGraphics.drawString(s.font, name, s.leftPos + mouseX, s.topPos + mouseY, 0xFFFFFF, true);
		}
	}

	protected class FloatWidget {
		private static final WidgetSprites RIGHT_SMALL_BUTTON = new WidgetSprites(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "right_arrow"),
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "right_arrow_disabled"));
		private static final WidgetSprites LEFT_SMALL_BUTTON = new WidgetSprites(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_arrow"),
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_arrow_disabled"));

		public EditBox box;
		public ImageButton button;

		public void init(AdjustinatorScreen screen, AnimatedFloat f, int x, int y, boolean isMax) {
			box = screen.addRenderableWidget(
					new EditBox(font, screen.leftPos + x, screen.topPos + y, 40, 14, playerInventoryTitle));

			box.setFilter(s -> filter(s));

			button = screen.addRenderableWidget(new ImageButton(screen.leftPos + 41 + x, screen.topPos + y + 2, 6, 10,
					RIGHT_SMALL_BUTTON, (button) -> addSmall()));
			button = screen.addRenderableWidget(new ImageButton(screen.leftPos + x - 7, screen.topPos + y + 2, 6, 10,
					LEFT_SMALL_BUTTON, (button) -> addSmall()));

			set(f, isMax);
		}

		public void set(AnimatedFloat f, boolean isMax) {
			if (isMax)
				box.setValue(Float.toString(f.getMax()));
			else
				box.setValue(Float.toString(f.getMin()));
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
