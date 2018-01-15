package model.snapshot;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class FileManager {

	private final Logger log = LogManager.getLogger(getClass());

	private File getLoadFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Load");
		fileChooser.setApproveButtonText("Load");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(
				"XML (*.xml)", "xml"));
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}

	private File getSaveFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save");
		fileChooser.setApproveButtonText("Save");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = new File(fileChooser.getSelectedFile(), "save.xml");
			return file;
		}
		return null;
	}

	public Memento load() {
		Memento loaded = null;
		try {
			File file = getLoadFile();
			Scanner scanner = new Scanner(file);
			String fileContent = scanner.useDelimiter("\\Z").next();
			XStream xstream = new XStream(new DomDriver());
			loaded = (Memento) xstream.fromXML(fileContent);
			scanner.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Loading failed.","Load",JOptionPane.ERROR_MESSAGE);
			log.error("Loading failed.");
		}
		return loaded;
	}

	public void save(final Memento memento) {
		try {
			File file = getSaveFile();
			file.createNewFile();
			XStream xstream = new XStream(new DomDriver());
			String xml = xstream.toXML(memento);
			FileWriter writer = new FileWriter(file);
			writer.write(xml);
			writer.close();
			JOptionPane.showMessageDialog(null,"Saved successfully","Save",JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Saving failed.","Save",JOptionPane.ERROR_MESSAGE);
			log.error("Saving failed.");
		}
	}

}
