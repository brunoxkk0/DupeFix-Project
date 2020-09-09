package com.focamacho.dupefixproject.mixin.actuallyadditions;

import de.ellpeck.actuallyadditions.mod.tile.CustomEnergyStorage;
import de.ellpeck.actuallyadditions.mod.tile.TileEntityInventoryBase;
import de.ellpeck.actuallyadditions.mod.util.StackUtil;
import de.ellpeck.actuallyadditions.mod.util.WorldUtil;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets = "de/ellpeck/actuallyadditions/mod/tile/TileEntityDirectionalBreaker", remap = false)
public abstract class TileEntityDirectionalBreakerMixin extends TileEntityInventoryBase {

    @Shadow private int currentTime;

    @Shadow @Final public CustomEnergyStorage storage;

    @Shadow private int lastEnergy;

    @Shadow @Final public static int ENERGY_USE;

    @Shadow @Final public static int RANGE;

    public TileEntityDirectionalBreakerMixin(int slots, String name) {
        super(slots, name);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.world.isRemote) {
            if (!this.isRedstonePowered && !this.isPulseMode) {
                if (this.currentTime > 0) {
                    this.currentTime--;
                    if (this.currentTime <= 0) {
                        this.doWork();
                    }
                } else {
                    this.currentTime = 15;
                }
            }

            if (this.storage.getEnergyStored() != this.lastEnergy && this.sendUpdateWithInterval()) {
                this.lastEnergy = this.storage.getEnergyStored();
            }
        }
    }

    private void doWork() {
        if (this.storage.getEnergyStored() >= ENERGY_USE * RANGE) {
            IBlockState state = this.world.getBlockState(this.pos);
            EnumFacing sideToManipulate = WorldUtil.getDirectionByPistonRotation(state);

            for (int i = 0; i < RANGE; i++) {
                BlockPos coordsBlock = this.pos.offset(sideToManipulate, i + 1);
                if(this.world.getTileEntity(coordsBlock) != null) return;
                IBlockState breakState = this.world.getBlockState(coordsBlock);
                Block blockToBreak = breakState.getBlock();
                if (blockToBreak != null && !this.world.isAirBlock(coordsBlock) && this.world.getBlockState(coordsBlock).getBlockHardness(this.world, coordsBlock) > -1.0F) {
                    NonNullList<ItemStack> drops = NonNullList.create();
                    blockToBreak.getDrops(drops, this.world, coordsBlock, breakState, 0);
                    float chance = WorldUtil.fireFakeHarvestEventsForDropChance(this, drops, this.world, coordsBlock);

                    if (chance > 0 && this.world.rand.nextFloat() <= chance) {
                        if (StackUtil.canAddAll(this.inv, drops, false)) {
                            this.world.playEvent(2001, coordsBlock, Block.getStateId(this.world.getBlockState(coordsBlock)));
                            this.world.setBlockToAir(coordsBlock);
                            StackUtil.addAll(this.inv, drops, false);
                            this.storage.extractEnergyInternal(ENERGY_USE, false);
                            this.markDirty();
                        }
                    }
                }
            }
        }
    }

}
