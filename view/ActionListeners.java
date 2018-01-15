package eg.edu.alexu.csd.oop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import controller.Controller;

public class ActionListeners {

	private JButton play, exit, back, load;
	private JButton[] difficultyLevelsButtons;
	private String[] difficultyLevels;
	private Controller controller;
	private MainMenu mainMenu;

	public ActionListeners(final JButton play, final JButton back,
			final JButton exit, final JButton load,
			final JButton[] difficultyLevelsButtons,
			final String[] difficultyLevels, final MainMenu mainMenu,
			final Controller controller) {
		this.play = play;
		this.back = back;
		this.load = load;
		this.exit = exit;
		this.difficultyLevelsButtons = difficultyLevelsButtons;
		this.mainMenu = mainMenu;
		this.difficultyLevels = difficultyLevels;
		this.controller = controller;
	}

	void addBackAction() {
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				for (int i = 0; i < MainMenu.numberOfDifficultyLevels; ++i) {
					difficultyLevelsButtons[i].setVisible(false);
				}

				back.setVisible(false);
				play.setVisible(true);
				load.setVisible(true);
				exit.setVisible(true);
			}
		});
	}

	void addDifficultyLevelsAction() {
		for (int i = 0; i < MainMenu.numberOfDifficultyLevels; ++i) {
			final int index = i;
			difficultyLevelsButtons[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(final ActionEvent e) {
					controller.startGame(mainMenu.getPlayerTwoImage(),
							mainMenu.getPlayerOneImage(),
							mainMenu.getPlayerTwoName(),
							mainMenu.getPlayerOneName(),
							difficultyLevels[index]);
				}
			});
		}
	}

	void addExitAction() {
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.exit();
			}
		});
	}

	void addLoadAction() {
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.loadGame();
			}
		});
	}

	public void addPlayAction() {
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				for (int i = 0; i < MainMenu.numberOfDifficultyLevels; ++i) {
					difficultyLevelsButtons[i].setVisible(true);
				}

				back.setVisible(true);
				play.setVisible(false);
				load.setVisible(false);
				exit.setVisible(false);
			}
		});

	}

}