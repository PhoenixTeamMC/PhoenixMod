package phoenix.rp;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import phoenix.lib.item.ItemBase;
import phoenix.main.IIntializer;
import phoenix.main.PheonixMod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import phoenix.rp.handler.ConfigurationHandler;
import phoenix.rp.handler.LivingHandler;
import phoenix.rp.items.ItemHandler;
import phoenix.rp.potion.PotionHandler;
import phoenix.rp.reference.ConfigSettings;
import phoenix.rp.reference.Reference;

import java.io.File;

public class Renascintis implements IIntializer{

	@Override
	public String getModuleName() {
		return "Renascintis";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ConfigurationHandler.init(new File(event.getModConfigurationDirectory(), getModuleName()+".cfg"));
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());

		for (int i = 0; i < ConfigSettings.types.length && i < ConfigSettings.playerList.length; i++)
		{
			Reference.godMap.put(ConfigSettings.types[i], ConfigSettings.playerList[i]);
		}

		ItemHandler.init();
		PotionHandler.init();

		itemMaterial = (ItemRP) new ItemRP().setUnlocalizedName("orb").setCreativeTab(PheonixMod.creativeTab);
		
		itemMaterial.addItem(0, "sol");
		itemMaterial.addItem(1, "luna");
	}

	@Override
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new LivingHandler());
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
			
			if(!world.isRemote){
				String name = super.getRawName(stack);
				
				if(name.equals("sol")){
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC.toString() + StatCollector.translateToLocal("info.phoenix.whisper")));
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.BOLD.toString() + EnumChatFormatting.GOLD.toString() + "CALZ CONPHO SOBRA ZOL ROR I TA NAZPSAD"));
				}else if(name.equals("luna")){
					player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC.toString() + StatCollector.translateToLocal("info.phoenix.whisper")));
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
