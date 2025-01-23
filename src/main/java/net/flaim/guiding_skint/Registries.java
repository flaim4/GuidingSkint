package net.flaim.guiding_skint;

import net.flaim.guiding_skint.block.GuidingSkintBlock;
import net.flaim.guiding_skint.block.GuidingSkintBlockEntity;
import net.flaim.guiding_skint.item.GuidingSkintBlockItem;
import net.flaim.guiding_skint.network.PacketHandler;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registries {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GuidingSkintMod.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GuidingSkintMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GuidingSkintMod.MOD_ID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, GuidingSkintMod.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(net.minecraft.core.registries.Registries.CREATIVE_MODE_TAB, GuidingSkintMod.MOD_ID);

    public static final RegistryObject<Block> GUIDING_SKINT_BLOCK = BLOCKS.register("guiding_skint", () -> new GuidingSkintBlock(BlockBehaviour.Properties.of()));

    public static final RegistryObject<Item> GUIDING_SKINT_BLOCK_ITEM = ITEMS.register("guiding_skint", () -> new GuidingSkintBlockItem(GUIDING_SKINT_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<BlockEntityType<GuidingSkintBlockEntity>> GUIDING_SKINT_BLOCK_ENTITY = BLOCK_ENTITIES.register("guiding_skint_block_entity", () -> BlockEntityType.Builder.of(GuidingSkintBlockEntity::new, Registries.GUIDING_SKINT_BLOCK.get()).build(null));

    public static final RegistryObject<CreativeModeTab> GUIDING_SKINT_TAB = CREATIVE_MODE_TABS.register("guiding_skint_tab", () -> CreativeModeTab.builder()
            .icon(() -> GUIDING_SKINT_BLOCK_ITEM.get().getDefaultInstance())
            .title(Component.translatable("itemGroup.guiding_skint_tab"))
            .displayItems((parameters, output) -> output.accept(GUIDING_SKINT_BLOCK_ITEM.get())).build());

    public static boolean NEVER(Object... ignoredObjects) {
        return false;
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
