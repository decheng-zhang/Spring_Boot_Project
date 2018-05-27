package com.lindedin.learning.model.response;

import java.time.LocalDate;

public class ReservationResponse {

	private Long id;
	private LocalDate checkin;
	private LocalDate checkout;

	public ReservationResponse() {
		super();
	}

	public ReservationResponse(Long id, LocalDate checkin, LocalDate checkout) {
		super();
		this.id = id;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public LocalDate getCheckin() {
		return checkin;
	}

	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}

	public LocalDate getCheckout() {
		return checkout;
	}

	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}

	public void setId(Long id) {
		this.id=id;
		
	}

	public Long getId() {
		return id;
	}

}
