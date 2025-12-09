package com.lance5057.compendium.index;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.index.material.base._MaterialBase;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CompendiumIndex {
	public static enum Generate {
		IGNORE, GENERATE, EXISTS
	};

	public static List<IIndexEntry> index = new ArrayList<IIndexEntry>();

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Compendium.MOD_ID);
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Compendium.MOD_ID);
	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister
			.create(Registries.ARMOR_MATERIAL, Compendium.MOD_ID);

	public static enum MATERIAL_TYPES {
		METAL, WOOD, GEM, GLASS, TEXTILE, CERAMIC, STONE
	}

	public static String getDefaultMaterialFromType(MATERIAL_TYPES type) {
		switch (type) {
		case METAL:
			return "iron";
		case WOOD:
			return "oak";
		case GEM:
			return "diamond";
		case GLASS:
			return "clear";
		case TEXTILE:
			return "white_wool";
		case CERAMIC:
			return "terracotta";
		case STONE:
			return "stone";
		default:
			return "oak";
		}
	}

	public static void setup(IEventBus bus) {

		Collections.sort(CompendiumIndex.index, new Comparator<IIndexEntry>() {

			@Override
			public int compare(IIndexEntry i, IIndexEntry o) {
				if (i instanceof _MaterialBase mb1) {
					if (o instanceof _MaterialBase mb2) {
						if (mb1.getType() == mb2.getType()) {
							return mb1.name.compareTo(mb2.name);
						} else if (mb1.getType().ordinal() > mb2.getType().ordinal())
							return 1;
						else
							return -1;
					} else
						return 1;
				} else if (o instanceof _MaterialBase)
					return -1;
				return 0;
			}

		});

		index.forEach(i -> i.setup());

		ITEMS.register(bus);
		BLOCKS.register(bus);
		ARMOR_MATERIALS.register(bus);
	}

	public static BigInteger generateChecksum() throws IOException, NoSuchAlgorithmException, NotSerializableException {
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(index);
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(baos.toByteArray());
			return new BigInteger(1, md.digest());
		} catch (NotSerializableException e) {
			Compendium.LOGGER
					.error("Unserializeable Object! Either make it implement Serializable or mark it transient!");
			return new BigInteger("0");
		} finally {
			oos.close();
			baos.close();
		}
	}

	public static boolean isIndexItem(ItemStack stack) {
		for (IIndexEntry i : index)
			if (i.isIndexItem(stack))
				return true;
		
		Compendium.LOGGER.warn(stack.toString() + " not a valid index item!");
		return false;
	}

	public static boolean isIndexItem(ItemStack stack, List<MATERIAL_TYPES> types) {
		for (IIndexEntry i : index)
			if (i instanceof _MaterialBase mb)
				if (types.contains(mb.getType()))
					if (i.isIndexItem(stack))
						return true;
		
		Compendium.LOGGER.warn(stack.toString() + " not a valid index item!");
		return false;
	}

	public static Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack) {
		for (IIndexEntry i : index) {
			Optional<IIndexEntry> o = i.getEntryItemBelongsTo(stack);
			if (o.isPresent())
				return o;
		}
		return Optional.empty();
	}

	public static Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack, List<MATERIAL_TYPES> types) {
		for (IIndexEntry i : index)
			if (i instanceof _MaterialBase mb)
				if (types.contains(mb.getType())) {
					Optional<IIndexEntry> o = i.getEntryItemBelongsTo(stack);
					if (o.isPresent())
						return o;
				}
		return Optional.empty();
	}
}
