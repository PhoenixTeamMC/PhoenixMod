package pheonix.command;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingEvent;
import pheonix.main.IIntializer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CommandInit implements IIntializer{

	public static Configuration config;
	
	Map<String, String> eventList = new HashMap<String, String>();
	
	@Override
	public String getModuleName() {
		return "command";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		config = new Configuration(new File(event.getModConfigurationDirectory(), "eventCommand.cfg"));
		
		MinecraftForge.EVENT_BUS.register(this);
		
		config.load();
		
		String[] commands = config.getStringList("eventList", "commands", new String[0], "Category is formatted such that the line is this \"EventName:Command\". An example would be: LivingDeathEvent:say I am dead");
		
		for(String str : commands){
			
			if(str.isEmpty()){
				continue;
			}
			
			String[] parameters = str.split(":");
			if(parameters.length != 2){
				throw new RuntimeException(String.format("Command %s is malfigured in the eventCommand.cfg file. Please fix.", str));
			}
			
			eventList.put(parameters[0].toLowerCase(), parameters[1]);
		}
		
		config.save();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
	@SubscribeEvent
	public void onLivingDeathEvent(LivingEvent e){
		if(!e.entity.worldObj.isRemote && e.entity instanceof EntityPlayer){
			
			String name = eventList.get(e.getClass().getSimpleName().toLowerCase());
			
			if(name == null){
				return;
			}
			
			MinecraftServer.getServer().getCommandManager().executeCommand(new CommandSender((EntityPlayer) e.entity), name);
		}
	}

	public static class CommandSender implements ICommandSender{
		
		Entity player;
		
		private CommandSender(Entity player){
			this.player = player;
		}

		@Override
		public String getCommandSenderName() {
			return player.getCommandSenderName();
		}

		@Override
		public IChatComponent func_145748_c_() {
			return player.func_145748_c_();
		}

		@Override
		public void addChatMessage(IChatComponent p_145747_1_) {
			if(player instanceof ICommandSender){
				((ICommandSender) player).addChatMessage(p_145747_1_);
			}
		}

		@Override
		public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
			return true;
		}

		@Override
		public ChunkCoordinates getPlayerCoordinates() {
			return new ChunkCoordinates(player.chunkCoordX, player.chunkCoordY, player.chunkCoordZ);
		}

		@Override
		public World getEntityWorld() {
			return player.worldObj;
		}
		
	}
	
}
