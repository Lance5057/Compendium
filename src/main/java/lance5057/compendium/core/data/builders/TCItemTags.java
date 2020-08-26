package lance5057.compendium.core.data.builders;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.Tag;

public class TCItemTags extends ItemTagsProvider {
	public static List<Pair<Tag<Item>, Item>> ItemTags = new ArrayList<Pair<Tag<Item>, Item>>();
	public static List<Pair<Tag<Item>, Tag<Item>>> NestedTags = new ArrayList<Pair<Tag<Item>, Tag<Item>>>();

	public TCItemTags(DataGenerator generatorIn) {
		super(generatorIn);
	}
	
	@Override
	protected void registerTags() {
		for(Pair<Tag<Item>, Item> p : ItemTags)
		{
			getBuilder(p.getKey()).add(p.getValue());
		}
		
		for(Pair<Tag<Item>, Tag<Item>> p : NestedTags)
		{
			getBuilder(p.getKey()).add(p.getValue());
		}
	}

}
