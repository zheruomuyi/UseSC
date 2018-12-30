package sc.ustc.controller;

import java.util.Map;

public class confInfo {
	private Map<String, actionInfo> actionInfos;
	private Map<String, interceptorInfo> interInfos;
	private static String actionName1;
	public Map<String, interceptorInfo> getInterInfos() {
		return interInfos;
	}
	public void setInterInfos(Map<String, interceptorInfo> interInfos) {
		this.interInfos = interInfos;
	}
	public Map<String, actionInfo> getActionInfos() {
		return actionInfos;
	}
	public void setActionInfos(Map<String, actionInfo> actionInfos) {
		this.actionInfos = actionInfos;
	}
	public  String getActionName1() {
		return actionName1;
	}
	public void setActionName1(String actionName1) {
		confInfo.actionName1 = actionName1;
	}

}
