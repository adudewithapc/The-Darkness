package thatmartinguy.thedarkness.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import thatmartinguy.thedarkness.client.render.RenderLivingShadow;
import thatmartinguy.thedarkness.entity.EntityLivingShadow;
import thatmartinguy.thedarkness.util.Reference;

public class ModModelManager
{
    public static void registerAllModels()
    {
        registerItemModels();
        registerBlockModels();
        registerMobModels();
    }

    private static void registerItemModels()
    {

    }

    private static void registerItemModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
    }

    private static void registerBlockModels()
    {

    }

    private static void registerBlockModel(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "normal"));
    }

    private static void registerMobModels()
    {
        registerMobModel(EntityLivingShadow.class, RenderLivingShadow::new);
    }

    private static <T extends Entity> void registerMobModel(Class<T> entityClass, IRenderFactory<? super T> renderFactory)
    {
        RenderingRegistry.registerEntityRenderingHandler(entityClass, renderFactory);
    }
}
