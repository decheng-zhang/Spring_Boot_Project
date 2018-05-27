package com.lindedin.learning.converter;

import org.springframework.core.convert.converter.Converter;

import com.lindedin.learning.entity.RoomEntity;
import com.lindedin.learning.model.Links;
import com.lindedin.learning.model.Self;
import com.lindedin.learning.model.response.ReservableRoomResponse;
import com.lindedin.learning.rest.ResourceConstants;

public class RoomEntityToReservationResponseConverter implements Converter<RoomEntity, ReservableRoomResponse>{

	@Override
	public ReservableRoomResponse convert(RoomEntity source) {
		
		ReservableRoomResponse reservationResponse =new ReservableRoomResponse();
		reservationResponse.setRoomNumber(source.getRoomNumber());
		reservationResponse.setPrice(Integer.valueOf(source.getPrice()));
		
		Links links= new Links();
		Self self=new Self();
		self.setRef(ResourceConstants.ROOM_RESERVATION_V1+ "/" + source.getId());
		reservationResponse.setLinks(links);
		return reservationResponse;
	}

}
