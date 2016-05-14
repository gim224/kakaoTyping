package GameScene;

import javax.swing.JSplitPane;


public class GameScene extends JSplitPane {
	public GameScene() {
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setDividerLocation(600);
		setEnabled(false);// Á¶Á¤
		setDividerSize(5);

		GamePanel gamePanel = new GamePanel();
		setLeftComponent(gamePanel);
		ScorePanel scorePanel = new ScorePanel();
		setRightComponent(scorePanel);
	}
}
