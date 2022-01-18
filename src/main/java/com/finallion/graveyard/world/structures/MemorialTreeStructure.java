package com.finallion.graveyard.world.structures;

import com.finallion.graveyard.TheGraveyard;
import com.finallion.graveyard.config.StructureConfigEntry;
import com.finallion.graveyard.init.TGConfiguredStructures;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.feature.JigsawFeature;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Arrays;
import java.util.Optional;

public class MemorialTreeStructure extends AbstractGraveyardStructure {

    public MemorialTreeStructure(Codec<JigsawConfiguration> codec) {
        super(codec, new StructureConfigEntry(14, 12,
                        Arrays.asList(Biome.BiomeCategory.FOREST.getName()),
                        Arrays.asList("forest", "flower_forest", "dark_forest", "windswept_forest")), // only allow in birch forests
                10, 529239621, MemorialTreeGenerator.STARTING_POOL, "memorial_tree");
    }


    @Override
    public ConfiguredStructureFeature<?, ?> getStructureFeature() {
        return TGConfiguredStructures.MEMORIAL_TREE_STRUCTURE_CONFIG;
    }

    public static class MemorialTreeGenerator {
        public static final StructureTemplatePool STARTING_POOL;

        public MemorialTreeGenerator() {
        }

        public static void init() {
        }

        static {
            STARTING_POOL = Pools.register(new StructureTemplatePool(new ResourceLocation(TheGraveyard.MOD_ID, "memorial_tree"), new ResourceLocation("empty"), ImmutableList.of(Pair.of(StructurePoolElement.legacy(TheGraveyard.MOD_ID + ":memorial_tree/memorial_tree_01"), 1)), StructureTemplatePool.Projection.RIGID));
        }
    }

}