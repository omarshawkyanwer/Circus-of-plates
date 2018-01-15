package eg.edu.alexu.csd.oop.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.game.FallingShapesManager;
import model.game.IScoreBoard;
import model.game.ITimer;
import model.game.Player;
import model.game.ScoreBoard;
import model.game.Timer;
import model.shapes.Shape;
import model.shapes.ShapesLoader;
import model.snapshot.Memento;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.Controller;

@SuppressWarnings("serial")
public class GameScreen extends JPanel implements KeyListener {

	private Controller controller;
	private ArrayList<Shape> drops;
	private FallingShapesManager shapesManager;
	private Player playerOne, playerTwo;
	private ITimer timer;
	private IScoreBoard scoreBoard;
	private boolean initializedVariables, gameRunning, gamePaused;
	private JButton mainMenu, exit, save;
	private Image background;
	private int loserId;
	private final Logger log = LogManager.getLogger(getClass());

	public GameScreen(final Controller controller) {
		this.controller = controller;
		setLayout(null);
		setPreferredSize(new Dimension(1200, 600));
		initButtons();
		initCreator();

		playerOne = new Player(1, 880, 480, 130, 1200, drops, this);
		playerTwo = new Player(2, 180, 480, 130, 1200, drops, this);
		background = new ImageIcon(getClass().getResource(
				"/images/background.png")).getImage();

		initializedVariables = true;
	}

