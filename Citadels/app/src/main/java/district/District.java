package district;
import color.Color;

public class District {
	private final String name;
	private final int cost;
	private final Color color;
	private final int drawable;
	
	// Some buildings are worth more than they costed, when counting the score
	private final int value;
	
	public District(String name, int cost, Color color, int drawable) {
		this(name, cost, color, drawable, cost);
	}
	
	public District(String name, int cost, Color color, int drawable, int value) {
		this.name = name;
		this.cost = cost;
		this.color = color;
		this.drawable = drawable;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getDrawable() {
		return drawable;
	}
	
	public int getValue() {
		return value;
	}
}
