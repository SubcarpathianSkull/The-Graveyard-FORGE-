package com.finallion.graveyard.biomes.features.trees;

import com.finallion.graveyard.biomes.features.surfaceFeatures.FeatureHelper;
import com.finallion.graveyard.biomes.features.trees.config.TGTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;

import java.util.Random;

public class LargeBentSpruceTree01 extends BaseSpruceTree {
    private final int trunkHeight = 24;

    public LargeBentSpruceTree01(Codec<TGTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator generator, Random random, BlockPos blockPos, TGTreeFeatureConfig config) {
        BlockPos.Mutable mutable = new BlockPos.Mutable().set(blockPos);
        BlockState wood = config.woodState;
        BlockState leaf = config.leafState;
        int offsetTrunk = random.nextInt(3);
        Direction.Axis axis = random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);


        if (!FeatureHelper.canBePlaced(world, blockPos.below(), world.getBlockState(blockPos.below()))) {
            return false;
        }

        if (!FeatureHelper.canGenerate(world, blockPos, 24)) {
            return false;
        }


        // straight half of the trunk
        // trunkHeight - top bent - middle bent + offset
        for (int i = 0; i <= trunkHeight - 9 - 7 + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
        }

        // bent first part of the trunk
        // trunkHeight - top bent + offset
        for (int i = 9 + offsetTrunk; i < trunkHeight - 7 + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i).relative(direction), wood, 2);
        }


        // bent second part of the trunk
        for (int i = 17 + offsetTrunk; i <= trunkHeight + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i).relative(direction, 2), wood, 2);
        }

        // start generating leaves from the top
        // shift leaf layer in bending direction
        mutable.move(0, trunkHeight + offsetTrunk, 0);
        mutable.move(direction, 2);

        // bent tree top leaf layer
        setLeaves(world, mutable.offset(0, 5, 0).relative(direction), leaf);
        setLeaves(world, mutable.offset(0, 4, 0).relative(direction), leaf);
        setLeaves(world, mutable.offset(0, 3, 0).relative(direction), leaf);
        setLeaves(world, mutable.offset(0, 3, 0), leaf);
        setLeaves(world, mutable.offset(0, 2, 0), leaf);
        setLeaves(world, mutable.offset(0, 1, 0), leaf);
        setLeaves(world, mutable.offset(0, 2, 0).relative(direction.getOpposite()), leaf);

        generateOneStar(world, mutable.offset(0, 1, 0), false);
        generateTwoStar(world, mutable.offset(0, 0, 0), true);
        randomSpreadOne(world, mutable.offset(0, -1, 0), false, 2);
        generateOneStar(world, mutable.offset(0, -2, 0), false);
        generateTwoStar(world, mutable.offset(0, -3, 0), false);
        randomSpreadThree(world, mutable.offset(0, -3, 0), true, 2);
        randomSpreadTwo(world, mutable.offset(0, -4, 0), false, 2);
        generateOneStar(world, mutable.offset(0, -5, 0), false);
        generateTwoStar(world, mutable.offset(0, -6, 0), false);
        generateThreeStar(world, mutable.offset(0, -7, 0), true);

        // middle bent
        mutable.move(direction.getOpposite(), 1);
        randomSpreadThree(world, mutable.offset(0, -7, 0), true, 2);
        generateOneStar(world, mutable.offset(0, -8, 0), false);
        randomSpreadTwo(world, mutable.offset(0, -8, 0), false, 4);
        generateTwoStar(world, mutable.offset(0, -9, 0), false);
        generateThreeStar(world, mutable.offset(0, -10, 0), false);
        generateFourStar(world, mutable.offset(0, -11, 0), true);
        generateOneStar(world, mutable.offset(0, -12, 0), false);
        randomSpreadTwo(world, mutable.offset(0, -12, 0), false, 4);
        generateTwoStar(world, mutable.offset(0, -13, 0), false);

        // lower bent
        mutable.move(direction.getOpposite(), 1);
        generateTwoStar(world, mutable.offset(0, -13, 0), false);
        randomSpreadFour(world, mutable.offset(0, -13, 0), false, 4);
        generateThreeStar(world, mutable.offset(0, -14, 0), false);
        randomSpreadFour(world, mutable.offset(0, -14, 0), false, 4);
        generateFourStar(world, mutable.offset(0, -15, 0), true);
        randomSpreadFour(world, mutable.offset(0, -15, 0), false, 4);
        generateTwoStar(world, mutable.offset(0, -16, 0), false);
        randomSpreadFour(world, mutable.offset(0, -16, 0), false, 8);
        generateThreeStar(world, mutable.offset(0, -17, 0), false);
        randomSpreadFour(world, mutable.offset(0, -17, 0), false, 4);
        generateFourStar(world, mutable.offset(0, -18, 0), false);
        randomSpreadFive(world, mutable.offset(0, -18, 0), false, 4);
        generateFiveStar(world, mutable.offset(0, -19, 0), true);
        generateThreeStar(world, mutable.offset(0, -20, 0), false);
        generateOneStar(world, mutable.offset(0, -21, 0), false);

        return false;
    }
}