package com.lance5057.compendium.workstations.workbench;

import com.lance5057.compendium.Compendium;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class WorkbenchScreen extends AbstractContainerScreen<WorkbenchMenu> {
	private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
			"textures/gui/workbench.png");

	public WorkbenchScreen(WorkbenchMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);

	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void render(GuiGraphics gui, final int mouseX, final int mouseY, float partialTicks) {
		super.render(gui, mouseX, mouseY, partialTicks);

		this.renderTooltip(gui, mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics graphics, float pPartialTick, int pMouseX, int pMouseY) {
//		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		if (this.minecraft == null)
			return;

		RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
		graphics.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos - 19, 0, 0, 205, 193);

		if (this.menu.blockEntity.gridLevel == 3) {
			for (int i = 0; i < 3; i++)
				graphics.blit(BACKGROUND_TEXTURE, this.leftPos + 30, this.topPos + 6 + (i * 18), 7, 110, 54, 18);
		}

		if (this.menu.blockEntity.gridLevel == 4) {
			for (int i = 0; i < 4; i++)
				graphics.blit(BACKGROUND_TEXTURE, this.leftPos + 30 - 18, this.topPos + 6 - 18 + (i * 18), 7, 110, 72,
						18);
		}
		
		if (this.menu.blockEntity.gridLevel == 5) {
			for (int i = 0; i < 5; i++)
				graphics.blit(BACKGROUND_TEXTURE, this.leftPos + 30 - 18, this.topPos + 6 - 18 + (i * 18), 7, 110, 90,
						18);
		}
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
//        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
		guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY+8,
				4210752, false);
	}
}
