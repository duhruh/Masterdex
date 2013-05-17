package com.protodx.masterdex;

import java.util.ArrayList;

public class Pokemon {
	private int id;
	private String name;
	private String evolvesFrom;
	private String evolvesInto;
	private String pronunce;
	private int hp;
	private int attack;
	private int defence;
	private int spAtk;
	private int spDef;
	private int speed;
	private int statTotal;
	private String species;
	private String type;
	private String height;
	private String weight;
	private String abilities;
	private String weakness;
	private String bio;
	private String layout;
	private String pic;
	private String icon;
	private String evolutionSet;
	private String evolutionLayout;
	private String method;
	private ArrayList<Learnset> learnset = new ArrayList<Learnset>();
	private EffortsValue EV = null;
	private Location location = null;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getEvolvesFrom(){
		return evolvesFrom;
	}
	public void setEvolvesFrom(String evolvesFrom){
		this.evolvesFrom = evolvesFrom;
	}
	public String getEvolvesInto(){
		return evolvesInto;
	}
	public void setEvolvesInto(String evolvesInto){
		this.evolvesInto = evolvesInto;
	}
	public String getPronunce(){
		return pronunce;
	}
	public void setPronunce(String pronunce){
		this.pronunce = pronunce;
	}
	public int getHp(){
		return hp;
	}
	public void setHp(int hp){
		this.hp = hp;
	}
	public int getAttack(){
		return attack;
	}
	public void setAttack(int attack){
		this.attack = attack;
	}
	public int getDefence(){
		return defence;
	}
	public void setDefence(int defence){
		this.defence = defence;
	}
	public int getSpAtk(){
		return spAtk;
	}
	public void setSpAtk(int spAtk){
		this.spAtk = spAtk;
	}
	public int getSpDef(){
		return spDef;
	}
	public void setSpDef(int spDef){
		this.spDef = spDef;
	}
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public int getStatTotal(){
		return statTotal;
	}
	public void setStatTotal(int statTotal){
		this.statTotal = statTotal;
	}
	public String getSpecies(){
		return species;
	}
	public void setSpecies(String species){
		this.species = species;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getHeight(){
		return height;
	}
	public void setHeight(String height){
		this.height = height;
	}
	public String getWeight(){
		return weight;
	}
	public void setWeight(String weight){
		this.weight = weight;
	}
	public String getAbilities(){
		return abilities;
	}
	public void setAbilities(String abilities){
		this.abilities = abilities;
	}
	public String getWeakness(){
		return weakness;
	}
	public void setWeakness(String weakness){
		this.weakness = weakness;
	}
	public String getBio(){
		return bio;
	}
	public void setBio(String bio){
		this.bio = bio;
	}
	public String getLayout(){
		return layout;
	}
	public void setLayout(String layout){
		this.layout = layout;
	}
	public String getPic(){
		return pic;
	}
	public void setPic(String pic){
		this.pic = pic;
	}
	public String getIcon(){
		return icon;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getEvolutionSet(){
		return evolutionSet;
	}
	public void setEvolutionSet(String evolutionSet){
		this.evolutionSet = evolutionSet;
	}
	public String getEvolutionLayout(){
		return evolutionLayout;
	}
	public void setEvolutionLayout(String evolutionLayout){
		this.evolutionLayout = evolutionLayout;
	}
	public String getMethod(){
		return method;
	}
	public void setMethod(String method){
		this.method = method;
	}
	public ArrayList<Learnset> getLearnset(){
		return learnset;
	}
	public void setLearnset(ArrayList<Learnset> learnset){
		this.learnset.addAll(learnset);
	}
	public void setEffortsValue(EffortsValue EV){
		this.EV = EV;
	}
	public EffortsValue getEffortsValue(){
		return EV;
	}
	public void setLocation(Location location){
		this.location = location;
	}
	public Location getLocation(){
		return location;
	}
}