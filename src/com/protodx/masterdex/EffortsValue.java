package com.protodx.masterdex;

public class EffortsValue {
	private int HP;
	private int Attack;
	private int Defense;
	private int SpecialAtk;
	private int SpecialDef;
	private int Speed;
	
	public void setHP(int HP){
		this.HP = HP;
	}
	public void setAttack(int Attack){
		this.Attack = Attack;
	}
	public void setDefense(int Defense){
		this.Defense = Defense;
	}
	public void setSpecialAtk(int SpecialAtk){
		this.SpecialAtk = SpecialAtk;
	}
	public void setSpecialDef(int SpecialDef){
		this.SpecialDef = SpecialDef;
	}
	public void setSpeed(int Speed){
		this.Speed = Speed;
	}
	public int getHP(){
		return HP;
	}
	public int getAttack(){
		return Attack;
	}

	public int getDefense(){
		return Defense;
	}

	public int getSpecialAtk(){
		return SpecialAtk;
	}

	public int getSpecialDef(){
		return SpecialDef;
	}

	public int getSpeed(){
		return Speed;
	}


}
