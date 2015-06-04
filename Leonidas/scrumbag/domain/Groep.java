<<<<<<< HEAD
package scrumbag.domain;

import java.util.List;

public class Groep {
	 public Groep(String naam, String omschrijving) {
		this.naam = naam;
		this.omschrijving = omschrijving;
	}
	 public List<Kornuit> getLeden() {
		return leden;
	}
	public void setLeden(List<Kornuit> leden) {
		this.leden = leden;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	private List<Kornuit> leden;
	private String naam, omschrijving;
	public void addKornuit(Kornuit k){
		if(!leden.contains(k)){
			leden.add(k);
		}
	}
	public void removeKornuit(Kornuit k){
		if(leden.contains(k)){
			leden.remove(k);
		}
	}
	
}
=======
package scrumbag.domain;

import java.util.List;

public class Groep {
	 public Groep(String naam, String omschrijving) {
		this.naam = naam;
		this.omschrijving = omschrijving;
	}
	 public List<Kornuit> getLeden() {
		return leden;
	}
	public void setLeden(List<Kornuit> leden) {
		this.leden = leden;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	private List<Kornuit> leden;
	private String naam, omschrijving;
	public void addKornuit(Kornuit k){
		if(!leden.contains(k)){
			leden.add(k);
		}
	}
	public void removeKornuit(Kornuit k){
		if(leden.contains(k)){
			leden.remove(k);
		}
	}
	
}
>>>>>>> 5c7e126b334cd885a5154cf42e7a4662abf935b2
