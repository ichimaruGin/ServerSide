package com.iwebirth.sxfj.codec;

import java.sql.Date;
import java.sql.Time;

import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.model.RapierModel;

public class ModelParser {
	/**
	 * @param dataFrame 客户端发送来的数据帧(airjet)
	 * 前12个字符分别是帧头\命令标识\机器型号
	 * 最后4个字符为帧尾
	 * 中间26个 4字符数据(16进制，最大为FFFF)
	 * **/
	public static AirJetModel parseToAirJetModel(String dataFrame){
		AirJetModel airModel = new AirJetModel();
		int[] d = new int[26];
		try{
			for(int i=0;i<26;i++){
				d[i] = Integer.parseInt(dataFrame.substring(12+i*4, 16+i*4), 16);
				//System.out.println("d["+i+"]="+d[i]);
			}
			airModel.setMachineSno(dataFrame.substring(8, 12));
			airModel.setWeaveLength(d[0]);
			airModel.setWeiNumber(d[1]);
			airModel.setRunTime(d[2]);
			airModel.setWholeStopNumber(d[3]);
			airModel.setWholeStopTime(d[4]);
			airModel.setH1WeiTingNumber(d[5]);
			airModel.setH1WeiTingTime(d[6]);
			airModel.setJingTingNumber(d[7]);
			airModel.setJingTingTime(d[8]);
			airModel.setOtherStopNumber(d[9]);
			airModel.setOtherStopTime(d[10]);
			airModel.setC1H1(d[11]);
			airModel.setC1H2(d[12]);
			airModel.setC2H1(d[13]);
			airModel.setC2H2(d[14]);
			airModel.setC3H1(d[15]);
			airModel.setC3H2(d[16]);
			airModel.setC4H1(d[17]);
			airModel.setC4H2(d[18]);
			airModel.setTingJingPian(d[19]);
			airModel.setLeftTwistFlat(d[20]);
			airModel.setRightTwistFlat(d[21]);
			airModel.setChuWeiQi(d[22]);
			airModel.setFeiBianSha(d[23]);
			airModel.setWorkingStatus(d[24]);
			airModel.setClearBytes(d[25]);
			//设置时间
			airModel.setUploadDate(new Date(System.currentTimeMillis()));
			airModel.setUploadTime(new Time(System.currentTimeMillis()));
			airModel.setTimeinMillis(System.currentTimeMillis());
		}catch(Exception e){
			airModel = null;
		}	
		return airModel;
	}
	
	/**
	 * 剑杆织机数据模型转换
	 * **/
	public static RapierModel parseToRapierModel(String dataFrame){
		RapierModel model = new RapierModel();
		int[] d = new int[22]; 
		try{
			for(int i=0;i<d.length;i++){
				d[i] = Integer.parseInt(dataFrame.substring(12+i*4, 16+i*4),16);
			}
			model.setMachineSno(dataFrame.substring(8, 12));
			model.setJingTingNumber(d[0]);
			model.setWeiTingNumber(d[1]);
			model.setWholePowerTime(d[2]);
			model.setWholeRunTime(d[3]);
			model.setTeamOnePowerTime(d[4]);
			model.setTeamOneRunTime(d[5]);
			model.setTeamTwoPowerTime(d[6]);
			model.setTeamTwoRunTime(d[7]);
			model.setTeamThreePowerTime(d[8]);
			model.setTeamThreeRunTime(d[9]);
			model.setTeamFourPowerTime(d[10]);
			model.setTeamFourRunTime(d[11]);
			model.setWholeOutput(d[12]);
			model.setTeamOneOutput(d[13]);
			model.setTeamTwoOutput(d[14]);
			model.setTeamThreeOutput(d[15]);
			model.setTeamFourOutput(d[16]);
			model.setWholeStartRate(d[17]);
			model.setTeamOneStartRate(d[18]);
			model.setTeamTwoStartRate(d[19]);
			model.setTeamThreeStartRate(d[20]);
			model.setTeamFourStartRate(d[21]);
			//时间
			model.setUploadDate(new Date(System.currentTimeMillis()));
			model.setUploadTime(new Time(System.currentTimeMillis()));
			model.setTimeInMillis(System.currentTimeMillis());			
		}catch(Exception e){
			model = null;
		}
		return model;
	}
}
