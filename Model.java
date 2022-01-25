import java.util.List;

abstract class Model {
	
	public List<Line> lines;

	public Model(List<Line> lines) {
		this.lines = lines;
	}

}