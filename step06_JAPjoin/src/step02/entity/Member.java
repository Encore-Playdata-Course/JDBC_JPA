package step02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

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
@SequenceGenerator(name="member_seq_gen", sequenceName="member_seq_id",
					initialValue=1, allocationSize=50)
public class Member {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="member_seq_gen")
	@Column(name="member_id")
	private Long MemberId;
	
	@Column(length=20)
	private String name;
	
	private int age;
	
	@OneToOne
	@JoinColumn(name="team_id")
	private Team teamId;
}
