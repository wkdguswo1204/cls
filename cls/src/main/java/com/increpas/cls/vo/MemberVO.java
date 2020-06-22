package com.increpas.cls.vo;

import java.util.ArrayList;
import java.util.Date;
import java.text.*;

public class MemberVO {
	private int mno, ano;
	private String name, id, pw, mail, gen, tel, sdate, birth, avatar, status;
	private Date jDate, bDate;
	private ArrayList<ProfileVO> pic;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getGen() {
		return gen;
	}
	public void setGen(String gen) {
		this.gen = gen;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.sdate = form.format(jDate);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
		setSdate();
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Date getjDate() {
		return jDate;
	}
	public void setjDate(Date jDate) {
		this.jDate = jDate;
		setSdate();
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth() {
		SimpleDateFormat form = new SimpleDateFormat("yyyy년 MM월 dd일");
		this.birth = form.format(bDate);
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public Date getbDate() {
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
		setBirth();
	}
	public ArrayList<ProfileVO> getPic() {
		return pic;
	}
	public void setPic(ArrayList<ProfileVO> pic) {
		this.pic = pic;
	}
	public String toString() {
		return "MemberVO [mno=" + mno + ", ano=" + ano + ", name=" + name + ", id=" + id + ", pw=" + pw + ", mail="
				+ mail + ", gen=" + gen + ", tel=" + tel + ", avatar=" + avatar + ", jDate=" + jDate + "]";
	}
}
