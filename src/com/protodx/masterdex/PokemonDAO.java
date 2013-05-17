package com.protodx.masterdex;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class PokemonDAO {
	private SQLiteDatabase database;
	private SQLengine engine;
	
	public PokemonDAO(Context context){
		engine = new SQLengine(context);
	}
	
	public void open() throws SQLException{
		database = engine.openDatabase();
	}
	
	public void close(){
		engine.close();
	}
	
	public int getDBVersion()throws Exception{
		String table = "version";
		String[] col = new String[]{"version"};
		Cursor cursor = database.query(table, col, null, null, null, null, null);
		int version = 0;
		for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
			version = cursor.getInt(cursor.getColumnIndex("version"));
		}
		return version;
	}
	
	public Pokemon getPokemon(String[] name)throws Exception{
//		String[] col = new String[]{"_id","name","evolvesFrom","evolvesInto","pronunce","hp","attack","defense",
//				"spAtk","spDef","speed","statTotal","species","type","height","weight","abilities","weakness",
//				"bio","layout","pic","icon",};
//		String table = "pokemon";
		//Cursor cursor = database.query(table, col, "name = ?", name, null, null, null);
		Cursor cursor = database.rawQuery("select pokemon._id, pokemon.name, pokemon.evolvesFrom, " +
				"pokemon.evolvesInto, pokemon.pronunce, pokemon.hp, pokemon.attack, " +
				"pokemon.defense, pokemon.spAtk, pokemon.spDef, pokemon.speed, pokemon.statTotal, " +
				"pokemon.species, pokemon.type, pokemon.height, pokemon.weight, pokemon.abilities, " +
				"pokemon.weakness, pokemon.bio, pokemon.layout, pokemon.pic, pokemon.icon, " +
				"evolutions.pokeset, evolutions.method, evolutions.evolutionlayout  from pokemon left join evolutions on " +
				"pokemon.pokeset = evolutions._id where pokemon.name = ?", name);
		
		Pokemon pokemon = new Pokemon();
		
		int id = cursor.getColumnIndex("_id");
		int pokName = cursor.getColumnIndex("name");
		int eF = cursor.getColumnIndex("evolvesFrom");
		int eT = cursor.getColumnIndex("evolvesInto");
		int pronunce = cursor.getColumnIndex("pronunce");
		int hp = cursor.getColumnIndex("hp");
		int attack = cursor.getColumnIndex("attack");
		int defense = cursor.getColumnIndex("defense");
		int spAtk = cursor.getColumnIndex("spAtk");
		int spDef = cursor.getColumnIndex("spDef");
		int speed = cursor.getColumnIndex("speed");
		int statTotal = cursor.getColumnIndex("statTotal");
		int species = cursor.getColumnIndex("species");
		int type = cursor.getColumnIndex("type");
		int height = cursor.getColumnIndex("height");
		int weight = cursor.getColumnIndex("weight");
		int abilities = cursor.getColumnIndex("abilities");
		int weakness = cursor.getColumnIndex("weakness");
		int bio = cursor.getColumnIndex("bio");
		int layout = cursor.getColumnIndex("layout");
		int pic = cursor.getColumnIndex("pic");
		int icon = cursor.getColumnIndex("icon");
		int pokeset = cursor.getColumnIndex("pokeset");
		int method = cursor.getColumnIndex("method");
		int evolutionLayout = cursor.getColumnIndex("evolutionlayout");
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			pokemon.setId(cursor.getInt(id));
			pokemon.setName(cursor.getString(pokName));
			pokemon.setEvolvesFrom(cursor.getString(eF));
			pokemon.setEvolvesInto(cursor.getString(eT));
			pokemon.setPronunce(cursor.getString(pronunce));
			pokemon.setHp((cursor.getInt(hp)));
			pokemon.setAttack(cursor.getInt(attack));
			pokemon.setDefence(cursor.getInt(defense));
			pokemon.setSpAtk(cursor.getInt(spAtk));
			pokemon.setSpDef(cursor.getInt(spDef));
			pokemon.setSpeed(cursor.getInt(speed));
			pokemon.setStatTotal(cursor.getInt(statTotal));
			pokemon.setSpecies(cursor.getString(species));
			pokemon.setType(cursor.getString(type));
			pokemon.setHeight(cursor.getString(height));
			pokemon.setWeight(cursor.getString(weight));
			pokemon.setAbilities(cursor.getString(abilities));
			pokemon.setWeakness(cursor.getString(weakness));
			pokemon.setBio(cursor.getString(bio));
			pokemon.setLayout(cursor.getString(layout));
			pokemon.setPic(cursor.getString(pic));
			pokemon.setIcon(cursor.getString(icon));
			if(cursor.isNull(pokeset))
				pokemon.setEvolutionSet("None");
			else
				pokemon.setEvolutionSet(cursor.getString(pokeset));
			if(cursor.isNull(method))
				pokemon.setMethod("None");
			else
				pokemon.setMethod(cursor.getString(method));
			if(cursor.isNull(evolutionLayout))
				pokemon.setEvolutionLayout("None");
			else
				pokemon.setEvolutionLayout(cursor.getString(evolutionLayout));
		}
		cursor.close();
		return pokemon;
	}
	public Vector<Pokemon> getPokemonList()throws Exception{
		String[] col = new String[]{"_id","name","icon"};
		String table = "pokemon";
		Cursor cursor = database.query(table, col, null, null, null, null, null);
		int _id = cursor.getColumnIndex("_id");
		int name = cursor.getColumnIndex("name");
		int icon = cursor.getColumnIndex("icon");
		Vector<Pokemon> AllPokemon = new Vector<Pokemon>();
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			Pokemon pokemon = new Pokemon();
			pokemon.setId(cursor.getInt(_id));
			pokemon.setName(cursor.getString(name));
			pokemon.setIcon(cursor.getString(icon));
			AllPokemon.add(pokemon);
		}
		cursor.close();
		return AllPokemon;
	}
	public ArrayList<Pokemon> getEevee(String[] eevee) throws Exception{
//		String[] col = new String[]{"_id","name","evolvesFrom","evolvesInto","pronunce","hp","attack","defense",
//				"spAtk","spDef","speed","statTotal","species","type","height","weight","abilities","weakness",
//				"bio","layout","pic","icon",};
//		String table = "pokemon";
		Cursor cursor = database.rawQuery("select pokemon._id, pokemon.name, pokemon.evolvesFrom, " +
				"pokemon.evolvesInto, pokemon.pronunce, pokemon.hp, pokemon.attack, " +
				"pokemon.defense, pokemon.spAtk, pokemon.spDef, pokemon.speed, pokemon.statTotal, " +
				"pokemon.species, pokemon.type, pokemon.height, pokemon.weight, pokemon.abilities, " +
				"pokemon.weakness, pokemon.bio, pokemon.layout, pokemon.pic, pokemon.icon, " +
				"evolutions.pokeset, evolutions.method, evolutions.evolutionlayout  from pokemon left join evolutions on " +
				"pokemon.pokeset = evolutions._id where pokemon.name = ?", eevee);
			
		ArrayList<Pokemon> EeveeList = new ArrayList<Pokemon>();
		int id = cursor.getColumnIndex("_id");
		int pokName = cursor.getColumnIndex("name");
		int eF = cursor.getColumnIndex("evolvesFrom");
		int eT = cursor.getColumnIndex("evolvesInto");
		int pronunce = cursor.getColumnIndex("pronunce");
		int hp = cursor.getColumnIndex("hp");
		int attack = cursor.getColumnIndex("attack");
		int defense = cursor.getColumnIndex("defense");
		int spAtk = cursor.getColumnIndex("spAtk");
		int spDef = cursor.getColumnIndex("spDef");
		int speed = cursor.getColumnIndex("speed");
		int statTotal = cursor.getColumnIndex("statTotal");
		int species = cursor.getColumnIndex("species");
		int type = cursor.getColumnIndex("type");
		int height = cursor.getColumnIndex("height");
		int weight = cursor.getColumnIndex("weight");
		int abilities = cursor.getColumnIndex("abilities");
		int weakness = cursor.getColumnIndex("weakness");
		int bio = cursor.getColumnIndex("bio");
		int layout = cursor.getColumnIndex("layout");
		int pic = cursor.getColumnIndex("pic");
		int icon = cursor.getColumnIndex("icon");
		int pokeset = cursor.getColumnIndex("pokeset");
		int method = cursor.getColumnIndex("method");
		int evolutionLayout = cursor.getColumnIndex("evolutionlayout");
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			Pokemon pokemon = new Pokemon();
			pokemon.setId(cursor.getInt(id));
			pokemon.setName(cursor.getString(pokName));
			pokemon.setEvolvesFrom(cursor.getString(eF));
			pokemon.setEvolvesInto(cursor.getString(eT));
			pokemon.setPronunce(cursor.getString(pronunce));
			pokemon.setHp((cursor.getInt(hp)));
			pokemon.setAttack(cursor.getInt(attack));
			pokemon.setDefence(cursor.getInt(defense));
			pokemon.setSpAtk(cursor.getInt(spAtk));
			pokemon.setSpDef(cursor.getInt(spDef));
			pokemon.setSpeed(cursor.getInt(speed));
			pokemon.setStatTotal(cursor.getInt(statTotal));
			pokemon.setSpecies(cursor.getString(species));
			pokemon.setType(cursor.getString(type));
			pokemon.setHeight(cursor.getString(height));
			pokemon.setWeight(cursor.getString(weight));
			pokemon.setAbilities(cursor.getString(abilities));
			pokemon.setWeakness(cursor.getString(weakness));
			pokemon.setBio(cursor.getString(bio));
			pokemon.setLayout(cursor.getString(layout));
			pokemon.setPic(cursor.getString(pic));
			pokemon.setIcon(cursor.getString(icon));
			if(cursor.isNull(pokeset))
				pokemon.setEvolutionSet("None");
			else
				pokemon.setEvolutionSet(cursor.getString(pokeset));
			if(cursor.isNull(method))
				pokemon.setMethod("None");
			else
				pokemon.setMethod(cursor.getString(method));
			if(cursor.isNull(evolutionLayout))
				pokemon.setEvolutionLayout("None");
			else
				pokemon.setEvolutionLayout(cursor.getString(evolutionLayout));
			EeveeList.add(pokemon);
		}
		cursor.close();
		return EeveeList;
		
	}
	public Pokemon getPokemonIcon(String[] name) throws Exception{
		String[] col = new String[]{"icon"};
		String table = "pokemon";
		Cursor cursor = database.query(table, col, "name = ?", name, null, null, null);
		Pokemon pokemon = new Pokemon();
		int icon = cursor.getColumnIndex("icon");
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			pokemon.setIcon(cursor.getString(icon));
		}
		cursor.close();
		return pokemon;
	}
	public Pokemon getLearnset(Pokemon pokemon) throws Exception{
		String[] col = new String[]{"_id","rby","gsc","rse","frlg","dppt","hgss","bw","move","pokemon"};
		String table = "learnset";
		String[] select = new String[]{String.valueOf(pokemon.getId())};
		Cursor cursor = database.query(table, col, "pokemon = ?", select, null, null, "bw");
		ArrayList<Learnset> listLearnset = new ArrayList<Learnset>();
		int _id = cursor.getColumnIndex("_id");
		int rby = cursor.getColumnIndex("rby");
		int gsc = cursor.getColumnIndex("gsc");
		int rse = cursor.getColumnIndex("rse");
		int frlg = cursor.getColumnIndex("frlg");
		int dppt = cursor.getColumnIndex("dppt");
		int hgss = cursor.getColumnIndex("hgss");
		int bw = cursor.getColumnIndex("bw");
		int move = cursor.getColumnIndex("move");
		int pokemonId = cursor.getColumnIndex("pokemon");
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			Learnset learnset = new Learnset();
			learnset.setID(cursor.getInt(_id));
			learnset.setRBY(cursor.getInt(rby));
			learnset.setGSC(cursor.getInt(gsc));
			learnset.setRSE(cursor.getInt(rse));
			learnset.setFRLG(cursor.getInt(frlg));
			learnset.setDPPT(cursor.getInt(dppt));
			learnset.setHGSS(cursor.getInt(hgss));
			learnset.setBW(cursor.getInt(bw));
			learnset.setMove(cursor.getString(move));
			learnset.setPokemonId(cursor.getInt(pokemonId));
			listLearnset.add(learnset);
		}
		pokemon.setLearnset(listLearnset);
		return pokemon;
	}
	public Pokemon getPokemonById(String[] _id) throws Exception{
		//String[] col = new String[]{"_id","name","evolvesFrom","evolvesInto","pronunce","hp","attack","defense",
		//		"spAtk","spDef","speed","statTotal","species","type","height","weight","abilities","weakness",
		//		"bio","layout","pic","icon",};
		//String table = "pokemon";
		//Cursor cursor = database.query(table, col, "_id = ?", _id, null, null, null);
		Cursor cursor = database.rawQuery("select pokemon._id, pokemon.name, pokemon.evolvesFrom, " +
				"pokemon.evolvesInto, pokemon.pronunce, pokemon.hp, pokemon.attack, " +
				"pokemon.defense, pokemon.spAtk, pokemon.spDef, pokemon.speed, pokemon.statTotal, " +
				"pokemon.species, pokemon.type, pokemon.height, pokemon.weight, pokemon.abilities, " +
				"pokemon.weakness, pokemon.bio, pokemon.layout, pokemon.pic, pokemon.icon, " +
				"evolutions.pokeset, evolutions.method, evolutions.evolutionlayout  from pokemon left join evolutions on " +
				"pokemon.pokeset = evolutions._id where pokemon._id = ?", _id);
		Pokemon pokemon = new Pokemon();
		
		int id = cursor.getColumnIndex("_id");
		int pokName = cursor.getColumnIndex("name");
		int eF = cursor.getColumnIndex("evolvesFrom");
		int eT = cursor.getColumnIndex("evolvesInto");
		int pronunce = cursor.getColumnIndex("pronunce");
		int hp = cursor.getColumnIndex("hp");
		int attack = cursor.getColumnIndex("attack");
		int defense = cursor.getColumnIndex("defense");
		int spAtk = cursor.getColumnIndex("spAtk");
		int spDef = cursor.getColumnIndex("spDef");
		int speed = cursor.getColumnIndex("speed");
		int statTotal = cursor.getColumnIndex("statTotal");
		int species = cursor.getColumnIndex("species");
		int type = cursor.getColumnIndex("type");
		int height = cursor.getColumnIndex("height");
		int weight = cursor.getColumnIndex("weight");
		int abilities = cursor.getColumnIndex("abilities");
		int weakness = cursor.getColumnIndex("weakness");
		int bio = cursor.getColumnIndex("bio");
		int layout = cursor.getColumnIndex("layout");
		int pic = cursor.getColumnIndex("pic");
		int icon = cursor.getColumnIndex("icon");
		int pokeset = cursor.getColumnIndex("pokeset");
		int method = cursor.getColumnIndex("method");
		int evolutionLayout = cursor.getColumnIndex("evolutionlayout");
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			pokemon.setId(cursor.getInt(id));
			pokemon.setName(cursor.getString(pokName));
			pokemon.setEvolvesFrom(cursor.getString(eF));
			pokemon.setEvolvesInto(cursor.getString(eT));
			pokemon.setPronunce(cursor.getString(pronunce));
			pokemon.setHp((cursor.getInt(hp)));
			pokemon.setAttack(cursor.getInt(attack));
			pokemon.setDefence(cursor.getInt(defense));
			pokemon.setSpAtk(cursor.getInt(spAtk));
			pokemon.setSpDef(cursor.getInt(spDef));
			pokemon.setSpeed(cursor.getInt(speed));
			pokemon.setStatTotal(cursor.getInt(statTotal));
			pokemon.setSpecies(cursor.getString(species));
			pokemon.setType(cursor.getString(type));
			pokemon.setHeight(cursor.getString(height));
			pokemon.setWeight(cursor.getString(weight));
			pokemon.setAbilities(cursor.getString(abilities));
			pokemon.setWeakness(cursor.getString(weakness));
			pokemon.setBio(cursor.getString(bio));
			pokemon.setLayout(cursor.getString(layout));
			pokemon.setPic(cursor.getString(pic));
			pokemon.setIcon(cursor.getString(icon));
			if(cursor.isNull(pokeset))
				pokemon.setEvolutionSet("None");
			else
				pokemon.setEvolutionSet(cursor.getString(pokeset));
			if(cursor.isNull(method))
				pokemon.setMethod("None");
			else
				pokemon.setMethod(cursor.getString(method));
			if(cursor.isNull(evolutionLayout))
				pokemon.setEvolutionLayout("None");
			else
				pokemon.setEvolutionLayout(cursor.getString(evolutionLayout));
		}
		cursor.close();
		return pokemon;
	}
	public Vector<Trainer> getTrainerList(){
		Vector<Trainer> mTrainerList = new Vector<Trainer>();
		String[] mCol = new String[]{"_id","name","icon"};
		String mTable = "trainers";
		Cursor mCursor = database.query(mTable, mCol, null, null, null, null, null);
		
		int m_id = mCursor.getColumnIndex("_id");
		int mName = mCursor.getColumnIndex("name");
		int mIcon = mCursor.getColumnIndex("icon");
		
		for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
			Trainer trainer = new Trainer();
			trainer.setId(mCursor.getInt(m_id));
			trainer.setName(mCursor.getString(mName));
			trainer.setIcon(mCursor.getString(mIcon));
			mTrainerList.add(trainer);
		}
		return mTrainerList;
	}
	public ArrayList<TrainedPokemon> getTrainedPokemonByTrainer(String[] name){
		ArrayList<TrainedPokemon> list = new ArrayList<TrainedPokemon>();
//		String[] col = new String[]{"_id","pokemon","leader","level","moves","generation","match"};
//		String table = "leaders_pokemon";
		//Cursor cursor = database.query(table, col, "leader=?", name, null, null, null);
		
		Cursor cursor = database.rawQuery("select leaders_pokemon._id,leaders_pokemon.pokemon," +
				"leaders_pokemon.leader,leaders_pokemon.level,leaders_pokemon.moves," +
				"leaders_pokemon.generation,leaders_pokemon.match, pokemon.icon from leaders_pokemo" +
				"n join pokemon on leaders_pokemon.pokemon = pokemon.name where leader = ?", name);
		
		int _id = cursor.getColumnIndex("_id");
		int pokemon = cursor.getColumnIndex("pokemon");
		int leader = cursor.getColumnIndex("leader");
		int level = cursor.getColumnIndex("level");
		int moves = cursor.getColumnIndex("moves");
		int generation = cursor.getColumnIndex("generation");
		int match = cursor.getColumnIndex("match");
		int icon = cursor.getColumnIndex("icon");
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			TrainedPokemon tp = new TrainedPokemon();
			tp.setId(cursor.getInt(_id));
			tp.setPokemon(cursor.getString(pokemon));
			tp.setLeader(cursor.getString(leader));
			tp.setLevel(cursor.getInt(level));
			tp.setMoves(cursor.getString(moves));
			tp.setGeneration(cursor.getString(generation));
			tp.setMatch(cursor.getInt(match));
			tp.setIcon(cursor.getString(icon));
			list.add(tp);
		}
		cursor.close();
		return list;
	}
	public Trainer getTrainer(String[] name){
		Trainer trainer = new Trainer();
		String[] col = new String[]{"_id","name","age","hometown","region","badge","preferred_type"};
		String table = "trainers";
		Cursor cursor = database.query(table, col, "name = ?", name, null, null, null);
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
			trainer.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			trainer.setName(cursor.getString(cursor.getColumnIndex("name")));
			trainer.setAge(cursor.getInt(cursor.getColumnIndex("age")));
			trainer.setHometown(cursor.getString(cursor.getColumnIndex("hometown")));
			trainer.setRegion(cursor.getString(cursor.getColumnIndex("region")));
			trainer.setBadge(cursor.getString(cursor.getColumnIndex("badge")));
			trainer.setPreferredType(cursor.getString(cursor.getColumnIndex("preferred_type")));
		}
		cursor.close();
		return trainer;
	}
	public Vector<Move> getMoveList(){
		Vector<Move> mTrainerList = new Vector<Move>();
		String[] mCol = new String[]{"_id","name","type"};
		String mTable = "moves";
		Cursor mCursor = database.query(mTable, mCol, null, null, null, null, null);
		
		int m_id = mCursor.getColumnIndex("_id");
		int mName = mCursor.getColumnIndex("name");
		int mType = mCursor.getColumnIndex("type");
		
		for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
			Move move = new Move();
			move.setId(mCursor.getInt(m_id));
			move.setName(mCursor.getString(mName));
			move.setType(mCursor.getString(mType));
			mTrainerList.add(move);
		}
		return mTrainerList;
	}
	public Move getMove(String[] name){
		Move move = new Move();
		String[] mCol = new String[]{"_id","name","type","category","power","accuracy","PP","TMHM","effect","probability"};
		String mTable = "moves";
		Cursor mCursor = database.query(mTable, mCol, "name = ?", name, null, null, null);
		
		for(mCursor.moveToFirst();!mCursor.isAfterLast();mCursor.moveToNext()){
			move.setId(mCursor.getInt(mCursor.getColumnIndex("_id")));
			move.setName(mCursor.getString(mCursor.getColumnIndex("name")));
			move.setType(mCursor.getString(mCursor.getColumnIndex("type")));
			move.setCategory(mCursor.getString(mCursor.getColumnIndex("category")));
			move.setPower(mCursor.getInt(mCursor.getColumnIndex("power")));
			move.setAccuracy(mCursor.getInt(mCursor.getColumnIndex("accuracy")));
			move.setPP(mCursor.getInt(mCursor.getColumnIndex("PP")));
			move.setTmHm(mCursor.getString(mCursor.getColumnIndex("TMHM")));
			move.setEffect(mCursor.getString(mCursor.getColumnIndex("effect")));
			move.setProbability(mCursor.getInt(mCursor.getColumnIndex("probability")));
		}
		return move;
	}
	/*This is a decorator pattern*/
	public Pokemon getEffortsValue(Pokemon pokemon) throws Exception{
		String table = "ev";
		String[] name = new String[]{pokemon.getName()};
		String[] col = new String[]{"hp","attack","defense","special_attack","special_defense","speed"};
		Cursor cursor = database.query(table, col, "pokemon = ?", name, null, null, null);
		
		for(cursor.moveToFirst(); !cursor.isAfterLast();cursor.moveToNext()){
			EffortsValue ev = new EffortsValue();
			ev.setHP(cursor.getInt(cursor.getColumnIndex("hp")));
			ev.setAttack(cursor.getInt(cursor.getColumnIndex("attack")));
			ev.setDefense(cursor.getInt(cursor.getColumnIndex("defense")));
			ev.setSpecialAtk(cursor.getInt(cursor.getColumnIndex("special_attack")));
			ev.setSpecialDef(cursor.getInt(cursor.getColumnIndex("special_defense")));
			ev.setSpeed(cursor.getInt(cursor.getColumnIndex("speed")));
			pokemon.setEffortsValue(ev);
		}
		return pokemon;
	}
	public Pokemon getLocations(Pokemon pokemon) throws Exception{
		String table = "locations";
		String[] col = new String[]{"_id","rb","y","gs","c","rs","frlg","e","dp","p","hgss","bw"};
		Cursor cursor = database.query(table, col, "_id = ?", new String[]{String.valueOf(pokemon.getId())}, null, null, null);
		
		for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
			Location location = new Location();
			location.setId(cursor.getInt(cursor.getColumnIndex("_id")));
			location.setRedBlue(cursor.getString(cursor.getColumnIndex("rb")));
			location.setYellow(cursor.getString(cursor.getColumnIndex("y")));
			location.setGoldSilver(cursor.getString(cursor.getColumnIndex("gs")));
			location.setCrystal(cursor.getString(cursor.getColumnIndex("c")));
			location.setRubySapphire(cursor.getString(cursor.getColumnIndex("rs")));
			location.setFireRedLeafGreen(cursor.getString(cursor.getColumnIndex("frlg")));
			location.setEmerald(cursor.getString(cursor.getColumnIndex("e")));
			location.setDiamondPearl(cursor.getString(cursor.getColumnIndex("dp")));
			location.setPlatinum(cursor.getString(cursor.getColumnIndex("p")));
			location.setHeartGoldSoulSilver(cursor.getString(cursor.getColumnIndex("hgss")));
			location.setBlackWhite(cursor.getString(cursor.getColumnIndex("bw")));
			pokemon.setLocation(location);
		}
		return pokemon;
	}
}