package com.lindedin.learning.converter;

import org.springframework.core.convert.converter.Converter;

import com.lindedin.learning.entity.ReservationEntity;
import com.lindedin.learning.model.response.ReservationResponse;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse>{

	@Override
	public ReservationResponse convert(ReservationEntity source) {
		ReservationResponse reservationResponse= new ReservationResponse();
		reservationResponse.setCheckin(source.getCheckin());
		reservationResponse.setCheckout(source.getCheckout());
		if(null!=source.getRoomEntity())
			reservationResponse.setId(source.getRoomEntity().getId());
		// TODO Auto-generated method stub
		return reservationResponse;
	}

}
