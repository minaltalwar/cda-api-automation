package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.Map;
import pojo.SampleShare;

public class TestDataBuild {

	public SampleShare sampleSharePayload(String kpi , String technology) {
		
		System.out.println(kpi);
		
		SampleShare ss = new SampleShare();
		ss.setCountry("France");
		ss.setDashboardType("union");
		ss.setEndTime("1619391600000");
		
		Map m = new Map();
		m.setKpi(kpi);
		ss.setMap(m);
		
		List<String> myList= new ArrayList<String>();
		myList.add("Orange");
		myList.add("SFR");
		myList.add("Bouygues");
		myList.add("Free Mobile");
		
		ss.setOperator(myList);
		ss.setPixelSize("4");
		ss.setPolygon(null);
		ss.setPolygonId("0");
		ss.setStartTime("1619222400000");
		
		ss.setTechnology(technology);
		
		List<String> myList1= new ArrayList<String>();
		ss.setCarriers(myList1);
		
		ss.setTileSize("256");
		ss.setZoom("6");
		
		List<String> myList2= new ArrayList<String>();
		ss.setSampleType(myList2);
		
		ss.setPolygonBounds(null);
		return ss;
		
	}
	
}
