package net.wfoas.gh.structureapi.export;

import net.wfoas.gh.dropsapi.pdr.Vec3d;

public class DoubleVector3f {
	double x1, y1, z1, x2, y2, z2;

	public DoubleVector3f(Vec3d vec31, Vec3d vec32) {
		this(vec31.x, vec31.y, vec31.z, vec32.x, vec32.y, vec32.z);
	}

	public static final DoubleVector3f NULL_VECTOR = new DoubleVector3f(-1, -1, -1, -1, -1, -1);

	public DoubleVector3f(double x1, double y1, double z1, double x2, double y2, double z2) {
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}

	public DoubleVector3f setLeftSide(Vec3d v3d) {
		x1 = v3d.x;
		y1 = v3d.y;
		z1 = v3d.z;
		return this;
	}

	public DoubleVector3f setRightSide(Vec3d v3d) {
		x2 = v3d.x;
		y2 = v3d.y;
		z2 = v3d.z;
		return this;
	}

	public Vec3d getLeftSide() {
		return new Vec3d(x1, y1, z1);
	}

	public Vec3d getRightSide() {
		return new Vec3d(x2, y2, z2);
	}

	public DoubleVector3f aabb3d() {
		return new DoubleVector3f(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2),
				Math.max(y1, y2), Math.max(z1, z2));
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DoubleVector3f))
			return false;
		DoubleVector3f other = (DoubleVector3f) obj;
		return x1 == other.x1 && x2 == other.x2 && y1 == other.y1 && y2 == other.y2 && z1 == other.z1 && z2 == other.z2;
	}
}
