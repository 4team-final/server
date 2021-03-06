package com.douzone.server.config.socket.vehicle;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class VehicleSocketHandler extends TextWebSocketHandler {
	private final ObjectMapper objectMapper;
	private final VehicleSocketService service;

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("{}", payload);
		VehicleSocketDTO vehicleSocketDTO = objectMapper.readValue(payload, VehicleSocketDTO.class);

		VehicleRoomDTO room = service.findRoomById(vehicleSocketDTO.getUid());
		room.handlerActions(session, vehicleSocketDTO, service);
	}
}
