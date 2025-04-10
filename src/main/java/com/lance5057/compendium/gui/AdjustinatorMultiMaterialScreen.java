package com.lance5057.compendium.gui;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.level.block.entity.BlockEntity;

public class AdjustinatorMultiMaterialScreen extends AbstractContainerScreen<AdjustinatorMultiMaterialMenu> {
	private BlockPos pos = BlockPos.ZERO;

	public AdjustinatorMultiMaterialScreen(AdjustinatorMultiMaterialMenu menu, Inventory playerInventory,
			Component title) {
		super(menu, playerInventory, title);

	}

	// MULTIMATERIAL
	public List<EditBox> boxes = new ArrayList<EditBox>();
	private MultiMaterialBlockEntity station;

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {

		if (boxes == null) {

		}

	}

	@Override
	public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
		this.renderBg(guiGraphics, partialTick, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
		BlockEntity e = this.minecraft.level.getBlockEntity(this.pos);
		if (e != null) {
			if (e instanceof MultiMaterialBlockEntity mtrs) {
				station = mtrs;

				for (int i = 0; i < mtrs.getMaterialsCount(); i++) {
					EditBox b = addRenderableWidget(new EditBox(font, this.leftPos + 1, this.topPos + 1 + (25 * i), 160,
							24, playerInventoryTitle));

					List<String> mats = mtrs.getMaterials();

					if (mats != null && mats.size() > i) {
						b.insertText(mats.get(i));
					}

					final int index = i;
					b.setResponder(s -> setMaterialFromBox(index, s, mtrs));

					boxes.add(b);
				}
			}
		}

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

	private void setMaterialFromBox(int index, String s, MultiMaterialBlockEntity mmbe) {
		mmbe.setMaterial(index, s);
	}

	@Override
	public void onClose() {
		this.menu.markDirty();
//		this.minecraft.level.blockEntityChanged(pos);
		this.minecraft.player.closeContainer();
		super.onClose();
	}

}
