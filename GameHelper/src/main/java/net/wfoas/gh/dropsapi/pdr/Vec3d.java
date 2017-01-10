package net.wfoas.gh.dropsapi.pdr;

import net.minecraft.util.BlockPos;

public class Vec3d {
	public double x, y, z;
	public Vec3d(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3d(BlockPos bp){
		this(bp.getX(), bp.getY(), bp.getZ());
	}
	
	public Vec3d(){
		this(0, 0, 0);
	}
	
	public BlockPos toBlockPos(){
		return new BlockPos(x, y, z);
	}
	
	public Vec3d(Vec3d v3d){
		this(v3d.x, v3d.y, v3d.z);
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	
	public void setX(double x){
		this.x = x;
	}
	public void setY(double x){
		this.y = x;
	}
	public void setZ(double x){
		this.z = x;
	}
	
	public Vec3d getRelative(double x, double y, double z){
		return new Vec3d(this.x + x, this.y + y, this.z + z);
	}
	
	public void relative(double x, double y, double z){
		this.x += x;
		this.y += y;
		this.z += z;
	}
}
