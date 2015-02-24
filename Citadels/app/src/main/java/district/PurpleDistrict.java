package district;
import color.Color;

public class PurpleDistrict extends District {

	// todo should implement the special power somehow, probably creating subclasses for each special purple district
	
	public PurpleDistrict(String name, int cost, int drawable) {
		super(name, cost, Color.PURPLE, drawable);
	}
	
}
