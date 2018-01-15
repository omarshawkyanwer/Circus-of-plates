package model.shapes;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShapesLoader {

	private final ArrayList<Class<Shape>> shapes;
	private boolean loadShapesFailed;
	private final Logger log = LogManager.getLogger(getClass());

	public ShapesLoader() {
		shapes = new ArrayList<Class<Shape>>();
		if (!loadShapes()) {
			loadShapesFailed = true;
			log.error("Failed to load shapes.");
		}
		else {
			log.info("Shapes classes loaded successfully.");
		}
	}

	@SuppressWarnings("unchecked")
	private void addShape(final File dir, final File clsFile) {
		try {
			String name = clsFile.getName().substring(0,
					clsFile.getName().indexOf('.'));
			URL url = dir.toURI().toURL();
			URL[] urls = new URL[] { url };
			@SuppressWarnings("resource")
			ClassLoader loader = new URLClassLoader(urls);
			Class<?> cls = loader.loadClass("model.shapes." + name);
			if (Shape.class.isAssignableFrom(cls)) {
				shapes.add((Class<Shape>) cls);
			}
		} catch (Exception e) {
		}
	}

	public boolean didLoadShapesFail() {
		return loadShapesFailed;
	}

	public ArrayList<Class<Shape>> getShapes() {
		return shapes;
	}

	private boolean loadShapes() {
		File dir = new File("Shapes");
		if (!dir.exists()) {
			return false;
		}
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.getName().endsWith(".class")) {
					addShape(dir, child);
				}
			}
			return shapes.size() == 0 ? false : true;
		} else {
			return false;
		}
	}

}
