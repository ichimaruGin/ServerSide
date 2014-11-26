package com.iwebirth.sxfj.model;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author YY_410
 * air-jet loom 喷气织机
 * 数据模型
 * **/
@Entity
@Table(name="airjet")
//@DynamicInsert(true) 动态生成insert的sql语句。若插入的对象有部分值没有set（为null），那么只会插入已经set后的字段，没有set的字段根据default值进行补全。若没有设定default值则会出异常。
//该annotation可以在表生成后进行改动
//与之相仿的还有@DynamicUpdate  参考http://www.cnblogs.com/quanyongan/p/3152290.html
@DynamicInsert(true)
public class AirJetModel {
	//数据位
	@Column(length=10,columnDefinition="varchar(10) not null default -1")  //columnDefinition仅在Hibernate的DDL时有效，即生成数据表时会根据该参数来生成
	private String machineSno;//"AI00"
	@Column(columnDefinition="int not null default -1")
	private Integer weaveLength;  			//织布长度
	@Column(columnDefinition="int not null default -1")
	private Integer weiNumber;			//纬数
	//private float runEfficiency;//运转效率
	@Column(columnDefinition="int not null default -1")
	private Integer runTime;	//运转时间
	@Column(columnDefinition="int not null default -1")
	private Integer wholeStopNumber; //总停次数
	@Column(columnDefinition="int not null default -1")
	private Integer wholeStopTime;		 //总停时间
	@Column(columnDefinition="int not null default -1")
	private Integer h1WeiTingNumber; //H1纬停次数
	@Column(columnDefinition="int not null default -1")
	private Integer h1WeiTingTime;		 //H1纬停时间
	@Column(columnDefinition="int not null default -1")
	private Integer jingTingNumber;   //经停次数
	@Column(columnDefinition="int not null default -1")
	private Integer jingTingTime; 		 //经停时间	
	@Column(columnDefinition="int not null default -1")
	private Integer otherStopNumber,otherStopTime;		//其他停次数、其他停时间
	//故障
	@Column(columnDefinition="int not null default -1")
	private Integer C1H1,C1H2;
	@Column(columnDefinition="int not null default -1")
	private Integer C2H1,C2H2;
	@Column(columnDefinition="int not null default -1")
	private Integer C3H1,C3H2;
	@Column(columnDefinition="int not null default -1")
	private Integer C4H1,C4H2;
	@Column(columnDefinition="int not null default -1")
	private Integer tingJingPian;					//停经片
	@Column(columnDefinition="int not null default -1")
	private Integer leftTwistFlat,rightTwistFlat;	//左绞片、右绞片
	@Column(columnDefinition="int not null default -1")
	private Integer feiBianSha;						//废边纱
	@Column(columnDefinition="int not null default -1")
	private Integer chuWeiQi;						//储纬器
	//操作位
	@Column(columnDefinition="int not null default -1")
	private Integer clearBytes; //清零操作  为1则清零
	@Column(columnDefinition="int not null default -1")
	private Integer workingStatus;  // 工作状态  为1则说明机器在运转
	@Id
	@GeneratedValue(generator="mysql") //引用下面名为mysqldefaultid的主键
	@GenericGenerator(name="mysql", strategy = "identity")   //"identity","increment","native"  
	private Integer id;
	@Column
	private Date uploadDate;
	@Column
	private Time uploadTime;
	@Column(columnDefinition="bigint(20) not null default -1")
	private Long timeinMillis;
	

	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Time getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Time uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Long getTimeinMillis() {
		return timeinMillis;
	}
	public void setTimeinMillis(Long timeinMillis) {
		this.timeinMillis = timeinMillis;
	}
	public String getMachineSno() {
		return machineSno;
	}
	public void setMachineSno(String machineSno) {
		this.machineSno = machineSno;
	}
	public Integer getWeaveLength() {
		return weaveLength;
	}
	public void setWeaveLength(Integer weaveLength) {
		this.weaveLength = weaveLength;
	}
	public Integer getWeiNumber() {
		return weiNumber;
	}
	public void setWeiNumber(Integer weiNumber) {
		this.weiNumber = weiNumber;
	}
	public Integer getRunTime() {
		return runTime;
	}
	public void setRunTime(Integer runTime) {
		this.runTime = runTime;
	}
	public Integer getWholeStopNumber() {
		return wholeStopNumber;
	}
	public void setWholeStopNumber(Integer wholeStopNumber) {
		this.wholeStopNumber = wholeStopNumber;
	}
	
