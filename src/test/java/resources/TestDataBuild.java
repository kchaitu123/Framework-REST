package resources;

import java.util.ArrayList;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayLoad(String name,String language,String address) {
		
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setName(name);
		place.setLanguage(language);
		place.setPhone_number("(+91) 983 893 3937");
		place.setAddress(address);
		place.setWebsite("https://rahulshettyacademy.com");
		//place.setLanguage(language);
		
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
        
		place.setTypes(myList);
		
		Location location = new Location();
		
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		place.setLocation(location);
		
		return place;
	}
	
	public String deletePlacePlayLoad(String place_id) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"}";
	}
}
