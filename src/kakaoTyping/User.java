package kakaoTyping;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import fileIO.FileOutput;

public class User implements Serializable, Comparable {
	private int imgNum = -1; // 0(남자), 1(여자)
	private String name = null; // 사용자 이름
	private int birth = 0; // 사용자 생년월일
	private int score = 0; // 게임 최대점
	private int goal = 0; // 목표 오타율
	private double miss = 0; // 오타율
	private Calendar date = null;

	public int getImgNum() {
		return imgNum;
	}

	public String getName() {
		return name;
	}

	public int getBirth() {
		return birth;
	}

	public int getScore() {
		return score;
	}

	public int getGoal() {
		return goal;
	}

	public double getMiss() {
		return miss;
	}

	public String getDate() {
		return date.get(Calendar.YEAR) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.DAY_OF_MONTH) + "/"
				+ date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE);
	}

	// public void setImgNum(int imgNum) { imgNum = imgNum; }

	// public void setName(String name) { this.name = name; }

	// public void setbirth(int birth) { this.birth = birth; }

	
	public void setScore(int score) {
		this.score = score;
	}

	// public void setGoal(int goal) { this.goal = goal; }

	
	public void setMiss(double miss) {
		this.miss = miss;
	}

	// public void setDate(Calendar date) { this.date = date; }

	public User(int imgNum, String name, int birth, int goal) {
		this.imgNum = imgNum;
		this.name = name;
		this.birth = birth;
		this.goal = goal;
		date = Calendar.getInstance();

	}

	public int compareTo(Object obj) {
		User user = (User) obj;
		if (this.getScore() > user.getScore())
			return -1;
		else if (this.getScore() < user.getScore())
			return 1;
		return 0;
	}
}