/*
create table Member (
   id varchar2(255) not null,
    age number(10,0),
    name varchar2(255),
    primary key (id)
)
 */

package step01.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity 
public class Member {
	@Id  //pk로 설정
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String userName;

	// 정수 10자리 타입 의미
	@Column(precision = 10)
	private int age;

}
