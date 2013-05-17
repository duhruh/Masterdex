package com.protodx.masterdex;

public class Move {
	private int _id;
	private String name;
	private String type;
	private String category;
	private int power;
	private int accuracy;
	private int pp;
	private String tmhm;
	private String effect;
	private int probability;
	
	public void setId(int _id){
		this._id = _id;
	}
	public int getId(){
		return _id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
	public void setCategory(String category){
		this.category = category;
	}
	public String getCategory(){
		return category;
	}
	public void setPower(int power){
		this.power = power;
	}
	public int getPower(){
		return power;
	}
	public void setAccuracy(int accuracy){
		this.accuracy = accuracy;
	}
	public int getAccuracy(){
		return accuracy;
	}
	public void setPP(int pp){
		this.pp = pp;
	}
	public int getPP(){
		return pp;
	}
	public void setTmHm(String tmhm){
		this.tmhm = tmhm;
	}
	public String getTmHm(){
		return tmhm;
	}
	public void setEffect(String effect){
		this.effect = effect;
	}
	public String getEffect(){
		return effect;
	}
	public void setProbability(int probability){
		this.probability = probability;
	}
	public int getProbability(){
		return probability;
	}
}
