package com.douzone.server.config.socket;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "time")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Time {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private Calendar calendar;

	private String time;

	private Integer isSeat;

	private String empNo;

	private Integer roomId;

	public void updateIsSeat(Integer isSeat, String empNo) {
		this.isSeat = isSeat;
		this.empNo = empNo;
	}
}
