package com.iwebirth.sxfj.codec;

import com.iwebirth.sxfj.model.AirJetModel;

public class CopyOnReadModelList {   

    private static CopyOnReadList<AirJetModel> airJetModelinstance;
  //  private static CopyOnReadList<RapierModel> rapierModelinstance;
    public static CopyOnReadList<AirJetModel> getAirJetModelInstance () {   
        if (airJetModelinstance == null) {
        	airJetModelinstance = new CopyOnReadList<AirJetModel>();
        }
        return airJetModelinstance;
    }

}