	private void createExitButton() {
		exit = new JButton();
		exit.setVisible(false);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		Icon butIcon = new ImageIcon(getClass().getResource("/images/exit.png"));
		exit.setIcon(butIcon);
		exit.setBounds(560, 300, 80, 50);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.exit();
			}
		});
		add(exit);
	}

	private void createMainMenuButton() {
		mainMenu = new JButton();
		mainMenu.setVisible(false);
		mainMenu.setContentAreaFilled(false);
		mainMenu.setBorderPainted(false);
		Icon butIcon = new ImageIcon(getClass().getResource(
				"/images/main menu.png"));
		mainMenu.setBounds(518, 200, 164, 50);
		mainMenu.setIcon(butIcon);
		mainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				drops.clear();
				loserId = -1;
				resumeGame();
				gameRunning = false;
			}
		});
		add(mainMenu);
	}

	private void createSaveButton() {
		save = new JButton();
		save.setVisible(false);
		save.setContentAreaFilled(false);
		save.setBorderPainted(false);
		Icon butIcon = new ImageIcon(getClass().getResource("/images/save.png"));
		save.setIcon(butIcon);
		save.setBounds(550, 250, 100, 50);
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				saveGame();
			}
		});
		add(save);
	}

	private void declareDraw() {
		log.info("Game ended in draw.");
		Image drawImg = new ImageIcon(getClass()
				.getResource("/images/draw.png")).getImage();
		getGraphics().drawImage(drawImg, 800, 100, 300, 300, null, null);
		getGraphics().drawImage(drawImg, 100, 100, 300, 300, null, null);
	}

	private void declarePlayerOneWinner() {
		log.info("Player one won.");
		Image winImg = new ImageIcon(getClass().getResource("/images/win.png"))
				.getImage();
		getGraphics().drawImage(winImg, 800, 100, 300, 300, null, null);
		Font font = new Font("Garamond", Font.BOLD, 80);
		Graphics2D g = (Graphics2D) getGraphics();
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Player One", 780, 450);
	}

	private void declarePlayerTwoWinner() {
		log.info("Player two won.");
		Image winImg = new ImageIcon(getClass().getResource("/images/win.png"))
				.getImage();
		getGraphics().drawImage(winImg, 100, 100, 300, 300, null, null);
		Font font = new Font("Garamond", Font.BOLD, 80);
		Graphics2D g = (Graphics2D) getGraphics();
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawString("Player Two", 80, 450);
	}

	private void declareWinner() {
		if (loserId == 0) {
			int winner = scoreBoard.getWinnerId();
			if (winner == 1) {
				declarePlayerOneWinner();
			} else if (winner == 2) {
				declarePlayerTwoWinner();
			} else {
				declareDraw();
			}
		} else {
			if (loserId == 1) {
				declarePlayerTwoWinner();
			} else {
				declarePlayerOneWinner();
			}
		}
		pauseGame();
	}

	public void endGame(final int loserId) {
		this.loserId = loserId;
	}

	private Image getPlayerImage(final String playerName) {
		if (playerName.equalsIgnoreCase("mario")) {
			return new ImageIcon(getClass().getResource("/images/mario.png"))
					.getImage();
		} else if (playerName.equalsIgnoreCase("luigi")) {
			return new ImageIcon(getClass().getResource("/images/luigi.png"))
					.getImage();
		} else {
			return new ImageIcon(getClass()
					.getResource("/images/bowser jr.png")).getImage();
		}
	}

	public KeyListener getPlayerOneKeyListener() {
		return playerOne;
	}

	public KeyListener getPlayerTwoKeyListener() {
		return playerTwo;
	}

	private void initButtons() {
		createMainMenuButton();
		createSaveButton();
		createExitButton();
	}

	private void initCreator() {
		drops = new ArrayList<Shape>();
		ShapesLoader loader = new ShapesLoader();
		if (!loader.didLoadShapesFail()) {
			shapesManager = new FallingShapesManager(drops, loader.getShapes(),
					600);
		} else {
			System.out.println("Failed to load shapes.");
			controller.exit();
		}
	}

	@Override
	public void keyPressed(final KeyEvent arg0) {
		if (arg0.getKeyCode() == 27) {
			if (gamePaused) {
				resumeGame();
			} else {
				pauseGame();
			}
		}
	}

	@Override
	public void keyReleased(final KeyEvent arg0) {
		arg0.consume();
	}

	@Override
	public void keyTyped(final KeyEvent arg0) {
		arg0.consume();
	}

	public void loadGame(final Memento memento) {
		playerOne.setName(memento.getPlayerOneName());
		playerTwo.setName(memento.getPlayerTwoName());
		playerOne.setImage(getPlayerImage(playerOne.getName()));
		playerTwo.setImage(getPlayerImage(playerTwo.getName()));

		timer = new Timer(memento.getDuration(), this);
		timer.setCurrentTime(memento.getCurrentTime());

		Image[] playerImages = new Image[3];
		playerImages[1] = playerOne.getImage();
		playerImages[2] = playerTwo.getImage();
		scoreBoard = new ScoreBoard(2, playerImages);
		scoreBoard.setLevels(memento.getLevels());
		scoreBoard.setScores(memento.getScores());

		drops = memento.getDrops();
		shapesManager.setDrops(drops);

		playerOne.setDrops(drops);
		playerOne.setX(memento.getPlayerOneX());
		playerOne.setLevel(memento.getPlayerOneLevel());
		playerOne.setScore(memento.getPlayerOneScore());
		playerOne.setLeftPile(memento.getPlayerOneLeftPile());
		playerOne.setRightPile(memento.getPlayerOneRightPile());

		playerTwo.setDrops(drops);
		playerTwo.setX(memento.getPlayerTwoX());
		playerTwo.setLevel(memento.getPlayerTwoLevel());
		playerTwo.setScore(memento.getPlayerTwoScore());
		playerTwo.setLeftPile(memento.getPlayerTwoLeftPile());
		playerTwo.setRightPile(memento.getPlayerTwoRightPile());
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		if (!initializedVariables) {
			return;
		}

		g2.drawImage(background, 0, 0, 1200, 600, null, null);
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(0, 40, 350, 6));
		g2.fill(new Rectangle2D.Double(850, 40, 350, 6));
		for (Shape s : drops) {
			s.draw(g2);
		}
		g2.drawImage(playerOne.getImage(), (int) playerOne.getX(), 450, 150,
				150, null, null);
		g2.drawImage(playerTwo.getImage(), (int) playerTwo.getX(), 450, 150,
				150, null, null);
		playerOne.drawPiles(g2);
		playerTwo.drawPiles(g2);
		timer.draw(g2);
		scoreBoard.draw(g2);
	}

	public void pauseGame() {
		gamePaused = true;
		mainMenu.setVisible(true);
		save.setVisible(true);
		exit.setVisible(true);
		log.info("Game paused.");
	}

	public void resetPlayers(final Image playerOneImg,
			final Image playerTwoImg, final String playerOneName,
			final String playerTwoName) {
		playerOne.setImage(playerOneImg);
		playerTwo.setImage(playerTwoImg);
		playerOne.reset(880, 480, 130);
		playerTwo.reset(180, 480, 130);
		playerOne.setName(playerOneName);
		playerTwo.setName(playerTwoName);
	}

	public void resetScoreBoard() {
		Image[] playerImages = new Image[3];
		playerImages[1] = playerOne.getImage();
		playerImages[2] = playerTwo.getImage();
		scoreBoard = new ScoreBoard(2, playerImages);
	}

	public void resetTimer() {
		timer = new Timer(90, this);
	}

	private void resumeGame() {
		if (loserId != -1) {
			return;
		}
		mainMenu.setVisible(false);
		save.setVisible(false);
		exit.setVisible(false);
		gamePaused = false;
		log.info("Game resumed.");
	}

	private void saveGame() {
		ArrayList<Shape> clonedDrops = new ArrayList<Shape>();
		for (Shape s : drops) {
			clonedDrops.add(s.clone());
		}
		Memento memento = new Memento(clonedDrops, timer.getCurrentTime(),
				timer.getDuration(), scoreBoard.getScores(),
				scoreBoard.getLevels(), playerOne.getName(),
				playerTwo.getName(), playerOne.getX(), playerTwo.getX(),
				playerOne.getLeftPile(), playerOne.getRightPile(),
				playerTwo.getLeftPile(), playerTwo.getRightPile(),
				playerOne.getLevel(), playerTwo.getLevel(),
				playerOne.getScore(), playerTwo.getScore());
		controller.saveGame(memento);
	}

	public void setDifficulty(final int difficulty) {
		shapesManager.setDifficultySpeed(difficulty);
	}

	public void startGame() {
		loserId = -1;
		gameRunning = true;
		while (gameRunning) {
			if (!gamePaused) {
				updateGame();
				repaint();
			}
			if (loserId != -1) {
				declareWinner();
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
			}
		}
		controller.showMainMenu();
	}

	private void updateGame() {
		playerOne.update();
		playerTwo.update();
		shapesManager.update();
		scoreBoard.updatePlayerScore(1, playerOne.getScore(),
				playerOne.getLevel());
		scoreBoard.updatePlayerScore(2, playerTwo.getScore(),
				playerTwo.getLevel());
	}
}
