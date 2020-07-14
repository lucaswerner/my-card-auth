package com.mycard.auth.property.map;

import com.mycard.auth.dto.CompleteUserDTO;
import com.mycard.auth.entity.User;
import org.modelmapper.PropertyMap;

public class UserToCompleteUserDTO extends PropertyMap<User, CompleteUserDTO> {
    @Override
    protected void configure() {
        map().setId(source.getId());
        map().setEmail(source.getEmail());
        map().setEnabled(source.getEnabled());
        map().setLastLogin(source.getLastLogin());
        map().setFirstName(source.getUserDescription().getFirstName());
        map().setLastName(source.getUserDescription().getLastName());
        map().setCcInvoiceDt(source.getUserDescription().getCcInvoiceDt());
        map().setAddressStreet(source.getUserDescription().getAddressStreet());
        map().setAddressNumber(source.getUserDescription().getAddressNumber());
        map().setAddressComplement(source.getUserDescription().getAddressComplement());
    }
}
