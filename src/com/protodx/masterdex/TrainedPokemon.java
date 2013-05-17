package com.protodx.masterdex;

public class TrainedPokemon {
	private int _id;
	private String pokemon;
	private String leader;
	private int level;
	private String moves;
	private String generation;
	private int match;
	private String icon;
	
	public void setId(int _id){
		this._id = _id;
	}
	public int getId(){
		return _id;
	}
	public void setPokemon(String pokemon){
		this.pokemon = pokemon;
	}
	public String getPokemon(){
		return pokemon;
	}
	public void setLeader(String leader){
		this.leader = leader;
	}
	public String getLeader(){
		return leader;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public int getLevel(){
		return level;
	}
	public void setMoves(String moves){
		this.moves = moves;
	}
	public String getMoves(){
		return moves;
	}
	public void setGeneration(String generation){
		this.generation = generation;
	}
	public String getGeneration(){
		return generation;
	}
	public void setMatch(int match){
		this.match = match;
	}
	public int getMatch(){
		return match;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getIcon(){
		return icon;
	}

}
