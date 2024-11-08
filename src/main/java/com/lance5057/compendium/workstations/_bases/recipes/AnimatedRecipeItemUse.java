package com.lance5057.compendium.workstations._bases.recipes;

import java.util.List;

import com.lance5057.compendium.client.BlacklistedModel;
import com.lance5057.compendium.recipes.RecipeItemUse;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

public class AnimatedRecipeItemUse extends RecipeItemUse {

	public final List<BlacklistedModel> model;

	public static final AnimatedRecipeItemUse EMPTY = new AnimatedRecipeItemUse(RecipeItemUse.EMPTY,
			BlacklistedModel.empty);

	public AnimatedRecipeItemUse(int uses, Ingredient tool, int count, boolean damage, ResourceLocation loottable,
			List<BlacklistedModel> model) {
		super(uses, tool, count, damage, loottable);

		this.model = model;
	}

	public AnimatedRecipeItemUse(RecipeItemUse riu, BlacklistedModel... model) {
		super(riu.uses, riu.tool, riu.count, riu.damageTool, riu.lootTable);

		this.model = List.of(model);
	}

	public static final Codec<AnimatedRecipeItemUse> CODEC = RecordCodecBuilder
			.create(inst -> inst
					.group(Codec.INT.fieldOf("uses").forGetter(AnimatedRecipeItemUse::getUses),
							Ingredient.CODEC_NONEMPTY.fieldOf("tool").forGetter(AnimatedRecipeItemUse::getTool),
							Codec.INT.fieldOf("count").forGetter(AnimatedRecipeItemUse::getCount),
							Codec.BOOL.fieldOf("damage").forGetter(AnimatedRecipeItemUse::isDamageTool),
							ResourceLocation.CODEC.fieldOf("loot_table").forGetter(AnimatedRecipeItemUse::getLootTable),
							Codec.list(BlacklistedModel.CODEC).fieldOf("models")
									.forGetter(AnimatedRecipeItemUse::getModel))
					.apply(inst, AnimatedRecipeItemUse::new));

	public static final StreamCodec<RegistryFriendlyByteBuf, AnimatedRecipeItemUse> STREAM_CODEC = StreamCodec
			.of(AnimatedRecipeItemUse::write, AnimatedRecipeItemUse::read);

	public List<BlacklistedModel> getModel() {
		return model;
	}

	private static AnimatedRecipeItemUse read(RegistryFriendlyByteBuf buffer) {
		RecipeItemUse riu = RecipeItemUse.STREAM_CODEC.decode(buffer);

		int size = buffer.readInt();

		BlacklistedModel[] b = new BlacklistedModel[size];

		for (int i = 0; i < size; i++)
			b[i] = BlacklistedModel.STREAM_CODEC.decode(buffer);

		return new AnimatedRecipeItemUse(riu, b);
	}

	private static void write(RegistryFriendlyByteBuf buffer, AnimatedRecipeItemUse r) {
		buffer.writeVarInt(r.uses);
		Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, r.tool);
		buffer.writeVarInt(r.count);
		buffer.writeBoolean(r.damageTool);
		buffer.writeResourceLocation(r.lootTable);

		buffer.writeInt(r.model.size());

		for (int i = 0; i < r.model.size(); i++)
			BlacklistedModel.STREAM_CODEC.encode(buffer, r.model.get(i));
	}
}