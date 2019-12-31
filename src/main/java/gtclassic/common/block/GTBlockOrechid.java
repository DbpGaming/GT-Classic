package gtclassic.common.block;

import java.util.List;

import gtclassic.GTMod;
import gtclassic.api.block.GTBlockBase;
import ic2.core.block.render.model.ModelSapling;
import ic2.core.platform.textures.Ic2Icons;
import ic2.core.platform.textures.models.BaseModel;
import ic2.core.platform.textures.obj.ICustomModeledBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GTBlockOrechid extends GTBlockBase implements ICustomModeledBlock {

	static final String TOOLTIP = "Indicates the presence of a bedrock ore deposit nearby";

	public GTBlockOrechid() {
		super(Material.PLANTS);
		setRegistryName("orechid");
		setUnlocalizedName(GTMod.MODID + ".orechid");
		setCreativeTab(GTMod.creativeTabGT);
		setHardness(0.8F);
		setResistance(0.2F);
		setSoundType(SoundType.PLANT);
		setHarvestLevel("axe", 0);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format(TOOLTIP));
	}

	@Override
	public TextureAtlasSprite getTextureFromState(IBlockState var1, EnumFacing var2) {
		return Ic2Icons.getTextures(GTMod.MODID + "_items")[38];
	}

	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.MODEL;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.2D, 0.0D, 0.2D, 0.8D, 0.7D, 0.8D);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
		return NULL_AABB;
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
		return BlockFaceShape.UNDEFINED;
	}

	@SideOnly(Side.CLIENT)
	public BaseModel getModelFromState(IBlockState state) {
		return new ModelSapling(Ic2Icons.getTextures(GTMod.MODID + "_items")[38]);
	}

	@Override
	public List<IBlockState> getValidModelStates() {
		return this.getBlockState().getValidStates();
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		IBlockState soil = worldIn.getBlockState(pos.down());
		return super.canPlaceBlockAt(worldIn, pos) && soil.getMaterial() == Material.GRASS;
	}
}
