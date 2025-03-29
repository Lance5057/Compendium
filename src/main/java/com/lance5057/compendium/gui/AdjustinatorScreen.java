package com.lance5057.compendium.gui;

import java.util.function.Consumer;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.gui.AdjustinatorMenu.MODES;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloat;
import com.lance5057.compendium.util.rendering.animation.floats.AnimatedFloatVector3;
import com.lance5057.compendium.util.rendering.animation.floats.AnimationFloatTransform;
import com.lance5057.compendium.workstations._bases.blockentities.MultiToolRecipeStation;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Checkbox;
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

	private BlockPos pos = BlockPos.ZERO;
	private MODES mode = MODES.NONE;

	public void setMode(MODES mode) {
		this.mode = mode;
	}

	// STATION
	AnimatedFloatVector3Widget loc;
	AnimatedFloatVector3Widget rot;
	AnimatedFloatVector3Widget scale;
	AnimatedFloatVector3Widget pivot;

	private MultiToolRecipeStation<?> station;

	// MULTIMATERIAL
	public EditBox box;

	@Override
	public void init() {
		super.init();

		if (this.mode == MODES.STATION) {
			loc = new AnimatedFloatVector3Widget();
			rot = new AnimatedFloatVector3Widget();
			scale = new AnimatedFloatVector3Widget();
			pivot = new AnimatedFloatVector3Widget();

			loc.init(this, 180, -25);
			rot.init(this, 180, 30);
			scale.init(this, 180, 85);
			pivot.init(this, 180, 140);
		} else if (this.mode == MODES.MULTIMATERIAL) {
			box = new EditBox(font, this.leftPos + 100, this.topPos + 100, 40, 14, playerInventoryTitle);
		}
	}

	protected void setup() {
		if (this.mode == MODES.STATION) {
			BlockEntity e = this.minecraft.level.getBlockEntity(pos);
			if (e != null && e instanceof MultiToolRecipeStation)
				station = (MultiToolRecipeStation<?>) e;

			if (station != null)
				if (station.getCurrentTool() != null) {
					AnimationFloatTransform aft = station.getCurrentTool().model().get(0).transform();

				}
		}
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		if (this.mode == MODES.STATION) {
			if (station == null) {
				BlockEntity e = this.minecraft.level.getBlockEntity(pos);
				if (e != null && e instanceof MultiToolRecipeStation) {
					station = (MultiToolRecipeStation<?>) e;

					AnimationFloatTransform aft = station.getCurrentTool().model().get(0).transform();

					loc.set(aft.getLocation());
					rot.set(aft.getRotation());
					scale.set(aft.getScale());
					pivot.set(aft.getPivot());
				}
			} else if (station != null) {
				if (station.getCurrentTool() != null) {
					AnimationFloatTransform aft = station.getCurrentTool().model().get(0).transform();

					aft.setLocation(loc.get());
					aft.setRotation(rot.get());
					aft.setScale(scale.get());
					aft.setPivot(pivot.get());
				}
			}
		}
		else if(this.mode == MODES.MULTIMATERIAL)
		{
			if(box == null)
				box = this.addRenderableWidget(new EditBox(font, this.leftPos + 1, this.topPos + 1, 160, 24, playerInventoryTitle));
		}
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
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

		public void init(AdjustinatorScreen s, int x, int y) {
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

		public void render(AdjustinatorScreen s, GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick,
				String name) {
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

		public void init(AdjustinatorScreen screen, int x, int y) {

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
			af.setMax(max.get());
			af.setMin(min.get());
			af.setSpeed(speed.get());
			af.setOffset(offset.get());

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

		public void init(AdjustinatorScreen screen, int x, int y) {
			box = screen.addRenderableWidget(
					new EditBox(font, screen.leftPos + x, screen.topPos + y, 40, 14, playerInventoryTitle));

			box.setFilter(s -> filter(s));
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

	public void setPos(BlockPos pos2) {
		this.pos = pos2;
	}

}
