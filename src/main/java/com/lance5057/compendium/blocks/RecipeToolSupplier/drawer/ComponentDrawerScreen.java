package com.lance5057.compendium.blocks.RecipeToolSupplier.drawer;

import com.lance5057.compendium.Compendium;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ComponentDrawerScreen extends AbstractContainerScreen<ComponentDrawerMenu> {
	private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
			"textures/gui/component_drawer.png");

	public ComponentDrawerScreen(ComponentDrawerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
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
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		if (this.minecraft == null)
			return;

		RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
		graphics.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos - 19, 0, 0, 205, 193);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
//		guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY + 8,
//				4210752, false);
	}
}
