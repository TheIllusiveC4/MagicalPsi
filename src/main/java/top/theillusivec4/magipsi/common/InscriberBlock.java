/*
 * Copyright (c) 2020 C4
 *
 * This file is part of Magical Psi, a mod made for Minecraft.
 *
 * Magical Psi is free software: you can redistribute it and/or modify it under the terms of the
 * Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International Public License.
 *
 * You should have received a copy of the license along with Magical Psi.
 * If not, see <https://creativecommons.org/licenses/by-nc-sa/4.0/legalcode.txt>.
 */

package top.theillusivec4.magipsi.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import vazkii.psi.common.block.BlockProgrammer;

public class InscriberBlock extends BlockProgrammer {

  private static final VoxelShape SHAPE;

  static {
    VoxelShape top = Block.makeCuboidShape(0, 10, 0, 16, 16, 16);
    VoxelShape leg1 = Block.makeCuboidShape(1, 0, 1, 3, 10, 3);
    VoxelShape leg2 = Block.makeCuboidShape(13, 0, 13, 15, 10, 15);
    VoxelShape leg3 = Block.makeCuboidShape(1, 0, 13, 3, 10, 15);
    VoxelShape leg4 = Block.makeCuboidShape(13, 0, 1, 15, 10, 3);
    SHAPE = VoxelShapes.or(top, leg1, leg2, leg3, leg4);
  }

  public InscriberBlock(Properties props) {
    super(props);
    this.setDefaultState(
        (this.getStateContainer().getBaseState()).with(HORIZONTAL_FACING, Direction.NORTH)
            .with(ENABLED, false));
  }

  @Override
  public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos,
      ISelectionContext ctx) {
    return SHAPE;
  }
}
