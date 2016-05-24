package kakaoTyping;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import fileIO.FileOutput;

public class User implements Serializable {
	private int imgNum; // 0(mugi), 1(tube), 2(prodo)
	private String name; // 사용자 이름
	private int birth; // 사용자 생년월일
	private int score = 0; // 게임 최대점
	private int goal; // 목표 오타율
	private double miss = 0; // 오타율
	private Calendar date;

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
		return date.get(Calendar.YEAR)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.DAY_OF_MONTH)+"/"+date.get(Calendar.HOUR_OF_DAY)+":"+date.get(Calendar.MINUTE);
	}

	/*
	 * public void setImgNum(int imgNum) { ImgNum = imgNum; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public void setbirth(String birth) { this.birth = birth; }
	 * 
	 * public void setScore(int score) { this.score = score; }
	 * 
	 * public void setGoal(String goal) { this.goal = goal; }
	 * 
	 * public void setMiss(double miss) { this.miss = miss; }
	 * 
	 * public void setDate(Date date) { this.date = date; }
	 */

	public User(int imgNum, String name, int birth, int goal) {
		this.imgNum = imgNum;
		this.name = name;
		this.birth = birth;
		this.goal = goal;
		date = Calendar.getInstance();

	}
}