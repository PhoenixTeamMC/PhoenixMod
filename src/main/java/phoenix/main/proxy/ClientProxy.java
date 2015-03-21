package phoenix.main.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import phoenix.rp.entity.EntityDivineProjectile;
import phoenix.rp.render.RenderDivineArrow;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenaRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityDivineProjectile.class, new RenderDivineArrow());
    }
}
