package lance5057.compendium.core.workstations.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import lance5057.compendium.Reference;
import lance5057.compendium.core.workstations.containers.CraftingAnvilContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CraftingAnvilScreen extends AbstractContainerScreen<CraftingAnvilContainer> {

	private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(Reference.MOD_ID,
			"textures/gui/crafting_anvil.png");

	public CraftingAnvilScreen(CraftingAnvilContainer screenContainer, Inventory inv, Component titleIn) {
		super(screenContainer, inv, titleIn);
		this.imageWidth = 176;
		this.imageHeight = 193;

		this.titleLabelX = 110;

		this.inventoryLabelY = 100;
	}

	@Override
	protected void renderBg(PoseStack matrixStack, float partialTicks, int x, int y) {
		RenderSystem.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindForSetup(BACKGROUND_TEXTURE);

		int edgeSpacingX = (this.width - this.getXSize()) / 2;
		int edgeSpacingY = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, edgeSpacingX, edgeSpacingY, 0, 0, this.getXSize(), this.imageHeight);
	}

//	@Override
//	protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
//		super.drawGuiContainerForegroundLayer(matrixStack, x, y);
//		
//		//this.font.drawString(matrixStack, ""+this.container.strikes + "/" +this.container.maxStrikes, 118, 34, TextFormatting.BLACK.getColor());
//	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}
}
