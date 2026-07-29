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
import java.util.Random;

import com.lance5057.compendium.Compendium;
import com.lance5057.compendium.CompendiumComponents;
import com.lance5057.compendium.components.block.IndexEntryComponent;
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

	public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister
			.create(Registries.ARMOR_MATERIAL, Compendium.MOD_ID);

	public static enum MATERIAL_TYPES {
		METAL, WOOD, GEM, GLASS, TEXTILE, CERAMIC, STONE
	}

	public static String getDefaultMaterialFromType(MATERIAL_TYPES type) {
		switch (type) {
		case METAL:
			return findDefault(MATERIAL_TYPES.METAL);
		case WOOD:
			return findDefault(MATERIAL_TYPES.WOOD);
		case GEM:
			return findDefault(MATERIAL_TYPES.GEM);
		case GLASS:
			return findDefault(MATERIAL_TYPES.GLASS);
		case TEXTILE:
			return findDefault(MATERIAL_TYPES.TEXTILE);
		case CERAMIC:
			return findDefault(MATERIAL_TYPES.CERAMIC);
		case STONE:
			return findDefault(MATERIAL_TYPES.STONE);
		default:
			return "";
		}
	}

	private static String findDefault(MATERIAL_TYPES type) {
		List<IIndexEntry> metal = index.stream().filter(i -> {
			if (i instanceof _MaterialBase mb)
				return mb.getType() == type;
			return false;
		}).toList();

		if (metal != null && metal.size() > 0) {
			Random r = new Random();
			return metal.get(r.nextInt(metal.size())).getName();
		}

		Compendium.LOGGER.error("No valid " + type.toString() + " types in index!");
		return "";
	}

	public static List<String> getAllMaterialsForType(List<MATERIAL_TYPES> types) {
		List<String> materials = new ArrayList<String>();

		for (IIndexEntry i : index)
			if (i instanceof _MaterialBase mb)
				if (types.contains(mb.getType()))
					materials.add(mb.name);

		return materials;
	}

	public static void addEntry(IIndexEntry i) {
		index.add(i);
		Compendium.LOGGER.debug("Added Index Entry: " + i.getName());
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

		index.forEach(i -> {
			i.setup();
//			if (i instanceof _MaterialBase mb) {
//				mb.ITEMS.register(bus);
//				mb.BLOCKS.register(bus);
//			}
		});

		ARMOR_MATERIALS.register(bus);
	}

	public static BigInteger checksum = BigInteger.ZERO;

	public static BigInteger generateChecksum() throws IOException, NoSuchAlgorithmException, NotSerializableException {
		if (checksum == BigInteger.ZERO) {
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
				return BigInteger.ZERO;
			} finally {
				oos.close();
				baos.close();
			}
		} else
			return checksum;
	}

	public static boolean isIndexItem(ItemStack stack) {
		if (stack.has(CompendiumComponents.INDEX)) {
			return true;
		}

		return false;
	}

	public static boolean isIndexItem(ItemStack stack, List<MATERIAL_TYPES> types) {
		if (stack.has(CompendiumComponents.INDEX)) {
			IndexEntryComponent i = stack.get(CompendiumComponents.INDEX);

			if (types.contains(i.getType())) {
				return true;
			}
		}
		return false;
	}

	public static Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack) {
		if (stack.has(CompendiumComponents.INDEX)) {
			IndexEntryComponent i = stack.get(CompendiumComponents.INDEX);

			return index.stream().filter(x -> x.getName().equals(i.getName())).findFirst();
		}

//		for (IIndexEntry i : index) {
//			Optional<IIndexEntry> o = i.getEntryItemBelongsTo(stack);
//			if (o.isPresent())
//				return o;
//		}
		return Optional.empty();
	}

//	public static Optional<IIndexEntry> getEntryItemBelongsTo(ItemStack stack, List<MATERIAL_TYPES> types) {
//		for (IIndexEntry i : index)
//			if (i instanceof _MaterialBase mb)
//				if (types.contains(mb.getType())) {
//					Optional<IIndexEntry> o = i.getEntryItemBelongsTo(stack);
//					if (o.isPresent())
//						return o;
//				}
//		return Optional.empty();
//	}
}
