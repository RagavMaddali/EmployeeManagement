package com.project.employee.storage;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageStorage {
  
  private Map<Integer,String> storage = new HashMap<Integer,String>();
  
  public void put(Integer id, String message){
    storage.put(id, message);
  }
  
   
  public String get(Integer id)
  {
	  if(storage.containsKey(id))
	  {
		  return storage.get(id);
	  }
	  else
	  {
		  return null;
	  }
	  
  }
  
  public void clear(){
    storage.clear();
  }
}
