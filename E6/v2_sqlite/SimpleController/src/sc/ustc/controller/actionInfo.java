package sc.ustc.controller;

import java.util.HashMap;
import java.util.Map;

public class actionInfo {
	private String actionName;
	private String actionMethod;
	private String actionClass;
	private Map<String, resultInfo> resultInfos = new HashMap<String, resultInfo>();
	private Map<String, interceptorInfo> actionInterceptors;
	public actionInfo() {

    }


	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public Map<String, resultInfo> getResultInfos() {
		return resultInfos;
	}

	public void setResultInfos(Map<String, resultInfo> resultInfos) {
		this.resultInfos = resultInfos;
	}


	public Map<String, interceptorInfo> getInterInfos() {
		return actionInterceptors;
	}

	public void setInterInfos(Map<String, interceptorInfo> interInfos) {
		this.actionInterceptors = interInfos;
	}




}
