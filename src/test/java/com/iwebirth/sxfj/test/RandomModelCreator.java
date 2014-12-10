package com.iwebirth.sxfj.test;

import java.sql.Date;
import java.sql.Time;

import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.model.RapierModel;

/**
 * 产生随机随机模型数据并返回
 * **/
public class RandomModelCreator {
	/**
	 * 喷气织机
	 * 前12个字符分别是帧头\命令标识\机器型号
	 * 最后4个字符为帧尾
	 * 中间26个 4字符数据(16进制，最大为FFFF)
	 * **/
	public static AirJetModel createRandomAirjetModel(){
		AirJetModel airModel = new AirJetModel();
		int count = (int)(4*Math.random()+1); //1,2,3,4
		int[] d = new int[26];
		try{
			for(int i=0;i<26;i++){
				d[i] = (int)(10000*Math.random()+1);
			}
			switch(count){
				case 1: airModel.setMachineSno("AI01");break;
				case 2: airModel.setMachineSno("AI02");break;
				case 3: airModel.setMachineSno("AI03");break;
				case 4: airModel.setMachineSno("AI04");break;
				default:airModel.setMachineSno("AI05");break;
			}			
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
			airModel.setWorkingStatus((Math.random()>0.5)?1:0);
			airModel.setClearBytes((Math.random()>0.5)?1:0);
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
	 * 产生随机剑杆织机参数
	 * 前12个字符分别是帧头\命令标识\机器型号
	 * 最后4个字符为帧尾
	 * **/
	public static RapierModel createRandomRapierModel(){
		RapierModel model = new RapierModel();
		int count = (int)(4*Math.random()+1); //1,2,3,4
		int[] d = new int[22]; 
		try{
			for(int i=0;i<d.length;i++){
				d[i] = (int) (10000*Math.random()+1);
			}
			switch(count){
			case 1: model.setMachineSno("RA01");break;
			case 2: model.setMachineSno("RA02");break;
			case 3: model.setMachineSno("RA03");break;
			case 4: model.setMachineSno("RA04");break;
			default:model.setMachineSno("RA05");break;
			}	
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
