package zdoctor.lazymodder.easy.items;

import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class EasyPotion extends Potion {
	private final int potionId;
	
	public EasyPotion(String name, boolean isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
		this.setPotionName(name);
		this.potionId = REGISTRY.getKeys().size() + 1;
	}
	
	public <T extends Potion> EasyPotion registerPotion(ResourceLocation location, T potionEffect) {
		REGISTRY.register(this.potionId, location, potionEffect);
		return this;
	}
	
	public int getPotionId() {
		return this.potionId;
	}
	
	@Override
	public EasyPotion registerPotionAttributeModifier(IAttribute attribute, String uniqueId, double ammount,
			int operation) {
		return (EasyPotion) super.registerPotionAttributeModifier(attribute, uniqueId, ammount, operation);
	}
	
	@Override
	public EasyPotion setBeneficial() {
		return (EasyPotion) super.setBeneficial();
	}
	
	@Override
	protected EasyPotion setEffectiveness(double effectivenessIn) {
		return (EasyPotion) super.setEffectiveness(effectivenessIn);
	}
	
	@Override
	public EasyPotion setPotionName(String nameIn) {
		return (EasyPotion) super.setPotionName(nameIn);
	}
	
	@Override
	protected EasyPotion setIconIndex(int p_76399_1_, int p_76399_2_) {
		return (EasyPotion) super.setIconIndex(p_76399_1_, p_76399_2_);
	}
	
}
