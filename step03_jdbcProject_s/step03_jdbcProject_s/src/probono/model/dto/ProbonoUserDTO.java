package probono.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data

public class ProbonoUserDTO {
	private int prouserId;
	private String probonoId;
	private String activistId; 
	private String receiveId;
	private String projectContent;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProbonoUserDTO [prouserId=");
		builder.append(prouserId);
		builder.append(", probonoId=");
		builder.append(probonoId);
		builder.append(", activistId=");
		builder.append(activistId);
		builder.append(", receiveId=");
		builder.append(receiveId);
		builder.append(", projectContent=");
		builder.append(projectContent);
		builder.append("]");
		return builder.toString();
	}
	
	
}
