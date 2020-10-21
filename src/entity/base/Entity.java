package entity.base;

public abstract class Entity {

	public Entity() {
		setGc();
		setPosition();
	}

	public abstract void setPosition();

	public abstract void move();

	public abstract void setGc();

}
