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
import net.minecraft.world.phys.Vec3;

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

	Vector3Widget speedLoc = new Vector3Widget();
	Vector3Widget speedrot = new Vector3Widget();
	Vector3Widget speedscale = new Vector3Widget();
	Vector3Widget speedpivot = new Vector3Widget();

	private BlockPos pos = BlockPos.ZERO;
	private MultiToolRecipeStation<?> station;

	@Override
	public void init() {
		super.init();

		sloc.init(this, 180, -25);
		srot.init(this, 180, 30);
		sscale.init(this, 180, 85);
		spivot.init(this, 180, 140);

		eloc.init(this, 260, -25);
		erot.init(this, 260, 30);
		escale.init(this, 260, 85);
		epivot.init(this, 260, 140);

		speedLoc.init(this, 340, -25);
		speedrot.init(this, 340, 30);
		speedscale.init(this, 340, 85);
		speedpivot.init(this, 340, 140);
	}

	protected void setup() {
		BlockEntity e = this.minecraft.level.getBlockEntity(pos);
		if (e != null && e instanceof MultiToolRecipeStation)
			station = (MultiToolRecipeStation<?>) e;

		if (station.getCurrentTool() != null) {
			AnimationFloatTransform aft = station.getCurrentTool().model().get(0).transform();

			sloc.set(aft.getLocation(), false);
			srot.set(aft.getRotation(), false);
			sscale.set(aft.getScale(), false);
			spivot.set(aft.getPivot(), false);

			eloc.set(aft.getLocation(), true);
			erot.set(aft.getRotation(), true);
			escale.set(aft.getScale(), true);
			epivot.set(aft.getPivot(), true);

			speedLoc.set(aft.getLocation().getSpeed());
			speedrot.set(aft.getRotation().getSpeed());
			speedscale.set(aft.getScale().getSpeed());
			speedpivot.set(aft.getPivot().getSpeed());
		}
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
		if (this.station == null)
			setup();

		if (station != null) {
			if (station.getCurrentTool() != null) {
				AnimationFloatTransform aft = station.getCurrentTool().model().get(0).transform();

				aft.setLocation(setVector(aft.getLocation(), sloc.get(false), eloc.get(true)));
				aft.setRotation(setVector(aft.getRotation(), srot.get(false), erot.get(true)));
				aft.setScale(setVector(aft.getScale(), sscale.get(false), escale.get(true)));
				aft.setPivot(setVector(aft.getPivot(), spivot.get(false), epivot.get(true)));

				aft.getLocation().setSpeed(speedLoc.X.get(), speedLoc.Y.get(), speedLoc.Z.get());
				aft.getRotation().setSpeed(speedrot.X.get(), speedrot.Y.get(), speedrot.Z.get());
				aft.getScale().setSpeed(speedscale.X.get(), speedscale.Y.get(), speedscale.Z.get());
				aft.getPivot().setSpeed(speedpivot.X.get(), speedpivot.Y.get(), speedpivot.Z.get());
			}
		}

		sloc.render(this, guiGraphics, 178, -34, partialTick, "Loc Start");
		srot.render(this, guiGraphics, 178, 21, partialTick, "Rot Start");
		sscale.render(this, guiGraphics, 174, 76, partialTick, "Scale Start");
		spivot.render(this, guiGraphics, 174, 131, partialTick, "Pivot Start");

		eloc.render(this, guiGraphics, 260, -34, partialTick, "Loc End");
		erot.render(this, guiGraphics, 261, 21, partialTick, "Rot End");
		escale.render(this, guiGraphics, 256, 76, partialTick, "Scale End");
		epivot.render(this, guiGraphics, 258, 131, partialTick, "Pivot End");
	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	AnimatedFloatVector3 setVector(AnimatedFloatVector3 v, Vec3 min, Vec3 max) {
		v.setX(setFloat(v.getX(), (float) min.x, (float) max.x));
		v.setY(setFloat(v.getY(), (float) min.y, (float) max.y));
		v.setZ(setFloat(v.getZ(), (float) min.z, (float) max.z));
		return v;
	}

	AnimatedFloat setFloat(AnimatedFloat af, float min, float max) {
		af.setMax(max);
		af.setMin(min);
		return af;
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

		public void set(AnimatedFloatVector3 v, boolean isMax) {
			X.set(v.getX(), isMax);
			Y.set(v.getY(), isMax);
			Z.set(v.getZ(), isMax);
		}

		public void set(Vec3 v) {
			X.set((float) v.x);
			Y.set((float) v.y);
			Z.set((float) v.z);
		}

		public Vec3 get(boolean isMax) {
			return new Vec3(X.get(), Y.get(), Z.get());
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

		public void init(AdjustinatorScreen screen, int x, int y) {
			box = screen.addRenderableWidget(
					new EditBox(font, screen.leftPos + x, screen.topPos + y, 40, 14, playerInventoryTitle));

			box.setFilter(s -> filter(s));

			right_small_button = screen.addRenderableWidget(new ImageButton(screen.leftPos + 41 + x,
					screen.topPos + y + 2, 6, 10, RIGHT_SMALL_BUTTON, (button) -> addSmall()));
			left_small_button = screen.addRenderableWidget(new ImageButton(screen.leftPos + x - 7,
					screen.topPos + y + 2, 6, 10, LEFT_SMALL_BUTTON, (button) -> subSmall()));
			right_big_button = screen.addRenderableWidget(new ImageButton(screen.leftPos + 41 + 7 + x,
					screen.topPos + y + 2, 11, 10, RIGHT_BIG_BUTTON, (button) -> addBig()));
			left_big_button = screen.addRenderableWidget(new ImageButton(screen.leftPos + x - 19, screen.topPos + y + 2,
					11, 10, LEFT_BIG_BUTTON, (button) -> subBig()));
		}

		public void set(AnimatedFloat f, boolean isMax) {
			if (isMax)
				box.setValue(Float.toString(f.getMax()));
			else
				box.setValue(Float.toString(f.getMin()));
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
