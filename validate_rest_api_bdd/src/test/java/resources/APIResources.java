package resources;
//enum is special class in java which has collection of constants or  methods
public enum APIResources {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	final String resource;

	// Constructor of the class
	APIResources(String resource)
	{
		this.resource=resource;
	}

	// Returning the resource to the class constructor
	public String getResource()
	{
		return resource;
	}
	

}
