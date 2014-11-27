package com.iwebirth.sxfj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rapier")
public class RapierModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@Column(name="machineSno",nullable=false)
	String machineSno;
	@Column(nullable=false)
	Integer jingTingNumber;
	@Column(nullable=false)
	Integer weiTingNumber;
	@Column(nullable=false)
	Integer wholePowerTime;
	@Column(nullable=false)
	Integer wholeRunTime;
	@Column(nullable=false)
	Integer teamOnePowerTime;
	@Column(nullable=false)
	Integer teamOneRunTime;
	@Column(nullable=false)
	Integer teamTwoPowerTime;
	@Column(nullable=false)
	Integer teamTwoRunTime;
	@Column(nullable=false)
	Integer teamThreePowerTime;
	@Column(nullable=false)
	Integer teamThreeRunTime;
	@Column(nullable=false)
	Integer teamFourPowerTime;
	@Column(nullable=false)
	Integer teamFourRunTime;
	@Column(nullable=false)
	Integer wholeOutput;
	@Column(nullable=false)
	Integer teamOneOutput;
	@Column(nullable=false)
	Integer teamTwoOutput;
	@Column(nullable=false)
	Integer teamThreeOutput;
	@Column(nullable=false)
	Integer teamFourOutput;
	@Column(nullable=false)
	Integer wholeStartRate;
	@Column(nullable=false)
	Integer teamOneStartRate;
	@Column(nullable=false)
	Integer teamTwoStartRate;
	@Column(nullable=false)
	Integer teamThreeStartRate;
	@Column(nullable=false)
	Integer teamFourStartRate;
	@Column(nullable=false)
	Long timeInMillis;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMachineSno() {
		return machineSno;
	}
	public void setMachineSno(String machineSno) {
		this.machineSno = machineSno;
	}
	public Integer getJingTingNumber() {
		return jingTingNumber;
	}
	public void setJingTingNumber(Integer jingTingNumber) {
		this.jingTingNumber = jingTingNumber;
	}
	public Integer getWeiTingNumber() {
		return weiTingNumber;
	}
	public void setWeiTingNumber(Integer weiTingNumber) {
		this.weiTingNumber = weiTingNumber;
	}
	public Integer getWholePowerTime() {
		return wholePowerTime;
	}
	public void setWholePowerTime(Integer wholePowerTime) {
		this.wholePowerTime = wholePowerTime;
	}
	public Integer getWholeRunTime() {
		return wholeRunTime;
	}
	public void setWholeRunTime(Integer wholeRunTime) {
		this.wholeRunTime = wholeRunTime;
	}
	public Integer getTeamOnePowerTime() {
		return teamOnePowerTime;
	}
	public void setTeamOnePowerTime(Integer teamOnePowerTime) {
		this.teamOnePowerTime = teamOnePowerTime;
	}
	public Integer getTeamOneRunTime() {
		return teamOneRunTime;
	}
	public void setTeamOneRunTime(Integer teamOneRunTime) {
		this.teamOneRunTime = teamOneRunTime;
	}
	public Integer getTeamTwoPowerTime() {
		return teamTwoPowerTime;
	}
	public void setTeamTwoPowerTime(Integer teamTwoPowerTime) {
		this.teamTwoPowerTime = teamTwoPowerTime;
	}
	public Integer getTeamTwoRunTime() {
		return teamTwoRunTime;
	}
	public void setTeamTwoRunTime(Integer teamTwoRunTime) {
		this.teamTwoRunTime = teamTwoRunTime;
	}
	public Integer getTeamThreePowerTime() {
		return teamThreePowerTime;
	}
	public void setTeamThreePowerTime(Integer teamThreePowerTime) {
		this.teamThreePowerTime = teamThreePowerTime;
	}
	public Integer getTeamThreeRunTime() {
		return teamThreeRunTime;
	}
	public void setTeamThreeRunTime(Integer teamThreeRunTime) {
		this.teamThreeRunTime = teamThreeRunTime;
	}
	public Integer getTeamFourPowerTime() {
		return teamFourPowerTime;
	}
	public void setTeamFourPowerTime(Integer teamFourPowerTime) {
		this.teamFourPowerTime = teamFourPowerTime;
	}
	public Integer getTeamFourRunTime() {
		return teamFourRunTime;
	}
	public void setTeamFourRunTime(Integer teamFourRunTime) {
		this.teamFourRunTime = teamFourRunTime;
	}
	public Integer getWholeOutput() {
		return wholeOutput;
	}
	public void setWholeOutput(Integer wholeOutput) {
		this.wholeOutput = wholeOutput;
	}
	public Integer getTeamOneOutput() {
		return teamOneOutput;
	}
	public void setTeamOneOutput(Integer teamOneOutput) {
		this.teamOneOutput = teamOneOutput;
	}
	public Integer getTeamTwoOutput() {
		return teamTwoOutput;
	}
	public void setTeamTwoOutput(Integer teamTwoOutput) {
		this.teamTwoOutput = teamTwoOutput;
	}
	public Integer getTeamThreeOutput() {
		return teamThreeOutput;
	}
	public void setTeamThreeOutput(Integer teamThreeOutput) {
		this.teamThreeOutput = teamThreeOutput;
	}
	public Integer getTeamFourOutput() {
		return teamFourOutput;
	}
	public void setTeamFourOutput(Integer teamFourOutput) {
		this.teamFourOutput = teamFourOutput;
	}
	public Integer getWholeStartRate() {
		return wholeStartRate;
	}
	public void setWholeStartRate(Integer wholeStartRate) {
		this.wholeStartRate = wholeStartRate;
	}

	public Integer getTeamOneStartRate() {
		return teamOneStartRate;
	}
	public void setTeamOneStartRate(Integer teamOneStartRate) {
		this.teamOneStartRate = teamOneStartRate;
	}
	public Integer getTeamTwoStartRate() {
		return teamTwoStartRate;
	}
	public void setTeamTwoStartRate(Integer teamTwoStartRate) {
		this.teamTwoStartRate = teamTwoStartRate;
	}
	public Integer getTeamThreeStartRate() {
		return teamThreeStartRate;
	}
	public void setTeamThreeStartRate(Integer teamThreeStartRate) {
		this.teamThreeStartRate = teamThreeStartRate;
	}
	public Integer getTeamFourStartRate() {
		return teamFourStartRate;
	}
	public void setTeamFourStartRate(Integer teamFourStartRate) {
		this.teamFourStartRate = teamFourStartRate;
	}
	public Long getTimeInMillis() {
		return timeInMillis;
	}
	public void setTimeInMillis(Long timeInMillis) {
		this.timeInMillis = timeInMillis;
	}
	
	
	
}
