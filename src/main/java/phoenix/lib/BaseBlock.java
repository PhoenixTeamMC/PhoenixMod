package phoenix.lib;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import phoenix.main.PheonixMod;
import cpw.mods.fml.common.registry.GameRegistry;
import phoenix.main.lib.Reference;

public class BaseBlock extends Block
{
	String blockName;

	protected BaseBlock(String blockName, Material material)
	{
		super(material);
		this.blockName = blockName;
		
		this.setCreativeTab(PheonixMod.creativeTab);
		this.setBlockName(Reference.MODID.toLowerCase() + "." + blockName);
		this.setBlockTextureName(Reference.MODID + ":" + blockName);

		GameRegistry.registerBlock(this, blockName);
	}

	protected BaseBlock(String blockName, Material material, Class<? extends ItemBlock> itemBlock)
	{
		super(material);
		this.blockName = blockName;

		this.setCreativeTab(PheonixMod.creativeTab);
		this.setBlockName(Reference.MODID.toLowerCase() + blockName);
		this.setBlockTextureName(Reference.MODID + ":" + blockName);

		GameRegistry.registerBlock(this, itemBlock, blockName);
	}
}
