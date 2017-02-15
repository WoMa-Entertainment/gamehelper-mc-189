package net.wfoas.gh.items;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.wfoas.gh.dagger.throwable.ThrowableDagger;

public class ItemDagger extends Item {
	private float attackDamage;
	private final Item.ToolMaterial material;

	public ItemDagger(Item.ToolMaterial material) {
		this.material = material;
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.attackDamage = 4.0F + material.getDamageVsEntity() - 0.5f;
	}

	public static float getDamageIron() {
		return 4f + ToolMaterial.IRON.getDamageVsEntity() - 0.5f;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block) {
		if (block == Blocks.web) {
			return 10.0F;
		} else {
			Material material = block.getMaterial();
			return material != Material.plants && material != Material.vine && material != Material.coral
					&& material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		stack.damageItem(1, attacker);
		return true;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, Block blockIn, BlockPos pos,
			EntityLivingBase playerIn) {
		if (blockIn.getBlockHardness(worldIn, pos) != 0.0D) {
			stack.damageItem(2, playerIn);
		}

		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
		ItemStack retis = itemStackIn.copy();
		if (!playerIn.capabilities.isCreativeMode) {
			--itemStackIn.stackSize;
		}
		worldIn.playSoundAtEntity(playerIn, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
		if (this.getMaxDamage() == itemStackIn.getItemDamage()) {
			worldIn.playSoundAtEntity(playerIn, "random.break", 1, 1.5f);
			return retis;
		}

		if (!worldIn.isRemote) {
			ThrowableDagger d = new ThrowableDagger(worldIn, playerIn);
			System.out.println(playerIn.rotationYaw + "y:p" + playerIn.rotationPitch + "[]][");
			d.setPlayersYawPitch(-playerIn.rotationYaw, -playerIn.rotationPitch);
			d.saveItemStackToEntityData(ItemStack.copyItemStack(retis));
			d.onUpdate();
			worldIn.spawnEntityInWorld(d);
			d.syncToClients();
		}
		return itemStackIn;
	}

	@Override
	public boolean canHarvestBlock(Block blockIn) {
		return blockIn == Blocks.web;
	}

	@Override
	public int getItemEnchantability() {
		return this.material.getEnchantability();
	}

	public String getToolMaterialName() {
		return this.material.toString();
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		ItemStack mat = this.material.getRepairItemStack();
		if (mat != null && net.minecraftforge.oredict.OreDictionary.itemMatches(mat, repair, false))
			return true;
		return super.getIsRepairable(toRepair, repair);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
		Multimap multimap = super.getAttributeModifiers(stack);
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
				new AttributeModifier(itemModifierUUID, "Weapon modifier", this.attackDamage, 0));
		return multimap;
	}
}