package com.lance5057.compendium.styleblock;

import com.lance5057.compendium.Compendium;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Block;

public class StyleBlockScreen extends AbstractContainerScreen<StyleBlockMenu> {
	private BlockPos pos = BlockPos.ZERO;

	private static ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
			"textures/gui/style.png");
	private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation
			.withDefaultNamespace("container/stonecutter/scroller");
	private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation
			.withDefaultNamespace("container/stonecutter/scroller_disabled");
	private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation
			.withDefaultNamespace("container/stonecutter/recipe_selected");
	private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = ResourceLocation
			.withDefaultNamespace("container/stonecutter/recipe_highlighted");
	private static final ResourceLocation RECIPE_SPRITE = ResourceLocation
			.withDefaultNamespace("container/stonecutter/recipe");

	private float scrollOffs;
	private boolean scrolling;
	private int startIndex;

	private BlockPos pos = BlockPos.ZERO;
	private StyleBlock style;

	public StyleBlockScreen(StyleBlockMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}

	@Override
	protected void renderBg(GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		if (this.minecraft == null)
			return;

		BlockState state = this.minecraft.level.getBlockState(this.pos);
		if (state != null) {
			if (state.getBlock() instanceof StyleBlock style) {
				int i = this.leftPos + 8;
				int j = this.topPos + 36;

				RenderSystem.setShaderTexture(0, BACKGROUND);
				gui.blit(BACKGROUND, i, j, 0, 0, 176, 256);
			}
		}
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
	}

	void renderBlock(GuiGraphics guiGraphics, BlockState state) {
		guiGraphics.pose().pushPose();

		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
		guiGraphics.pose().translate(0, 0, 0);

		MultiBufferSource.BufferSource buffers = Minecraft.getInstance().renderBuffers().bufferSource();

		guiGraphics.pose().pushPose();
		guiGraphics.pose().translate(0, 0.5, 0);
		guiGraphics.pose().scale(1f, -1f, 1f);

		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, guiGraphics.pose(), buffers, 0xffffff,
				OverlayTexture.NO_OVERLAY);

		buffers.endBatch();

		guiGraphics.pose().popPose();
		guiGraphics.pose().popPose();
	}

	private void renderButtons(GuiGraphics p_282733_, int p_282136_, int p_282147_, int p_281987_, int p_281276_,
			int p_282688_) {
		for (int i = this.startIndex; i < p_282688_ && i < 0; ++i) {
			int j = i - this.startIndex;
			int k = p_281987_ + j % 4 * 16;
			int l = j / 4;
			int i1 = p_281276_ + l * 18 + 2;
			ResourceLocation resourcelocation;
			if (p_282136_ >= k && p_282147_ >= i1 && p_282136_ < k + 16 && p_282147_ < i1 + 18) {
				resourcelocation = RECIPE_HIGHLIGHTED_SPRITE;
			} else {
				resourcelocation = RECIPE_SPRITE;
			}

			p_282733_.blitSprite(resourcelocation, k, i1 - 1, 16, 18);
		}
	}

//	private boolean isScrollBarActive() {
//		if (style != null)
//			return style.numStyles() > 12;
//		return false;
//	}
		Block block = this.minecraft.level.getBlockState(pos).getBlock();
		if (block instanceof StyleBlock style)
			this.style = style;
	}
}
