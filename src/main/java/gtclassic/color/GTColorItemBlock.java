package gtclassic.color;

import java.awt.Color;

import gtclassic.itemblock.GTItemBlockRare;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class GTColorItemBlock extends GTItemBlockRare implements GTColorItemInterface {

	Block block;

	public GTColorItemBlock(Block block) {
		super(block);
		this.block = block;
	}

	@Override
	public Color getColor(ItemStack stack, int index) {
		if (this.block instanceof GTColorBlockInterface) {
			return ((GTColorBlockInterface) block).getColor(this.block, index);
		} else {
			return null;
		}
	}
}
