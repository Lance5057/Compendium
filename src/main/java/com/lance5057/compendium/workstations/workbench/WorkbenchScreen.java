//package com.lance5057.compendium.workstations.workbench;
//
//import com.lance5057.compendium.Compendium;
//import com.mojang.blaze3d.systems.RenderSystem;
//
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.entity.player.Inventory;
//
//public class WorkbenchScreen extends AbstractContainerScreen<WorkbenchMenu> {
//
//	private static final ResourceLocation BACKGROUND_TEXTURE = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
//			"textures/gui/workstation.png");
//
//	public WorkbenchScreen(WorkbenchMenu screenContainer, Inventory inv, Component titleIn) {
//		super(screenContainer, inv, titleIn);
//		this.imageWidth = 205;
//		this.imageHeight = 193;
//
//		this.titleLabelX = 110;
//
//		this.inventoryLabelY = 100;
//	}
//
//	@Override
//	protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
//		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
//		if (this.minecraft == null)
//			return;
//
//		RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
//		pGuiGraphics.blit(BACKGROUND_TEXTURE, this.leftPos, this.topPos + 60, 0, 0, 175, 89);
//	}
//
////	@Override
////	protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
////		RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
////		RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
////
////		int edgeSpacingX = (this.width - this.getXSize()) / 2;
////		int edgeSpacingY = (this.height - this.imageHeight) / 2;
////		this.blit(matrixStack, edgeSpacingX, edgeSpacingY, 0, 0, this.getXSize(), this.imageHeight);
////	}
//
////	@Override
////	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
////		super.drawGuiContainerForegroundLayer(matrixStack, x, y);
////		
////		//this.font.drawString(matrixStack, ""+this.container.strikes + "/" +this.container.maxStrikes, 118, 34, TextFormatting.BLACK.getColor());
////	}
//
////	@Override
////	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
////		this.renderBackground(matrixStack);
////		super.render(matrixStack, mouseX, mouseY, partialTicks);
////		this.renderTooltip(matrixStack, mouseX, mouseY);
////	}
//}