package district;

import com.dm.citadels.R;

import color.Color;

/**
 * This class contains the name constants
 * and their corresponding (static) get-methods 
 */
public class DistrictConstants {
	
	/* GREEN BUILDINGS */
	public static final String TAVERN = "Tavern";
	public static final String MARKET = "Market";
	public static final String TRADING_POST= "Trading Post";
	public static final String DOCKS = "Docks";
	public static final String HARBOR = "Harbor";
	public static final String TOWN_HALL = "Town Hall";
	
	/* BLUE BUILDINGS */
	public static final String TEMPLE = "Temple";
	public static final String CHURCH = "Church";
	public static final String MONASTERY = "Monastery";
	public static final String CATHEDRAL= "Cathedral";
	
	/* RED BUILDINGS */
	public static final String WATCHTOWER= "Watchtower";
	public static final String PRISON = "Prison";
	public static final String BATTLEFIELD = "Battlefield";
	public static final String FORTRESS = "Fortress";
	
	/* YELLOW BUILDINGS */
	public static final String MANOR = "Manor";
	public static final String CASTLE = "Castle";
	public static final String PALACE = "Palace";
	
	/* PURPLE BUILDINGS */
	public static final String HAUNTED_CITY= "Haunted City";
	public static final String KEEP = "Keep";
	public static final String LABORATORY = "Laboratory";
	public static final String SMITHY = "Smithy";
	public static final String GRAVEYARD = "Graveyard";
	public static final String OBSERVATORY = "Observatory";
	public static final String SCHOOL_OF_MAGIC = "School of Magic";
	public static final String LIBRARY = "Library";
	public static final String GREAT_WALL = "Great Wall";
	public static final String UNIVERSITY = "University";
	public static final String DRAGON_GATE= "Dragon Gate";
	
	
	/* GREEN BUILDINGS */
	
	// TODO Set the right drawables for each district
	public static District tavern() {
		return new District(TAVERN, 1, Color.GREEN, R.drawable.tavern);
	}
	
	public static District market() {
		return new District(MARKET, 2, Color.GREEN, R.drawable.market);
	}
	
	public static District tradingPost() {
		return new District(TRADING_POST, 2, Color.GREEN, R.drawable.trading_post);
	}
	
	public static District docks() {
		return new District(DOCKS, 3, Color.GREEN, R.drawable.docks);
	}
	
	public static District harbor() {
		return new District(HARBOR, 4, Color.GREEN, R.drawable.harbor);
	}
	
	public static District townHall() {
		return new District(TOWN_HALL, 5, Color.GREEN, R.drawable.town_hall);
	}
	
	
	/* BLUE BUILDINGS */
	
	public static District temple() {
		return new District(TEMPLE, 1, Color.BLUE, R.drawable.temple);
	}

	public static District church() {
		return new District(CHURCH, 2, Color.BLUE, R.drawable.church);
	}
	
	public static District monastery() {
		return new District(MONASTERY, 3, Color.BLUE, R.drawable.monastery);
	}
	
	public static District cathedral() {
		return new District(CATHEDRAL, 5, Color.BLUE, R.drawable.cathedral);
	}
	
	
	/* RED BUILDINGS */
	
	public static District watchtower() {
		return new District(WATCHTOWER, 1, Color.RED, R.drawable.watchtower);
	}
	
	public static District prison() {
		return new District(PRISON, 2, Color.RED, R.drawable.prison);
	}

	public static District battlefield() {
		return new District(BATTLEFIELD, 3, Color.RED, R.drawable.battlefield);
	}
	
	public static District fortress() {
		return new District(FORTRESS, 5, Color.RED, R.drawable.fortress);
	}
	
	
	/* YELLOW BUILDINGS */
	
	public static District manor() {
		return new District(MANOR, 3, Color.YELLOW, R.drawable.manor);
	}
	
	public static District castle() {
		return new District(CASTLE, 4, Color.YELLOW, R.drawable.castle);
	}
	
	public static District palace() {
		return new District(PALACE, 5, Color.YELLOW, R.drawable.palace);
	}
	
	
	/* PURPLE BUILDINGS */
	
	public static District hauntedCity() {
		return new District(HAUNTED_CITY, 2, Color.PURPLE, R.drawable.haunted_city);
	}
	
	public static District keep() {
		return new District(KEEP, 3, Color.PURPLE, R.drawable.keep);
	}
	
	public static District laboratory() {
		return new District(LABORATORY, 5, Color.PURPLE, R.drawable.laboratory);
	}
	
	public static District smithy() {
		return new District(SMITHY, 5, Color.PURPLE, R.drawable.smithy);
	}
	
	public static District graveyard() {
		return new District(GRAVEYARD, 5, Color.PURPLE, R.drawable.graveyard);
	}
	
	public static District observatory() {
		return new District(OBSERVATORY, 5, Color.PURPLE, R.drawable.observatory);
	}
	
	public static District schoolOfMagic() {
		return new District(SCHOOL_OF_MAGIC, 6, Color.PURPLE, R.drawable.school_of_magic);
	}
	
	public static District library() {
		return new District(LIBRARY, 6, Color.PURPLE, R.drawable.library);
	}
	
	public static District greatWall() {
		return new District(GREAT_WALL, 6, Color.PURPLE, R.drawable.great_wall);
	}
	
	public static District university() {
		return new District(UNIVERSITY, 6, Color.PURPLE, R.drawable.university, 8);
	}
	
	public static District dragonGate() {
		return new District(DRAGON_GATE, 6, Color.PURPLE, R.drawable.dragon_gate, 8);
	}
}
