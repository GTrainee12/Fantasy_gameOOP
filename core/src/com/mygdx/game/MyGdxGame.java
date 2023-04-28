package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Fantasy_game.Main;

import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon,archer,crossbowman,knight,monk,militiaman,spearman,spectrum;

	Music song;

	Main start ;
	
	@Override
	public void create () {

		Main start = new Main();
		start.setTeams(10);
		start.setTurnOrder();
		batch = new SpriteBatch();
		fon = new Texture("фоны/"+Fons.values()[new Random().nextInt(Fons.values().length)]+".png");
		song = Gdx.audio.newMusic(Gdx.files.internal("Музыка/"+Song.values()[new Random().nextInt(Fons.values().length)]+".mp3"));
		song.setLooping(true);
		song.setVolume(0.125f);
		song.play();


		crossbowman = new Texture("персонажи/CrossBowMan.png");
		archer = new Texture("персонажи/Archer.png");
		knight = new Texture("персонажи/Knight.png");
		monk = new Texture("персонажи/Monk.png");
		militiaman = new Texture("персонажи/Peasant.png");
		spearman =  new Texture("персонажи/SpearMan.png");
		spectrum = new Texture("персонажи/Spectrum.png");
	}

	@Override
	public void render () {
		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
			start.gameTurn();
			start.setTurnOrder();
			start.turnCount++;
			Gdx.graphics.setTitle(String.valueOf("Ход № "+ start.turnCount));
		}
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(fon, 0, 0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());




		for(int i = start.teamSize-1; i>=0; i--){
			int x = start.group_elves.get(i).Vector2D.posX * Gdx.graphics.getHeight()/120;
			int y = (start.group_elves.get(i).Vector2D.posY-1) * Gdx.graphics.getHeight()/12;
			switch (start.group_elves.get(i).getInfo()){
				case "Archer: ":
					batch.draw(archer,x,y);
					break;
				case "Knight: ":
					batch.draw(knight,x,y);
					break;
				case "Spectrum: ":
					batch.draw(spectrum,x,y);
					break;
				case "Militiaman: ":
					batch.draw(militiaman,x,y);
					break;
			}
		}for(int i = start.teamSize-1; i>=0; i--){
			int x = start.group_people.get(i).Vector2D.posX * Gdx.graphics.getHeight()/12;
			int y = (start.group_people.get(i).Vector2D.posY-1) * Gdx.graphics.getHeight()/12;
			switch (start.group_people.get(i).getInfo()){
				case "Crossbowman: ":
					Sprite sprite = new Sprite(crossbowman);
					sprite.setPosition(x,y);
					sprite.flip(true,false);
					sprite.draw(batch);
					break;
				case "Spearman: ":
					sprite = new Sprite(spearman);
					sprite.setPosition(x,y);
					sprite.flip(true,false);
					sprite.draw(batch);
					break;
				case "Monk: ":
					sprite = new Sprite(monk);
					sprite.setPosition(x,y);
					sprite.flip(true,false);
					sprite.draw(batch);
					break;
				case "Militiaman: ":
					sprite = new Sprite(militiaman);
					sprite.setPosition(x,y);
					sprite.flip(true,false);
					sprite.draw(batch);
					break;
			}
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
	}
}
