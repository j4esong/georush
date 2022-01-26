class Camera {
	
	private Vertex pos;
	private double pitch, yaw;
	private double[][] orientation;	//unit vectors storing orientation of cam

	public Camera(Vertex pos, double pitch, double yaw) {
		this.pos = pos;
		this.pitch = pitch;
		this.yaw = yaw;
		orientation = new double[3][3];
		updateOrientation();
	}

	public Vertex getPos() {
		return pos;
	}

	public double getPitch() {
		return pitch;
	}

	public double getYaw() {
		return yaw;
	}

	public void setPos(Vertex v) {
		this.pos = v;
		updateOrientation();
	}

	public void setPitch(double pitch) {
		this.pitch = pitch;
		updateOrientation();
	}

	public void setYaw(double yaw) {
		this.yaw = yaw;
		updateOrientation();
	}

	public double[][] getOrientation() {
		return orientation;
	}

	private void updateOrientation() {
		orientation[2][0] = Math.sin(yaw);
		orientation[2][1] = Math.cos(yaw) * Math.sin(pitch);
		orientation[2][2] = Math.cos(yaw) * Math.cos(pitch);
		orientation[0][0] = Math.cos(yaw);
		orientation[0][1] = Math.sin(yaw) * Math.sin(pitch);
		orientation[0][2] = -1 * Math.sin(yaw) * Math.cos(pitch);
	}

}