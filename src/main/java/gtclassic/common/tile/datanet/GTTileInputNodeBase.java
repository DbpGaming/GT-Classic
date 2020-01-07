package gtclassic.common.tile.datanet;

import gtclassic.common.util.datanet.GTDataNet;
import ic2.core.block.base.tile.TileEntityMachine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class GTTileInputNodeBase extends TileEntityMachine implements ITickable {

	public GTTileComputerCube computer;

	/**
	 * This tile actually moves items or fluids, and stores the output node list
	 **/
	public GTTileInputNodeBase(int slots) {
		super(slots);
	}

	@Override
	public boolean canSetFacing(EntityPlayer player, EnumFacing facing) {
		return this.getFacing() != facing;
	}

	@Override
	public boolean canRemoveBlock(EntityPlayer player) {
		return true;
	}

	@Override
	public void update() {
		if (world.getTotalWorldTime() % GTDataNet.TICK_RATE == 0) {
			if (this.computer == null || this.computer.dataNet == null || this.computer.dataNet.isEmpty()) {
				return;
			}
			// if import pos not loaded return;
			for (BlockPos nodePos : this.computer.dataNet) {
				if (!world.isBlockLoaded(nodePos) || nodePos == this.pos) {
					continue;
				}
				TileEntity wTile = world.getTileEntity(nodePos);
				if (wTile instanceof GTTileOutputNodeBase) {
					GTTileOutputNodeBase node = (GTTileOutputNodeBase) wTile;
					if (onDataNetTick(node)) {
						break;
					}
				}
			}
		}
	}

	// TODO redo this to pass the tile and get all the info for the node output from
	// the node tile, like facing etc..
	public abstract boolean onDataNetTick(GTTileOutputNodeBase node);
}