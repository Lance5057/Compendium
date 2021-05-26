package lance5057.compendium.indexes;

import lance5057.compendium.indexes.metals.IndexBaseMetals;
import lance5057.compendium.indexes.metals.IndexVanillaMetals;
import lance5057.compendium.indexes.woods.IndexCompendiumWoods;
import lance5057.compendium.indexes.woods.IndexVanillaWoods;

public class CompendiumIndexes {
    public static IndexBaseMetals baseMetals;
    public static IndexVanillaMetals vanillaMetals;
    
    public static IndexCompendiumWoods compWood;
    public static  IndexVanillaWoods vanillaWood;
 
    public CompendiumIndexes()
    {
	vanillaWood = new IndexVanillaWoods();
	compWood = new IndexCompendiumWoods();	
	
	baseMetals= new IndexBaseMetals();
	vanillaMetals = new IndexVanillaMetals();
    }
}
