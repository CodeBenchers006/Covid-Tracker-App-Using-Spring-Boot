package CodeBenchers006.sb.covid.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationStats {
	
	private String state;
	private String country;
	private int latestTotCases;
	
	 
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestTotCases=" + latestTotCases + "]";
	}
	
	

}
