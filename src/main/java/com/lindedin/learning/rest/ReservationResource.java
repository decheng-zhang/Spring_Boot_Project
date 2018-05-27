package com.lindedin.learning.rest;



import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Function;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lindedin.learning.config.ConversionConfig;
import com.lindedin.learning.entity.ReservationEntity;
import com.lindedin.learning.entity.RoomEntity;
import com.lindedin.learning.model.Links;
import com.lindedin.learning.model.Self;
import com.lindedin.learning.model.request.ReservationRequest;
import com.lindedin.learning.model.response.ReservableRoomResponse;
import com.lindedin.learning.model.response.ReservationResponse;
import com.lindedin.learning.repository.PageableRoomRepository;
import com.lindedin.learning.repository.ReservationRepository;
import com.lindedin.learning.repository.RoomRepository;



@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {
	
	@Autowired
	PageableRoomRepository pageableRoomRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	ConversionService conversionService;
		
	
	
	@RequestMapping(path="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<ReservableRoomResponse> getAvailableRooms(
			
			@RequestParam(value="checkin")
			@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
			LocalDate checkin, 
			
			@RequestParam(value="checkout")
			@DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
			LocalDate checkout, Pageable pageable)
	{
		Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);
		
		//RoomEntityToReservationResponseConverter conver= new RoomEntityToReservationResponseConverter();
		//CAREFULL: Spring Data2 takes a FUNCTION instead of a CONVERTER
		return roomEntityList.map(new Function<RoomEntity, ReservableRoomResponse> (){
			
			@Override
			public ReservableRoomResponse apply(RoomEntity source) {
			ReservableRoomResponse reservationResponse =new ReservableRoomResponse();
			reservationResponse.setRoomNumber(source.getRoomNumber());
			reservationResponse.setPrice(Integer.valueOf(source.getPrice()));
			reservationResponse.setId(source.getId());
			Links links= new Links();
			Self self=new Self();
			self.setRef(ResourceConstants.ROOM_RESERVATION_V1+ "/" + source.getId());
			links.setSelf(self);
			reservationResponse.setLinks(links);
			return reservationResponse;
		
		
			}
			
			
		});
				
	}
	@RequestMapping(path="/{roomId}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RoomEntity> getRoomById(
		@PathVariable
		Long roomId){
		
		Optional<RoomEntity> roomEntity =roomRepository.findById(roomId);
		return new ResponseEntity<>(roomEntity.get(), HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(path="",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservationResponse> createReservation(
			@RequestBody
			ReservationRequest reservationRequest){
		//assertTrue(conversionService.canConvert(ReservationRequest.class, ReservationEntity.class));
		ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class);
		reservationRepository.save(reservationEntity);
		
		RoomEntity roomEntity = roomRepository.findById(reservationRequest.getRoomId()).get();
		roomEntity.addReservationEntity(reservationEntity);
		roomRepository.save(roomEntity);
		reservationEntity.setRoomEntity(roomEntity);
		ReservationResponse reservationResponse=conversionService.convert(reservationEntity, ReservationResponse.class);	
		
		return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
	}
	
	@RequestMapping(path="",method=RequestMethod.PUT,produces=MediaType.APPLICATION_JSON_UTF8_VALUE,
			consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ReservableRoomResponse> updateReservation(
			@RequestBody
			ReservationRequest reservationRequest){
		return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
	}
	
	@RequestMapping(path="/{reservationId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteReservation(
			@PathVariable
			long reservationId){
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	
			
			
			
			
			
		
		
	
	
	
	
}
