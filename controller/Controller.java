package controller;

import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.music.MusicPlayer;
import model.snapshot.FileManager;
import model.snapshot.Memento;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eg.edu.alexu.csd.oop.view.GameScreen;
import eg.edu.alexu.csd.oop.view.MainMenu;

@SuppressWarnings("serial")
public class Controller extends JFrame {

	public static void main(final String[] args) {
		new Controller();
	}

	private CardLayout cardLayout;
	private JPanel cardPanel;
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	private final String MAIN_MENU = "Main Menu";
	private final String GAME_SCREEN = "Game Screen";
	private boolean gameRunning;
	private FileManager fileManager;
	private final Logger log = LogManager.getLogger(getClass());

	public Controller() {
		super("Super Mario");

		cardLayout = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(cardLayout);

		mainMenu = new MainMenu(this);
		gameScreen = new GameScreen(this);
		addKeyListener(gameScreen);
		addKeyListener(gameScreen.getPlayerOneKeyListener());
		addKeyListener(gameScreen.getPlayerTwoKeyListener());

		cardPanel.add(mainMenu, MAIN_MENU);
		cardPanel.add(gameScreen, GAME_SCREEN);
		add(cardPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);

		new MusicPlayer();
		fileManager = new FileManager();

		showMainMenu();
	}

	public void exit() {
		log.info("Game closed.");
		System.exit(0);
	}

	private int getDifficultySpeed(final String difficulty) {
		if (difficulty.equalsIgnoreCase("easy")) {
			return 0;
		} else if (difficulty.equalsIgnoreCase("medium")) {
			return 1;
		} else if (difficulty.equalsIgnoreCase("hard")) {
			return 2;
		}
		return 0;
	}

	public void loadGame() {
		Memento memento = fileManager.load();
		if (memento != null) {
			gameScreen.loadGame(memento);
			cardLayout.show(cardPanel, GAME_SCREEN);
			gameRunning = true;
			gameScreen.pauseGame();
		}
		log.info("Game loaded.");
	}

	public void saveGame(final Memento memento) {
		fileManager.save(memento);
		requestFocus();
		log.info("Game saved.");
	}

	public void showMainMenu() {
		log.info("Main menu is shown.");
		cardLayout.show(cardPanel, MAIN_MENU);
		gameRunning = false;
		while (!gameRunning) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		log.info("Game started.");
		requestFocus();
		gameScreen.startGame();
	}

	public void startGame(final Image playerOne, final Image playerTwo,
			final String playerOneName, final String playerTwoName,
			final String difficulty) {
		gameScreen.resetPlayers(playerOne, playerTwo, playerOneName, playerTwoName);
		gameScreen.resetScoreBoard();
		gameScreen.resetTimer();
		gameScreen.setDifficulty(getDifficultySpeed(difficulty));
		cardLayout.show(cardPanel, GAME_SCREEN);
		gameRunning = true;
	}

}
