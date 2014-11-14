package pheonix.rp;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import pheonix.lib.item.ItemBase;
import pheonix.main.IIntializer;
import pheonix.main.PheonixMod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Renascintis implements IIntializer{

	@Override
	public String getModuleName() {
		return "Renascintis";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		itemMaterial = (ItemRP) new ItemRP().setUnlocalizedName("orb").setCreativeTab(PheonixMod.creativeTab);
		
		itemMaterial.addItem(0, "sol");
		itemMaterial.addItem(1, "luna");
	}

	@Override
	public void init(FMLInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	ItemRP itemMaterial;

	public class ItemRP extends ItemBase{
		
		public ItemRP(){
			this.setMaxStackSize(1);
		}
		
		public EnumRarity getRarity(ItemStack stack){
			return EnumRarity.epic;
		}

		
		
		@Override
		public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
			
			if(world.isRemote == false){
				String name = super.getRawName(stack);
				
				if(name.equals("sol")){
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC.toString() + StatCollector.translateToLocal("info.pheonix.whisper")));
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.toString() + EnumChatFormatting.GOLD.toString() + "CALZ CONPHO SOBRA ZOL ROR I TA NAZPSAD"));
				}else if(name.equals("luna")){
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC.toString() + StatCollector.translateToLocal("info.pheonix.whisper")));
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.toString() + EnumChatFormatting.GOLD.toString() + "GRAA TA MALPRG DS HOLQ NOTHOA ZIMZ"));
				}
				
				
			}
			
			return super.onItemRightClick(stack, world, player);
		}

		@Override
		public boolean hasEffect(ItemStack par1ItemStack, int pass) {
			return true;
		}
		
		
	}
	
}
