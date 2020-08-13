package com.mycard.auth.property.map;

import com.mycard.auth.dto.UserDescriptionDTO;
import com.mycard.auth.entity.UserDescription;
import org.modelmapper.PropertyMap;

public class UserDescriptionToUserDescriptionDTO extends PropertyMap<UserDescription, UserDescriptionDTO> {
    @Override
    protected void configure() {
        map().setId(source.getId());
        map().setFirstName(source.getFirstName());
        map().setLastName(source.getLastName());
        map().setRegisterDt(source.getRegisterDt());
        map().setCcInvoiceDt(source.getCcInvoiceDt());
        map().setAddressStreet(source.getAddressStreet());
        map().setAddressNumber(source.getAddressNumber());
        map().setAddressComplement(source.getAddressComplement());
    }
}
