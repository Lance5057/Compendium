package lance5057.compendium.indexes;

import lance5057.compendium.indexes.metals.IndexBaseMetals;
import lance5057.compendium.indexes.metals.IndexVanillaMetals;

public class CompendiumIndexes {
    public static IndexBaseMetals baseMetals;
    public static IndexVanillaMetals vanillaMetals;
 
    public CompendiumIndexes()
    {
	baseMetals= new IndexBaseMetals();
	vanillaMetals = new IndexVanillaMetals();
    }
}
