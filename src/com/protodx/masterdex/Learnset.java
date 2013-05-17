package com.protodx.masterdex;

public class Learnset {
	private int _id;
	private int rby;
	private int gsc;
	private int rse;
	private int frlg;
	private int dppt;
	private int hgss;
	private int bw;
	private String move;
	private int pokemonId;
	
	public int getID(){
		return _id;
	}
	public int getRBY(){
		return rby;
	}
	public int getGSC(){
		return gsc;
	}
	public int getRSE(){
		return rse;
	}
	public int getFRLG(){
		return frlg;
	}
	public int getDPPT(){
		return dppt;
	}
	public int getHGSS(){
		return hgss;
	}
	public int getBW(){
		return bw;
	}
	public String getMove(){
		return move;
	}
	public int getPokemonID(){
		return pokemonId;
	}

	public void setID(int _id){
		this._id = _id;
	}
	public void setRBY(int rby){
		this.rby = rby;
	}
	public void setGSC(int gsc){
		this.gsc = gsc;
	}
	public void setRSE(int rse){
		this.rse = rse;
	}
	public void setFRLG(int frlg){
		this.frlg = frlg;
	}
	public void setDPPT(int dppt){
		this.dppt = dppt;
	}
	public void setHGSS(int hgss){
		this.hgss = hgss;
	}
	public void setBW(int bw){
		this.bw = bw;
	}
	public void setMove(String move){
		this.move = move;
	}
	public void setPokemonId(int pokemonId){
		this.pokemonId = pokemonId;
	}
}
