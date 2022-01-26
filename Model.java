import java.util.List;
import java.awt.Color;

abstract class Model {
	
	public List<Line> lines;
	public Color color;

	public Model(List<Line> lines) {
		this.lines = lines;
		color = Color.BLACK;
	}

}