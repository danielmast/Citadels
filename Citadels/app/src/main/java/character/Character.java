package character;

import color.Color;

public abstract class Character {
	protected Color color;
	protected int drawable;
	
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getDrawable() {
		return drawable;
	}
}
