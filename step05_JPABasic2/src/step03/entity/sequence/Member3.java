//step02 실습 - 컬럼 설정 확장

package step03.entity.sequence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//step03 - 사용자 정의 시퀀스명 설정하는 속성 추가
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name="MEMBER_SEQ_GEN",sequenceName="MEMBER_SEQ",
					initialValue=50)
					
public class Member3 {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,
					generator="MEMBER_SEQ_GEN") //시퀀스 반영하는 방법
	private long id;
	
	@Column(length=20, nullable=false) //NOT NULL VARCHAR2(20)
	private String name;
	
	@Column(length=6, nullable=false)
	private String gender;
	
	@Column(nullable=false) //NOT NULL NUMBER(10)
	private int age;
	
}

/*
@Id
private long id;
	
@Column(length=20)
private String name; 
 
 
 ID                     NOT NULL NUMBER(19)
 NAME                            VARCHAR2(20)
 */
