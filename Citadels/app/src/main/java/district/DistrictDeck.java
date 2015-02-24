package district;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class DistrictDeck {
	private Queue<District> districts;
	private int size;

	public DistrictDeck() {
		districts = new LinkedList<District>();
		size = 0;
		
		putAllDistricts();
	}
	
	public int getSize() {
		return size;
	}
	
	public District get() {
		if (size == 0)
			return null;
		
		size--;
		District district = districts.remove();
		return district;
	}
	
	public void put(District district) {
		districts.add(district);
		size++;
	}
	
	private void putAllDistricts() {
		// Create all districts and put in a list
		District[] array = {
				/* GREEN BUILDINGS */
				DistrictConstants.tavern(),
				DistrictConstants.tavern(),
				DistrictConstants.tavern(),
				DistrictConstants.tavern(),
				DistrictConstants.tavern(),
				DistrictConstants.market(),
				DistrictConstants.market(),
				DistrictConstants.market(),
				DistrictConstants.market(),
				DistrictConstants.tradingPost(),
				DistrictConstants.tradingPost(),
				DistrictConstants.tradingPost(),
				DistrictConstants.docks(),
				DistrictConstants.docks(),
				DistrictConstants.docks(),
				DistrictConstants.harbor(),
				DistrictConstants.harbor(),
				DistrictConstants.harbor(),
				DistrictConstants.townHall(),
				DistrictConstants.townHall(),
				
				/* BLUE BUILDINGS */
				DistrictConstants.temple(),
				DistrictConstants.temple(),
				DistrictConstants.temple(),
				DistrictConstants.church(),
				DistrictConstants.church(),
				DistrictConstants.church(),
				DistrictConstants.monastery(),
				DistrictConstants.monastery(),
				DistrictConstants.monastery(),
				DistrictConstants.cathedral(),
				DistrictConstants.cathedral(),
				
				/* RED BUILDINGS */
				DistrictConstants.watchtower(),
				DistrictConstants.watchtower(),
				DistrictConstants.watchtower(),
				DistrictConstants.prison(),
				DistrictConstants.prison(),
				DistrictConstants.prison(),
				DistrictConstants.battlefield(),
				DistrictConstants.battlefield(),
				DistrictConstants.battlefield(),
				DistrictConstants.fortress(),
				DistrictConstants.fortress(),
				
				/* YELLOW BUILDINGS */
				DistrictConstants.manor(),
				DistrictConstants.manor(),
				DistrictConstants.manor(),
				DistrictConstants.manor(),
				DistrictConstants.manor(),
				DistrictConstants.castle(),
				DistrictConstants.castle(),
				DistrictConstants.castle(),
				DistrictConstants.castle(),
				DistrictConstants.palace(),
				DistrictConstants.palace(),
				DistrictConstants.palace(),
				
				/* PURPLE BUILDINGS */
				DistrictConstants.hauntedCity(),
				DistrictConstants.keep(),
				DistrictConstants.keep(),
				DistrictConstants.laboratory(),
				DistrictConstants.smithy(),
				DistrictConstants.graveyard(),
				DistrictConstants.observatory(),
				DistrictConstants.schoolOfMagic(),
				DistrictConstants.library(),
				// DistrictConstants.greatWall(),
				DistrictConstants.university(),
				DistrictConstants.dragonGate()
		};
		ArrayList<District> list = new ArrayList<District>(Arrays.asList(array));
		
		// Randomly shuffle this list
		Collections.shuffle(list);
		
		// Put the shuffled list in the deck
		for (int i = 0; i < 65; i++) {
			put(list.get(i));
		}
	}
}
