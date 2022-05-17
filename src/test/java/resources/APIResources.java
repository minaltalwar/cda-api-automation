package resources;
//enum is special class in java which has collection of constants and methods
public enum APIResources {

	sampleshareAPI("/cda-opbench-service/opbench/sampleshare"), 
	statisticsAPI(""),
	trendingAPI(""),
	cdfAPI("");
	
	private String resource;
	
	APIResources(String resource) // every method will use this constructor to assign value to it
	{
		this.resource=resource;
	}
	
	public String getResource() // and this will return value 
	{
		return resource;
	}


	
}
