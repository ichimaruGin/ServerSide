package com.iwebirth.sxfj.codec;

import com.iwebirth.sxfj.model.AirJetModel;
import com.iwebirth.sxfj.model.RapierModel;

public class CopyOnReadModelList {   

    private static CopyOnReadList<AirJetModel> airJetModelinstance;
    private static CopyOnReadList<RapierModel> rapierModelinstance;
    public static CopyOnReadList<AirJetModel> getAirJetModelInstance () {   
        if (airJetModelinstance == null) {
        	airJetModelinstance = new CopyOnReadList<AirJetModel>();
        }
        return airJetModelinstance;
    }
    public static CopyOnReadList<RapierModel> getRapierModelInstance(){
    	if(rapierModelinstance == null){
    		rapierModelinstance = new CopyOnReadList<RapierModel>();
    	}
    	return rapierModelinstance;
    }
}
