package eg.edu.alexu.csd.oop.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ComboBoxAction implements ActionListener {

	private HashMap<String, ImageIcon> playerImages;
	private JLabel curPlayerImageLabel;
	private final Logger log = LogManager.getLogger(getClass());

	public ComboBoxAction(final HashMap<String, ImageIcon> playerImages,
			final JLabel curPlayerImageLabel) {
		this.playerImages = playerImages;
		this.curPlayerImageLabel = curPlayerImageLabel;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void actionPerformed(final ActionEvent e) {
		JComboBox comboBox = (JComboBox) e.getSource();
		String selected = (String) comboBox.getSelectedItem();
		log.info("A player changed his character to " + selected + ".");
		curPlayerImageLabel.setIcon(playerImages.get(selected));
	}

}