package com.example.examplemod.block;

import com.example.examplemod.SWM;
import com.example.examplemod.block.custom.GuidingSkintBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SWMBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SWM.MODID);

    public static final RegistryObject<Block> GUIDING_SKINT_BLOCK = BLOCKS.register("guiding_skint_block",
            () -> new GuidingSkintBlock(BlockBehaviour.Properties.copy(Blocks.STONE).sound(SoundType.STONE).strength(1f).noOcclusion().lightLevel(state -> 12).strength(6000f).noCollission()));

    public static void init(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
