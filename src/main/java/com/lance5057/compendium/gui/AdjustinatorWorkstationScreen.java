package com.lance5057.compendium.gui;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AdjustinatorWorkstationScreen extends AbstractContainerScreen<AdjustinatorWorkstationMenu> {

	public AdjustinatorWorkstationScreen(AdjustinatorWorkstationMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}

	private BlockPos pos = BlockPos.ZERO;

	// STATION
	AnimatedFloatVector3Widget loc;
	AnimatedFloatVector3Widget rot;
	AnimatedFloatVector3Widget scale;
	AnimatedFloatVector3Widget pivot;

	private MultiToolRecipeStation<?> station;

	// Copy datagen/json to clipboard!

	// MULTIMATERIAL
	public List<EditBox> boxes = new ArrayList<EditBox>();

	EditBox indexBox;
	Consumer<String> onChanged;

	public ImageButton right_index_button;
	public ImageButton left_index_button;

	PlainTextButton dataToClipboard;

	private static final WidgetSprites RIGHT_SMALL_BUTTON = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "right_arrow"),
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "right_arrow_disabled"));
	private static final WidgetSprites LEFT_SMALL_BUTTON = new WidgetSprites(
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_arrow"),
			ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_arrow_disabled"));

	AnimationFloatTransform aft;

	@Override
	protected void init() {
		super.init();

		indexBox = this.addRenderableWidget(
				new EditBox(font, this.leftPos - 160, this.topPos + 0, 40, 14, Component.literal("")));
		indexBox.setValue("0");
		indexBox.setFilter(s -> {
			int i = 0;
			try {
				i = Integer.parseInt(s);
			} catch (NumberFormatException exception) {
				return false;
			}

			if (i < 0) {
				return false;
			} else if (i >= station.getCurrentTool().model().size()) {
				return false;
			}

			return true;
		});

		right_index_button = this.addRenderableWidget(new ImageButton(this.leftPos + 41 - 160, this.topPos + 2, 6, 10,
				RIGHT_SMALL_BUTTON, (button) -> button(1)));
		left_index_button = this.addRenderableWidget(new ImageButton(this.leftPos - 7 - 160, this.topPos + 2, 6, 10,
				LEFT_SMALL_BUTTON, (button) -> button(-1)));

		dataToClipboard = this.addRenderableWidget(new PlainTextButton(this.leftPos - 200, this.topPos + 40, 80, 14,
				Component.literal("Export Data to Clipboard"), b -> saveDataGenToClipboard(), font));
	}

	private void button(int i) {
		if (station != null)
			if (station.getCurrentTool() != null)
				if (station.getCurrentTool().model() != null && !station.getCurrentTool().model().isEmpty()
						&& station.getCurrentTool().model().size() > Integer.parseInt(indexBox.getValue()) + i)
					indexBox.setValue(String.format("%d", Integer.parseInt(indexBox.getValue()) + i));
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {

		if (station == null) {
			BlockEntity e = this.minecraft.level.getBlockEntity(pos);
			if (e != null && e instanceof MultiToolRecipeStation) {
				station = (MultiToolRecipeStation<?>) e;

//				AnimationFloatTransform aft = station.getCurrentTool().model().get(0).transform();

//				loc.set(aft.getLocation());
//				rot.set(aft.getRotation());
//				scale.set(aft.getScale());
//				pivot.set(aft.getPivot());
			}
		} else if (station.getCurrentTool() != null) {

			int index = 0;
			try {
				index = Integer.parseInt(indexBox.getValue());
			} catch (NumberFormatException exception) {

			}

			aft = station.getCurrentTool().model().get(index).transform();

			loc.set(aft.getLocation());
			rot.set(aft.getRotation());
			scale.set(aft.getScale());
			pivot.set(aft.getPivot());

			loc.render(this, guiGraphics, -65, -10, partialTick, "Location");
			rot.render(this, guiGraphics, -65, 40, partialTick, "Rotation");
			scale.render(this, guiGraphics, -65, 100, partialTick, "Scale");
			pivot.render(this, guiGraphics, -65, 160, partialTick, "Pivot");

			guiGraphics.drawString(font, "Min", leftPos + 10, topPos - 40, 0xFFFFFF, true);
			guiGraphics.drawString(font, "Max", leftPos + 90, topPos - 40, 0xFFFFFF, true);
			guiGraphics.drawString(font, "Speed", leftPos + 160, topPos - 40, 0xFFFFFF, true);
			guiGraphics.drawString(font, "Offset", leftPos + 240, topPos - 40, 0xFFFFFF, true);
			guiGraphics.drawString(font, "Loop", leftPos + 290, topPos - 40, 0xFFFFFF, true);
			guiGraphics.drawString(font, "PingPong", leftPos + 320, topPos - 40, 0xFFFFFF, true);
		}

	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		InputConstants.Key mouseKey = InputConstants.getKey(keyCode, scanCode);
		if (this.minecraft.options.keyInventory.isActiveAndMatches(mouseKey)) {
			return true;
		}
		if (super.keyPressed(keyCode, scanCode, modifiers)) {
			return true;
		} else {
			boolean handled = this.checkHotbarKeyPressed(keyCode, scanCode);// Forge MC-146650: Needs to return true
																			// when the key is handled
			if (this.hoveredSlot != null && this.hoveredSlot.hasItem()) {
				if (this.minecraft.options.keyPickItem.isActiveAndMatches(mouseKey)) {
					this.slotClicked(this.hoveredSlot, this.hoveredSlot.index, 0, ClickType.CLONE);
					handled = true;
				} else if (this.minecraft.options.keyDrop.isActiveAndMatches(mouseKey)) {
					this.slotClicked(this.hoveredSlot, this.hoveredSlot.index, hasControlDown() ? 1 : 0,
							ClickType.THROW);
					handled = true;
				}
			} else if (this.minecraft.options.keyDrop.isActiveAndMatches(mouseKey)) {
				handled = true; // Forge MC-146650: Emulate MC bug, so we don't drop from hotbar when pressing
								// drop without hovering over a item.
			}

			return handled;
		}
	}

	void saveJsonToClipboard() {
		String s = null; // json code here
		Minecraft.getInstance().keyboardHandler.setClipboard(s);
	}

	void saveDataGenToClipboard() {
		String s = this.aft.clipboardData(); // datagen code here
		Minecraft.getInstance().keyboardHandler.setClipboard(s);
	}

	protected class AnimatedFloatVector3Widget {
		AnimatedFloatVector3 afv;

		public AnimatedFloatWidget X;
		public AnimatedFloatWidget Y;
		public AnimatedFloatWidget Z;

		public AnimatedFloatVector3Widget() {

			X = new AnimatedFloatWidget();
			Y = new AnimatedFloatWidget();
			Z = new AnimatedFloatWidget();
		}

		public void init(AdjustinatorWorkstationScreen s, int x, int y) {
			X.init(s, x, y);
			Y.init(s, x, y + 15);
			Z.init(s, x, y + 15 + 15);
		}

		public AnimatedFloatVector3 get() {
			afv.setX(X.get());
			afv.setY(Y.get());
			afv.setZ(Z.get());

			return afv;
		}

		public void set(AnimatedFloatVector3 v) {
			afv = v;

			X.set(v.getX());
			Y.set(v.getY());
			Z.set(v.getZ());
		}

		public void render(AdjustinatorWorkstationScreen s, GuiGraphics guiGraphics, int mouseX, int mouseY,
				float partialTick, String name) {
			guiGraphics.drawString(s.font, name, s.leftPos + mouseX, s.topPos + mouseY, 0xFFFFFF, true);

		}
	}

	protected class AnimatedFloatWidget {

		AnimatedFloat af;

		public FloatWidget min;
		public FloatWidget max;
		public FloatWidget speed;
		public FloatWidget offset;

		public Checkbox loop;
		public Checkbox pingpong;

		public AnimatedFloatWidget() {
			min = new FloatWidget(s -> af.setMin(Float.parseFloat(s)));
			max = new FloatWidget(s -> af.setMax(Float.parseFloat(s)));
			speed = new FloatWidget(s -> af.setSpeed(Float.parseFloat(s)));
			offset = new FloatWidget(s -> af.setOffset(Float.parseFloat(s)));
		}

		public void init(AdjustinatorWorkstationScreen screen, int x, int y) {

			min.init(screen, x, y);
			max.init(screen, x + 80, y);
			speed.init(screen, x + 160, y);
			offset.init(screen, x + 240, y);

			pingpong = screen.addRenderableWidget(
					Checkbox.builder(Component.empty(), screen.font).pos(screen.leftPos + x + 300, screen.topPos + y)
							.onValueChange((a, b) -> af.setPingpong(b)).build());
			loop = screen.addRenderableWidget(Checkbox.builder(Component.empty(), screen.font)
					.pos(screen.leftPos + x + 320, screen.topPos + y).onValueChange((a, b) -> af.setLoop(b)).build());
		}

		public AnimatedFloat get() {
			if (af != null) {
				af.setMax(max.get());
				af.setMin(min.get());
				af.setSpeed(speed.get());
				af.setOffset(offset.get());
			}

			return af;
		}

		public void set(AnimatedFloat a) {
			this.af = a;

			min.set(a.getMin());
			max.set(a.getMax());
			speed.set(a.getSpeed());
			offset.set(a.getOffset());

			if (a.getPingpong()) {
				if (!pingpong.selected())
					pingpong.onPress();
			} else {
				if (pingpong.selected())
					pingpong.onPress();
			}

			if (a.getLoop()) {
				if (!loop.selected())
					loop.onPress();
			} else {
				if (loop.selected())
					loop.onPress();
			}
		}
	}

	protected class FloatWidget {
		public float f = 0;
		Consumer<String> onChanged;

		private static final WidgetSprites RIGHT_SMALL_BUTTON = new WidgetSprites(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "right_arrow"),
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "right_arrow_disabled"));
		private static final WidgetSprites LEFT_SMALL_BUTTON = new WidgetSprites(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_arrow"),
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_arrow_disabled"));

		private static final WidgetSprites RIGHT_BIG_BUTTON = new WidgetSprites(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "right_double_arrow"),
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "right_double_arrow_disabled"));
		private static final WidgetSprites LEFT_BIG_BUTTON = new WidgetSprites(
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_double_arrow"),
				ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID, "left_double_arrow_disabled"));

		public EditBox box;
		public ImageButton right_small_button;
		public ImageButton right_big_button;
		public ImageButton left_small_button;
		public ImageButton left_big_button;

		public FloatWidget(Consumer<String> sup) {
			this.onChanged = sup;
		}

		public void init(AdjustinatorWorkstationScreen screen, int x, int y) {
			box = screen.addRenderableWidget(
					new EditBox(font, screen.leftPos + x, screen.topPos + y, 40, 14, playerInventoryTitle));

//			box.setFilter(s -> filter(s));
			box.setResponder(onChanged);

			right_small_button = screen.addRenderableWidget(new ImageButton(screen.leftPos + 41 + x,
					screen.topPos + y + 2, 6, 10, RIGHT_SMALL_BUTTON, (button) -> addSmall()));
			left_small_button = screen.addRenderableWidget(new ImageButton(screen.leftPos + x - 7,
					screen.topPos + y + 2, 6, 10, LEFT_SMALL_BUTTON, (button) -> subSmall()));
			right_big_button = screen.addRenderableWidget(new ImageButton(screen.leftPos + 41 + 7 + x,
					screen.topPos + y + 2, 11, 10, RIGHT_BIG_BUTTON, (button) -> addBig()));
			left_big_button = screen.addRenderableWidget(new ImageButton(screen.leftPos + x - 19, screen.topPos + y + 2,
					11, 10, LEFT_BIG_BUTTON, (button) -> subBig()));

		}

		public void set(float f) {
			box.setValue(Float.toString(f));
		}

		public float get() {
			if (box != null)
				try {
					Float f = Float.parseFloat(box.getValue());
					return f;
				} catch (Exception e) {

				}
			return 0;
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
			box.setValue(String.format("%.1f", Float.parseFloat(box.getValue()) + 0.1f));
		}

		void subSmall() {
			box.setValue(String.format("%.1f", Float.parseFloat(box.getValue()) - 0.1f));
		}

		void addBig() {
			box.setValue(String.format("%.1f", Float.parseFloat(box.getValue()) + 1f));
		}

		void subBig() {
			box.setValue(String.format("%.1f", Float.parseFloat(box.getValue()) - 1f));
		}
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
		BlockEntity e = this.minecraft.level.getBlockEntity(this.pos);
		if (e != null) {
			if (e instanceof MultiToolRecipeStation mtrs) {
				station = mtrs;

				loc = new AnimatedFloatVector3Widget();
				rot = new AnimatedFloatVector3Widget();
				scale = new AnimatedFloatVector3Widget();
				pivot = new AnimatedFloatVector3Widget();

				loc.init(this, 0, -25);
				rot.init(this, 0, 30);
				scale.init(this, 0, 85);
				pivot.init(this, 0, 140);

				if (station.getCurrentTool() != null) {
					AnimationFloatTransform aft = station.getCurrentTool().model().get(0).transform();

				}
			}
		}
	}

	@Override
	public void onClose() {
//		this.minecraft.level.blockEntityChanged(pos);
		this.minecraft.player.closeContainer();
		super.onClose();
	}

}
