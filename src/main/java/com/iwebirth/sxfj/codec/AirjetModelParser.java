package com.iwebirth.sxfj.codec;

import java.sql.Date;
import java.sql.Time;

import com.iwebirth.sxfj.model.AirJetModel;

public class AirjetModelParser {
	/**
	 * @param dataFrame 客户端发送来的数据帧(airjet)
	 * 前12个字符分别是帧头\命令标识\机器型号
	 * 最后4个字符为帧尾
	 * 中间26个 4字符数据(16进制，最大为FFFF)
	 * **/
	public static AirJetModel transforStringToAirJetModel(String dataFrame){
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
}
