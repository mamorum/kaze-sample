package sample.sql2o.model;

import java.sql.Timestamp;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Tsubuyaki {

	public long id;

	@NotEmpty @Size(max=255)
	public String txt;
	
	public Timestamp updatedTime, createdTime;
}
