package com.lance5057.compendium.workstations.cosmetictoolbox;

import java.util.ArrayList;
import java.util.List;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.blocks.IStyleable;
import com.lance5057.compendium.blocks.entities.MultiMaterialBlockEntity;
import com.lance5057.compendium.client.models.multimaterial.MultiMaterialModelData;
import com.lance5057.compendium.client.models.style.StyleModelData;
import com.lance5057.compendium.network.StyleSetPacket;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.client.model.data.ModelData.Builder;
import net.neoforged.neoforge.network.PacketDistributor;

public class CosmeticToolboxScreen extends AbstractContainerScreen<CosmeticToolboxMenu> {

	private static ResourceLocation BACKGROUND = ResourceLocation.fromNamespaceAndPath(Compendium.MOD_ID,
			"textures/gui/style.png");
	private static final ResourceLocation SCROLLER_SPRITE = ResourceLocation
			.withDefaultNamespace("container/stonecutter/scroller");
	private static final ResourceLocation SCROLLER_DISABLED_SPRITE = ResourceLocation
			.withDefaultNamespace("container/stonecutter/scroller_disabled");
	private static final ResourceLocation RECIPE_SELECTED_SPRITE = ResourceLocation
			.withDefaultNamespace("container/stonecutter/recipe_selected");
	private static final ResourceLocation RECIPE_HIGHLIGHTED_SPRITE = Compendium.modLoc("highlighted_style_recipe_bar");
	private static final ResourceLocation RECIPE_SPRITE = Compendium.modLoc("style_recipe_bar");

	private static final WidgetSprites tab_sprites = new WidgetSprites(Compendium.modLoc("tab"),
			Compendium.modLoc("tab_highlight"));

	private float scrollOffs;
	private boolean scrolling;
	private int startIndex;

	private BlockPos pos = BlockPos.ZERO;
	private IStyleable entity;

	private int curStyleType = 0;
//	private List<Integer> style;

	List<Button> tabs = new ArrayList<Button>();

	public CosmeticToolboxScreen(CosmeticToolboxMenu menu, Inventory playerInventory, Component title) {
		super(menu, playerInventory, title);
	}

	@Override
	protected void renderBg(GuiGraphics gui, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		if (this.minecraft == null)
			return;

		BlockEntity ent = this.minecraft.level.getBlockEntity(pos);
		BlockState state = this.minecraft.level.getBlockState(pos).getBlock().defaultBlockState();
		if (ent != null) {

			int i = this.leftPos + 8;
			int j = this.topPos;

			RenderSystem.setShaderTexture(0, BACKGROUND);
			gui.blit(BACKGROUND, i, j, 0, 0, 176, 256);
//			gui.blit(BACKGROUND, i - 81, j, 175, 0, 81, 81);

			int k = (int) (129.0F * this.scrollOffs);
			ResourceLocation resourcelocation = this.isScrollBarActive() ? SCROLLER_SPRITE : SCROLLER_DISABLED_SPRITE;
			gui.blitSprite(resourcelocation, i + 155, j + k + 8, 12, 15);

			if (ent instanceof IStyleable ssbe) {
				int j1 = this.startIndex + 8;
				this.renderButtons(gui, mouseX, mouseY, i + 8, j + 7, j1);

				RenderSystem.enableBlend();
				RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
				Lighting.setupForFlatItems();

				gui.pose().pushPose();
				{
					renderRecipes(gui, 24, 13, j1, state);
				}
				gui.pose().popPose();

				gui.pose().pushPose();
				{
					gui.pose().translate(this.leftPos - 32, this.topPos + 26, 100);
					gui.pose().scale(40F, 40F, 40F);

					gui.pose().mulPose(Axis.XP.rotationDegrees(-30F));
					gui.pose().mulPose(Axis.YP.rotationDegrees(-45F));

					renderBlock(gui, state, -1);

				}
				gui.pose().popPose();
			}
		}

	}

	private void renderRecipes(GuiGraphics gui, int p_282658_, int p_282563_, int p_283352_, BlockState state) {

		for (int i = this.startIndex; i < p_283352_ && i < entity.getStyles().get(this.curStyleType).size(); ++i) {
			int j = i - this.startIndex;
			int k = this.leftPos + p_282658_;
//			int l = j / 4;
			int i1 = this.topPos + p_282563_ + j * 18;

			gui.pose().pushPose();
			{
				gui.pose().translate(k, i1 + 0.5f, 100);
				float scale = 8.5f;
				gui.pose().scale(scale, scale, scale);

				gui.pose().mulPose(Axis.XP.rotationDegrees(-30F));
				gui.pose().mulPose(Axis.YP.rotationDegrees(-45F));

				renderBlock(gui, state, i);

			}
			gui.pose().popPose();
//			MutableComponent textEmpty = Component.translatable(Compendium.MOD_ID + ".tooltip." + style.get(curStyleType).getCurrentStyle());
			gui.drawString(this.font, Component.translatable(entity.getStyles().get(curStyleType).get(i)), k + 10, i1,
					0xFFFFFF, true);
		}
	}

	private boolean isScrollBarActive() {
		if (entity != null)
			return entity.getStyles().get(curStyleType).size() > 8;
		return false;
	}

