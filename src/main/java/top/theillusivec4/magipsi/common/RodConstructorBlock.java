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

import javax.annotation.Nonnull;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import vazkii.psi.common.block.BlockCADAssembler;

public class RodConstructorBlock extends BlockCADAssembler {

  public RodConstructorBlock(Properties props) {
    super(props);
  }

  @SuppressWarnings("deprecation")
  @Nonnull
  @Override
  public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos,
      ISelectionContext ctx) {
    VoxelShape top = Block.makeCuboidShape(0, 6, 0, 16, 16, 16);
    VoxelShape middle = Block.makeCuboidShape(4, 2, 4, 12, 6, 12);
    VoxelShape bottom = Block.makeCuboidShape(2, 0, 2, 14, 2, 14);
    return VoxelShapes.or(top, middle, bottom);
  }
}
