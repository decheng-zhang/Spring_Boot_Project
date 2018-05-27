package com.lindedin.learning.converter;

import org.springframework.core.convert.converter.Converter;

import com.lindedin.learning.entity.ReservationEntity;
import com.lindedin.learning.model.request.ReservationRequest;

public class ReservationRequestToReservationEntityConverter
		implements Converter<ReservationRequest, ReservationEntity> {

	@Override
	public ReservationEntity convert(ReservationRequest source) {
		// TODO Auto-generated method stub
		ReservationEntity reservationEntity = new ReservationEntity();
		reservationEntity.setCheckin(source.getCheckin());
		reservationEntity.setCheckout(source.getCheckout());
		if (null != source.getId())
			reservationEntity.setId(source.getId());

		return reservationEntity;
	}

}
