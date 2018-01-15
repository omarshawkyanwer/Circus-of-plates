package eg.edu.alexu.csd.oop.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	public static final int numberOfDifficultyLevels = 3,
			numberOfPlayerChars = 3;
	private JButton play, exit, load, back;
	private JComboBox<String> playerOneSelector, playerTwoSelector;
	private String[] difficultyLevels = { "easy", "medium", "hard" };
	private JButton[] difficultyLevelsButtons;
	private String[] playerNames = { "Mario", "Luigi", "Bowser Jr" };
	private HashMap<String, ImageIcon> playerImages;
	private JPanel body;
	private JLabel[] curPlayerImageLabel;
	private Controller controller;

	public MainMenu(final Controller controller) {
		this.controller = controller;

		createObjects();
		body.setLayout(new FlowLayout());
		body.setOpaque(false);
		setLayout();
		addActionLisenters();
		setPreferredSize(new Dimension(1200, 600));
		setLayout(new GridBagLayout());
		add(body, new GridBagConstraints());
		setOpaque(false);

		repaint();
	}

	private void addActionLisenters() {
		ActionListeners actionListeners = new ActionListeners(play, back, exit,
				load, difficultyLevelsButtons, difficultyLevels, this,
				controller);

		actionListeners.addPlayAction();
		actionListeners.addBackAction();
		actionListeners.addDifficultyLevelsAction();
		actionListeners.addLoadAction();
		actionListeners.addExitAction();

		playerOneSelector.addActionListener(new ComboBoxAction(playerImages,
				curPlayerImageLabel[1]));
		playerTwoSelector.addActionListener(new ComboBoxAction(playerImages,
				curPlayerImageLabel[2]));
		playerTwoSelector.setSelectedIndex(0);
		playerOneSelector.setSelectedIndex(1);
	}

	void createObjects() {
		body = new JPanel();
		play = makeButton("play");
		exit = makeButton("exit");
		load = makeButton("load");
		back = makeButton("back");
		back.setVisible(false);

		difficultyLevelsButtons = new JButton[numberOfDifficultyLevels];
		for (int i = 0; i < numberOfDifficultyLevels; ++i) {
			difficultyLevelsButtons[i] = makeButton(difficultyLevels[i]);
			difficultyLevelsButtons[i].setBorderPainted(false);
			difficultyLevelsButtons[i].setContentAreaFilled(false);
			difficultyLevelsButtons[i].setVisible(false);
		}

		// this image will depend on chooser
		playerImages = new HashMap<String, ImageIcon>();

		for (int i = 0; i < numberOfPlayerChars; ++i) {
			playerImages.put(playerNames[i], new ImageIcon(new ImageIcon(
					getClass()
							.getResource("/images/" + playerNames[i] + ".png"))
					.getImage()
					.getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
		}

		// here will be combo box of picture or names Depend .
		curPlayerImageLabel = new JLabel[3];

		playerOneSelector = new JComboBox<String>(playerNames);
		playerTwoSelector = new JComboBox<String>(playerNames);

	}

	public Image getPlayerOneImage() {
		int selectedIndex = playerOneSelector.getSelectedIndex();
		return playerImages.get(playerNames[selectedIndex]).getImage()
				.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
	}

	public String getPlayerOneName() {
		return playerNames[playerOneSelector.getSelectedIndex()];
	}

	public Image getPlayerTwoImage() {
		int selectedIndex = playerTwoSelector.getSelectedIndex();
		return playerImages.get(playerNames[selectedIndex]).getImage()
				.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
	}

	public String getPlayerTwoName() {
		return playerNames[playerTwoSelector.getSelectedIndex()];
	}

	private JButton makeButton(final String name) {
		JButton ret = new JButton(new ImageIcon(new ImageIcon(getClass()
				.getResource("/images/" + name + ".png")).getImage()
				.getScaledInstance(90, 40, Image.SCALE_DEFAULT)));

		ret.setBorderPainted(false);
		ret.setContentAreaFilled(false);
		return ret;
	}

	private JPanel makePalyerPanel(final int id,
			final JComboBox<String> playerSelector) {

		JLabel image = new JLabel(playerImages.get(playerNames[id - 1]));
		curPlayerImageLabel[id] = image;

		JPanel player = new JPanel();
		player.setLayout(new BoxLayout(player, BoxLayout.PAGE_AXIS));
		player.add(image, BorderLayout.CENTER);
		player.add(playerSelector, BorderLayout.CENTER);
		player.setOpaque(false);

		return player;
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		Image img = new ImageIcon(getClass().getResource("/images/bg.jpg"))
				.getImage();
		g2.drawImage(img, 0, 0, 1200, 600, null, null);
	}

	void setLayout() {
		JPanel buttonsLayout = new JPanel();
		buttonsLayout.setLayout(new BoxLayout(buttonsLayout,
				BoxLayout.PAGE_AXIS));

		buttonsLayout.add(play, BorderLayout.CENTER);
		buttonsLayout.add(load, BorderLayout.CENTER);
		buttonsLayout.add(exit, BorderLayout.CENTER);
		buttonsLayout.setOpaque(false);
		for (int i = 0; i < numberOfDifficultyLevels; ++i) {
			buttonsLayout.add(difficultyLevelsButtons[i], BorderLayout.CENTER);
		}
		buttonsLayout.add(back);

		body.add(makePalyerPanel(1, playerOneSelector), BorderLayout.CENTER);
		body.add(buttonsLayout, BorderLayout.CENTER);
		body.add(makePalyerPanel(2, playerTwoSelector), BorderLayout.CENTER);
	}

}