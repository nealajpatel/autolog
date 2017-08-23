package net.sqlitetutorial;

import java.io.Serializable;
import java.util.List;

public interface ApplicationSession extends Serializable{
	void setAttribute(Serializable key, Serializable value);
	Serializable getAttribute(Serializable key);
	List keySet();
}
