package com.lance5057.compendium.gui;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.multimaterial.MultiMaterialType;
import com.lance5057.compendium.network.AdjustinatorCallBackPacket;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.PacketDistributor;

public class AdjustinatorMultiMaterialScreen extends AbstractContainerScreen<AdjustinatorMultiMaterialMenu> {
	private BlockPos pos = BlockPos.ZERO;

	public AdjustinatorMultiMaterialScreen(AdjustinatorMultiMaterialMenu menu, Inventory playerInventory,
			Component title) {
		super(menu, playerInventory, title);

	}

	// MULTIMATERIAL
	public List<String> labels = new ArrayList<String>();
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
		if (labels != null) {
			int count = 0;
			for (String s : labels) {
				guiGraphics.drawString(font, s, -80, 10 + (count * 25), 0xFFFFFF, true);
				count++;
			}
		}
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
		BlockEntity e = this.minecraft.level.getBlockEntity(this.pos);
		if (e != null) {
			if (e instanceof MultiMaterialBlockEntity mtrs) {
				station = mtrs;

				for (MultiMaterialType mmt : mtrs.getMaterials()) {
					String s = "";
					for (String st : mmt.getTypeStr())
						s += st + "/";
					labels.add(s);
				}

				for (int i = 0; i < mtrs.getMaterialsCount(); i++) {
					EditBox b = addRenderableWidget(new EditBox(font, this.leftPos + 1, this.topPos + 1 + (25 * i), 160,
							24, playerInventoryTitle));

					List<String> mats = new ArrayList<String>();
					for (MultiMaterialType m : mtrs.getMaterials())
						mats.add(m.getCurrentMaterial());
//					mtrs.getMaterials().forEach(i -> mats.add(i.getCurrentMaterial()));

					if (mats != null && mats.size() > i) {
						b.insertText(mats.get(i));
					}

					final int index = i;
					b.setResponder(s -> setMaterialFromBox(index, s, mtrs));
					b.setFilter(s -> isAlpha(s));

					boxes.add(b);
				}
			}
		}

	}

	private boolean isAlpha(String s) {
		return s.matches("^[a-za_]*$");
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
		PacketDistributor.sendToServer(new AdjustinatorCallBackPacket(index, s, pos));
//		s = this.isAlpha(s);
		mmbe.setMaterial(index, s);
		mmbe.getLevel().sendBlockUpdated(pos, mmbe.getBlockState(), mmbe.getBlockState(), Block.UPDATE_ALL);
	}

//	@Override
//	public void onClose() {
////		String s = "";
////		for (EditBox b : boxes) {
////			s += b.getValue() + ":";
////		}
//		
////		this.minecraft.level.blockEntityChanged(pos);
//		this.minecraft.player.closeContainer();
//		super.onClose();
//	}

}
