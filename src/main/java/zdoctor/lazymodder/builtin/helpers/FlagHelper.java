package zdoctor.lazymodder.builtin.helpers;

import net.minecraft.entity.EntityLiving;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.EntityDataManager;

public class FlagHelper {
	private int flag;

	public FlagHelper(int startFlag) {
		this.flag = startFlag;
	}
	
	public void reset() {
		flag = 0;
	}
	
	public void or(int bit) {
		flag |= bit;
	}
	
	public boolean and(int bit) {
		return (flag & bit) > 0;
	}
}
