package kaze.sample.rdb.model;

import java.sql.Timestamp;

import javax.validation.constraints.Size;

public class Memo {
	
  public long id;

  @Size(min=1, max=200)
  public String txt;
	
  public Timestamp updated, created;
}
