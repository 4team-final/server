package com.douzone.server.dto.vehicle;

import com.douzone.server.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDateResDTO implements IVehicleDateResDTO {
	private LocalDateTime endedAt;
	private Vehicle vehicle;
}
