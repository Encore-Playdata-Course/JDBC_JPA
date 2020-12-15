/**
create table Customer (
   id varchar2(255) not null,
    age number(10,0),
    name varchar2(255),
    primary key (id)
)
*/

package step01.entity;

//JPA의 api
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
@Entity   //table 생성 의미 
public class Customer {
	@Id   //table에 생성하는 컬럼중 id라는 변수를 pk로 설정 의미
	@Column(name="id")  //table 의 id컬럼과 매핑되는 변수라는 의미
	private String id;
	
	@Column(name="name")   //매핑된 멤버 변수명은 userName이지만 컬럼명 name, 
	private String userName;
	
	//정수 10자리 타입 의미
	@Column(precision=10)  //매핑된 컬럼명은 age로 하나, 사이즈는 정수만을 표현하는 number(10,0)
	private int age;
   
}  

