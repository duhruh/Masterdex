package com.protodx.masterdex;

public class Trainer {
	private int _id;
	private String name;
	private int age;
	private String hometown;
	private String region;
	private String badge;
	private String preferredType;
	private String icon;
	
	public void setId(int _id){
		this._id = _id;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public void setHometown(String hometown){
		this.hometown = hometown;
	}
	public void setRegion(String region){
		this.region = region;
	}
	public void setBadge(String badge){
		this.badge = badge;
	}
	public void setPreferredType(String preferredType){
		this.preferredType = preferredType;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public int getId(){
		return _id;
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public String getHometown(){
		return hometown;
	}
	public String getRegion(){
		return region;
	}
	public String getBadge(){
		return badge;
	}
	public String getPreferredType(){
		return preferredType;
	}
	public String getIcon(){
		return icon;
	}
}
