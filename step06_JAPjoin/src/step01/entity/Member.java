package step01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Entity
public class Member {
	@Id
	@GeneratedValue
	@Column(name="member_id")
	private Long MemberId;
	
	@Column(length=20)
	private String name;
	
	private int age;
	
//	@Column(name="team_id")
	@ManyToOne
	@JoinColumn(name="team_id")
	private Team team;
}