	public Integer getH1WeiTingNumber() {
		return h1WeiTingNumber;
	}
	public void setH1WeiTingNumber(Integer h1WeiTingNumber) {
		this.h1WeiTingNumber = h1WeiTingNumber;
	}

	public Integer getJingTingNumber() {
		return jingTingNumber;
	}
	public void setJingTingNumber(Integer jingTingNumber) {
		this.jingTingNumber = jingTingNumber;
	}

	public Integer getWholeStopTime() {
		return wholeStopTime;
	}
	public void setWholeStopTime(Integer wholeStopTime) {
		this.wholeStopTime = wholeStopTime;
	}
	public Integer getH1WeiTingTime() {
		return h1WeiTingTime;
	}
	public void setH1WeiTingTime(Integer h1WeiTingTime) {
		this.h1WeiTingTime = h1WeiTingTime;
	}
	public Integer getJingTingTime() {
		return jingTingTime;
	}
	public void setJingTingTime(Integer jingTingTime) {
		this.jingTingTime = jingTingTime;
	}
	public Integer getOtherStopNumber() {
		return otherStopNumber;
	}
	public void setOtherStopNumber(Integer otherStopNumber) {
		this.otherStopNumber = otherStopNumber;
	}
	public Integer getOtherStopTime() {
		return otherStopTime;
	}
	public void setOtherStopTime(Integer otherStopTime) {
		this.otherStopTime = otherStopTime;
	}
	public Integer getC1H1() {
		return C1H1;
	}
	public void setC1H1(Integer c1h1) {
		C1H1 = c1h1;
	}
	public Integer getC1H2() {
		return C1H2;
	}
	public void setC1H2(Integer c1h2) {
		C1H2 = c1h2;
	}
	public Integer getC2H1() {
		return C2H1;
	}
	public void setC2H1(Integer c2h1) {
		C2H1 = c2h1;
	}
	public Integer getC2H2() {
		return C2H2;
	}
	public void setC2H2(Integer c2h2) {
		C2H2 = c2h2;
	}
	public Integer getC3H1() {
		return C3H1;
	}
	public void setC3H1(Integer c3h1) {
		C3H1 = c3h1;
	}
	public Integer getC3H2() {
		return C3H2;
	}
	public void setC3H2(Integer c3h2) {
		C3H2 = c3h2;
	}
	public Integer getC4H1() {
		return C4H1;
	}
	public void setC4H1(Integer c4h1) {
		C4H1 = c4h1;
	}
	public Integer getC4H2() {
		return C4H2;
	}
	public void setC4H2(Integer c4h2) {
		C4H2 = c4h2;
	}
	public Integer getTingJingPian() {
		return tingJingPian;
	}
	public void setTingJingPian(Integer tingJingPian) {
		this.tingJingPian = tingJingPian;
	}
	public Integer getLeftTwistFlat() {
		return leftTwistFlat;
	}
	public void setLeftTwistFlat(Integer leftTwistFlat) {
		this.leftTwistFlat = leftTwistFlat;
	}

	public Integer getRightTwistFlat() {
		return rightTwistFlat;
	}
	public void setRightTwistFlat(Integer rightTwistFlat) {
		this.rightTwistFlat = rightTwistFlat;
	}
	public Integer getFeiBianSha() {
		return feiBianSha;
	}
	public void setFeiBianSha(Integer feiBianSha) {
		this.feiBianSha = feiBianSha;
	}
	public Integer getChuWeiQi() {
		return chuWeiQi;
	}
	public void setChuWeiQi(Integer chuWeiQi) {
		this.chuWeiQi = chuWeiQi;
	}
	public Integer getClearBytes() {
		return clearBytes;
	}
	public void setClearBytes(Integer clearBytes) {
		this.clearBytes = clearBytes;
	}
	public Integer getWorkingStatus() {
		return workingStatus;
	}
	public void setWorkingStatus(Integer workingStatus) {
		this.workingStatus = workingStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}



	
}