	public void setPos(BlockPos pos) {
		this.pos = pos;
		BlockEntity block = this.minecraft.level.getBlockEntity(pos);
		if (block instanceof IStyleable s)
			this.entity = s;

		if (entity.getStyles().size() > 1)
			for (int i = 0; i < entity.getStyles().size(); i++) {
				int j = i;
				tabs.add(this.addRenderableWidget(
						new ImageButton(this.leftPos + 184, this.topPos + 4 + (i * 32), 43, 32, tab_sprites, b -> {
							this.curStyleType = j;
							this.startIndex = 0;
							this.scrollOffs = 0;
						})));
			}
	}

	void renderBlock(GuiGraphics guiGraphics, BlockState state, int cur) {
		guiGraphics.pose().pushPose();

		RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
		guiGraphics.pose().translate(0, 0, 0);

		MultiBufferSource.BufferSource buffers = Minecraft.getInstance().renderBuffers().bufferSource();

		guiGraphics.pose().pushPose();
		guiGraphics.pose().translate(0, 0.5, 0);
		guiGraphics.pose().scale(1f, -1f, 1f);

//		List<String> t = entity.getCurrentAllString();
//		if (cur != -1)
//			t.set(curStyleType, entity.getStyles().get(curStyleType).get(cur));

		Builder md = ModelData.builder();

		List<String> l = entity.getCurrentAllString();
		if (cur != -1)
			l.set(curStyleType, entity.getStyles().get(curStyleType).get(cur));
		md.with(StyleModelData.STYLES, l);

		if (entity instanceof MultiMaterialBlockEntity mmb) {
			md.with(MultiMaterialModelData.STATE, mmb.getMaterials());
		}

		Minecraft.getInstance().getBlockRenderer().renderSingleBlock(state, guiGraphics.pose(), buffers, 255,
				OverlayTexture.NO_OVERLAY, md.build(), null);

		buffers.endBatch();

		guiGraphics.pose().popPose();
		guiGraphics.pose().popPose();
	}

	private void renderButtons(GuiGraphics p_282733_, int p_282136_, int p_282147_, int p_281987_, int p_281276_,
			int p_282688_) {
		if (entity != null)
			for (int i = this.startIndex; i < p_282688_ && i < entity.getStyles().get(curStyleType).size(); ++i) {
				int j = i - this.startIndex;
				int k = p_281987_;
//			int l = j / 4;
				int i1 = p_281276_ + j * 18 + 2;
				ResourceLocation resourcelocation;
				if (p_282136_ >= k && p_282147_ >= i1 && p_282136_ < k + 145 && p_282147_ < i1 + 18) {
					resourcelocation = RECIPE_HIGHLIGHTED_SPRITE;
				} else {
					resourcelocation = RECIPE_SPRITE;
				}

				p_282733_.blitSprite(resourcelocation, k, i1 - 1, 145, 18);

			}
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
	}

	@Override
	public boolean mouseClicked(double p_99318_, double p_99319_, int p_99320_) {
		this.scrolling = false;
		int i = this.leftPos + 14;
		int j = this.topPos + 9;
		int k = this.startIndex + 8;

		for (int l = this.startIndex; l < k; ++l) {
			int i1 = l - this.startIndex;
			double d0 = p_99318_ - (double) (i);
			double d1 = p_99319_ - (double) (j + i1 * 18);
			if (d0 >= 0.0 && d1 >= 0.0 && d0 < 145.0 && d1 < 18.0) {
				entity.setCurrent(this.curStyleType, l);
				Minecraft.getInstance().getSoundManager()
						.play(SimpleSoundInstance.forUI(SoundEvents.MAGMA_CUBE_SQUISH, 1.0F));
//				this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, l);
				PacketDistributor.sendToServer(new StyleSetPacket(this.menu.containerId, this.curStyleType, l));
				return true;
			}
		}

		i = this.leftPos + 155 + 6;
		j = this.topPos + 9;
		if (p_99318_ >= (double) i && p_99318_ < (double) (i + 12) && p_99319_ >= (double) j
				&& p_99319_ < (double) (j + 144)) {
			this.scrolling = true;
		}

		return super.mouseClicked(p_99318_, p_99319_, p_99320_);
	}

	@Override
	public boolean mouseDragged(double p_99322_, double p_99323_, int p_99324_, double p_99325_, double p_99326_) {
		if (this.scrolling && this.isScrollBarActive()) {
			int i = this.topPos + 14;
			int j = i + 144;
			this.scrollOffs = ((float) p_99323_ - (float) i - 7.5F) / ((float) (j - i) - 15.0F);
			this.scrollOffs = Mth.clamp(this.scrollOffs, 0.0F, 1.0F);
			this.startIndex = (int) ((double) (this.scrollOffs * (float) this.getOffscreenRows()) + 0.5);
			return true;
		} else {
			return super.mouseDragged(p_99322_, p_99323_, p_99324_, p_99325_, p_99326_);
		}
	}

	@Override
	public boolean mouseScrolled(double p_99314_, double p_99315_, double p_99316_, double p_295672_) {
		if (this.isScrollBarActive()) {
			int i = this.getOffscreenRows();
			float f = (float) p_295672_ / (float) i;
			this.scrollOffs = Mth.clamp(this.scrollOffs - f, 0.0F, 1.0F);
			this.startIndex = (int) ((this.scrollOffs * (float) i) + 0.5);
		}

		return true;
	}

	protected int getOffscreenRows() {
		return entity.getStyles().get(curStyleType).size() - 8;
	}
}
