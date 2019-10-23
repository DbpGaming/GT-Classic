package gtclassic.tile.multi;

import gtclassic.tile.GTTileBaseMachine;
import net.minecraft.entity.player.EntityPlayer;

public abstract class GTTileMultiBaseMachineSimple extends GTTileBaseMachine {

	public boolean lastState;
	public boolean firstCheck = true;

	/**
	 * Constructor for a GTC Multi-block Machine.
	 * 
	 * @param slots        - int amount of slots, input, outputs, battery etc..
	 * @param upgrades     - int amount of upgrade slots
	 * @param defaultinput - int default energy input
	 * @param maxinput     - int maximum energy input
	 */
	public GTTileMultiBaseMachineSimple(int slots, int upgrades, int defaultinput, int maxinput) {
		super(slots, upgrades, defaultinput, 100, maxinput);
	}

	@Override
	public boolean canWork() {
		boolean superCall = super.canWork();
		if (superCall) {
			if (world.getTotalWorldTime() % 256 == 0 || firstCheck) {
				lastState = checkStructure();
				firstCheck = false;
			}
			superCall = superCall && lastState;
		}
		return superCall;
	}

	public boolean checkStructure() {
		if (!world.isAreaLoaded(pos, 3)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean hasGui(EntityPlayer player) {
		return true;
	}
}