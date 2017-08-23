package net.sqlitetutorial;

import java.io.Serializable;

public interface RequestContext extends Serializable {
	String getId();
	String[] getIds();
	ApplicationSession getApplicationSession();
	void setApplicationSession(ApplicationSession applicationSession);
}
