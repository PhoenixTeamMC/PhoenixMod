package phoenix.main.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import phoenix.rp.entity.EntityGenesisProjectile;
import phoenix.rp.render.RenderGenesisProjectile;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenaRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityGenesisProjectile.class, new RenderGenesisProjectile());
    }
}
