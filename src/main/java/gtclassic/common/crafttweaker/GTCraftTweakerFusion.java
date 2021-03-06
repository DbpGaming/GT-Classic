package gtclassic.common.crafttweaker;

import java.util.Locale;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import gtclassic.api.crafttweaker.GTCraftTweakerActions;
import gtclassic.common.tile.multi.GTTileMultiFusionReactor;
import ic2.api.recipe.IRecipeInput;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.gtclassic.FusionReactor")
@ZenRegister
public class GTCraftTweakerFusion {

	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient input1, IIngredient input2, int totalEu) {
		GTCraftTweakerActions.apply(new FusionReactorRecipeAction(GTCraftTweakerActions.of(input1), GTCraftTweakerActions.of(input2), totalEu, CraftTweakerMC.getItemStack(output)));
	}

	private static final class FusionReactorRecipeAction implements IAction {

		private final IRecipeInput input1;
		private final IRecipeInput input2;
		private final int totalEu;
		private final ItemStack output;

		FusionReactorRecipeAction(IRecipeInput input1, IRecipeInput input2, int totalEu, ItemStack output) {
			this.input1 = input1;
			this.input2 = input2;
			this.totalEu = totalEu;
			this.output = output;
		}

		@Override
		public void apply() {
			if (totalEu > 0) {
				GTTileMultiFusionReactor.addRecipe(new IRecipeInput[] { input1,
						input2 }, GTTileMultiFusionReactor.totalEu(totalEu), output);
			} else {
				CraftTweakerAPI.logError(CraftTweakerAPI.getScriptFileAndLine() + " > "
						+ "Eu amount must be greater then 0!!");
			}
		}

		@Override
		public String describe() {
			return String.format(Locale.ENGLISH, "Add Recipe[%s, %s -> %s] to %s", this.input1, this.input2, this.output, GTTileMultiFusionReactor.RECIPE_LIST);
		}
	}
}
