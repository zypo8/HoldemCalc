package com.holdem.calc;


import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.holdem.calc.entities.Button;
import com.holdem.calc.entities.Card;
import com.holdem.calc.entities.CardSelectEntity;
import com.holdem.calc.entities.CardSlot;
import com.holdem.calc.entities.HandsContainer;
import com.holdem.calc.systems.InputManagerSystem;
import com.holdem.calc.systems.RenderSystem;

import java.util.ArrayList;

public class MyGdxGame extends Game {
	public static SpriteBatch batch;
	public static Engine engine;
	public static CardSelectEntity cardSelectEntity;
	public static ArrayList<Card> cards = new ArrayList<>(52);
	public static final String[] CARD_TYPES= new String[]{"hearts", "tiles", "clovers", "pikes"};
	public static final String[] CARD_LETTERS= new String[]{"H", "T", "C", "P"};
	public static final String[] CARDS = { "2P", "3P", "4P", "5P", "6P", "7P", "8P", "9P", "10P", "11P", "12P", "13P","14P",
			"2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "11C", "12C", "13C","14C",
			"2T", "3T", "4T", "5T", "6T", "7T", "8T", "9T", "10T", "11T", "12T", "13T","14T",
			"2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "11H", "12H", "13H", "14H"};
	public static HandsContainer winningHandsContainer;
	public static HandsContainer losingHandsContainer;


	private class TableScreen implements Screen {
		private OrthographicCamera orthographicCamera;
		private ScreenViewport screenViewport;
		private InputMultiplexer ip;
		private InputManagerSystem inputManagerSystem;
		private Sprite backGroundSprite;

		public TableScreen(){
			//Gdx.app.setLogLevel(3);
			orthographicCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			screenViewport = new ScreenViewport(orthographicCamera);
			engine = new Engine();
			inputManagerSystem = new InputManagerSystem(orthographicCamera);
			ip = new InputMultiplexer(inputManagerSystem);
			cardSelectEntity = new CardSelectEntity(32, Gdx.graphics.getHeight()-525);

		}

		@Override
		public void show() {
			Gdx.input.setInputProcessor(ip);
			orthographicCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
			backGroundSprite = new Sprite(new Texture(Gdx.files.internal("TableBG.png")));
			//backGroundSprite.setRegionWidth(Gdx.graphics.getWidth());
			//backGroundSprite.setRegionHeight(Gdx.graphics.getHeight());

			engine.addSystem(new RenderSystem(batch));
			engine.addSystem(inputManagerSystem);
			engine.addEntity(cardSelectEntity);

			CardSlot handCard1 = new CardSlot(140, 125, CardSlot.SlotType.Hand);
			engine.addEntity(handCard1);

			CardSlot handCard2 = new CardSlot(210, 125, CardSlot.SlotType.Hand);
			engine.addEntity(handCard2);

			CardSlot tableCard1 = new CardSlot(120, 300, CardSlot.SlotType.Table);
			engine.addEntity(tableCard1);

			CardSlot tableCard2 = new CardSlot(120 + 70*1, 300, CardSlot.SlotType.Table);
			engine.addEntity(tableCard2);

			CardSlot tableCard3 = new CardSlot(120+ 70*2, 300, CardSlot.SlotType.Table);
			engine.addEntity(tableCard3);

			CardSlot tableCard4 = new CardSlot(120+ 70*3, 300, CardSlot.SlotType.Table);
			engine.addEntity(tableCard4);

			CardSlot tableCard5 = new CardSlot(120 + 70*4, 300, CardSlot.SlotType.Table);
			engine.addEntity(tableCard5);

			Button calcBTN = new Button(32, 32);
			engine.addEntity(calcBTN);

			for (int i = 0; i < 52; i++) {
				Card card = new Card("deck/" + CARDS[i] + ".png");
				cards.add(card);
				engine.addEntity(card);
			}
			winningHandsContainer = new HandsContainer(HandsContainer.HandsContainerType.Win);
			engine.addEntity(winningHandsContainer);
			losingHandsContainer = new HandsContainer(HandsContainer.HandsContainerType.Lose);
			engine.addEntity(losingHandsContainer);


		}

		@Override
		public void render(float delta) {
			ScreenUtils.clear(0, 0, 0, 1);
			batch.begin();
			backGroundSprite.draw(batch);
			batch.end();
			engine.update(Gdx.graphics.getDeltaTime());
			orthographicCamera.update();
			batch.setProjectionMatrix(orthographicCamera.combined);

		}

		@Override
		public void resize(int width, int height) {
			screenViewport.update(width, height);
			orthographicCamera.setToOrtho(false, width, height);

		}

		@Override
		public void pause() {
			//cardSelectEntity.show();
		}

		@Override
		public void resume() {

		}

		@Override
		public void hide() {

		}

		@Override
		public void dispose() {

		}
	}


	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new TableScreen());
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

}
