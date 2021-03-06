package com.samdlc.blackjack.hud;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import com.samdlc.blackjack.core.GameWindow;
import com.samdlc.blackjack.gamestate.GameState;

public abstract class HUDState {
	protected ArrayList<ActionButton> buttons;
	final GameState gameState;

	public final GameState getGameState() {
		return this.gameState;
	}
	
	public HUDState(GameState gameState) {
		this.buttons = new ArrayList<ActionButton>();
		this.gameState = gameState;
		this.buttons.add(new ActionButton("Quit", 5, 5, 80, 40, new Action() {

			@Override
			public void handle(ActionButton a) {
				HUDState.this.getGameState().handleAction("QUIT", null);
			}
			
		}));
	}
	
	public void render(Graphics2D g) {
		for(ActionButton button : buttons) {
			button.render(g);
		}
	}
	
	public void handleClick(Point p) {
		for(ActionButton button : buttons) {
			if(button.contains(p)) {
				button.click();
			}
		}
	}

	public void handleHover(Point p) {
		boolean hovering = false;
		for (ActionButton button : buttons) {
			if(button.contains(p) && button.isEnabled()) {
				button.hover(true);
				hovering = hovering | true;
			} else {
				hovering = hovering | false;
				button.hover(false);
			}
		}
		int cType = hovering ? Cursor.HAND_CURSOR : Cursor.DEFAULT_CURSOR;
		GameWindow.window.getRootPane().setCursor(new Cursor(cType));
		
	}
	
	public void handleAction(String action, Object data) {
		
	}
}
