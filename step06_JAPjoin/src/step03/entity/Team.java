package step03.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@SequenceGenerator(name="TEAM_SEQ_GEN", initialValue=1, 
sequenceName="TEAM_ID_SEQ")
public class Team {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEAM_SEQ_GEN")
	@Column(name="team_id")
	private Long teamId;
	
	@Column(length=20, name  = "team_name")
	private String teamName;
	
	@OneToMany(mappedBy="teamId")
	private List<Member> members;
	
	public int getMembersCount() {
		return members.size();
	}
}
