package step02.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;

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
@SequenceGenerator(name="team_seq_gen", sequenceName="member_seq_id",
					initialValue=1,allocationSize=50)
public class Team {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="team_seq_gen")
	@Column(name="team_id")
	private long teamId;
	
	@Column(length=20)
	private String teamName;
}
