package com.iwebirth.sxfj.codec;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.server.ClearTaskManager;
import com.iwebirth.sxfj.server.Server;
import com.iwebirth.sxfj.util.JParam;

public class DataDecoder extends CumulativeProtocolDecoder{
	
	private static Logger log = Logger.getLogger(DataDecoder.class);
	private static final int FRAME_MIN_LENGTH = 16; //根据最小数据帧长度得到
	private Charset charset;
	public DataDecoder(Charset charset){
		this.charset = charset;
	}
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		//in默认容量为2048
		if(in.limit()>=FRAME_MIN_LENGTH){
			String frame = trimAll(in.getString(Charset.forName("utf-8").newDecoder()));
			System.out.println("收到："+frame);
			if(isValid(frame)){
				if("AI".equals(frame.substring(8, 10))){
					//喷气织机 airjet loom
					if("0001".equals(frame.substring(4, 8))){
						//"0001"表示该帧时从客户端发来的具体数据
						session.setAttribute("command", JParam.AIR_DATA_COMMAND);
						AirJetModel model = AirjetModelParser.transforStringToAirJetModel(frame);						
						if(model != null)
							out.write(model);
					}else if("0002".equals(frame.substring(4, 8))){
						session.setAttribute("command", JParam.AIR_CONNECT_COMMAND);
						out.write(frame);
					}
				}
				if("RP".equals(frame.substring(8,10))){
					//剑杆织机 rapier loom
					
				}
			}else{
				System.out.println("非法数据帧头尾");
				session.close(true);
			}
			return true;
		}else{
			System.out.println("非法数据长度");
			session.close(true);
		}
		return true;
		//return false; 事实上，如果一次采集的数据不满足长度要求，那么，就直接舍弃这组数据(这个协议解码器就是这么处理的)，即返回true;如果想保留这组数据，让它和下一次来的数据一起再次被读进来，则返回false
	}
	
	/**
	 * @return 检查帧头帧尾
	 * **/
	private boolean isValid(String frame){
		if((frame.startsWith("5a5a")||frame.startsWith("5A5A"))&&(frame.endsWith("5c5c")||frame.endsWith("5C5C")))
			return true;
		else
			return false;
	}
	/**
	 * @return 去除所有空格号
	 * **/
	private String trimAll(String frame){
		return frame.replaceAll("\\s", "");
	}

	public static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\\s*|\n|\r|\t");  
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	    return dest;
	}

}

