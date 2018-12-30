package sc.ustc.dao;

import java.util.Map;

public class proInfo {
	private Map<String, String> jpros;
	private class_pro_table cpro_tables;
	private Map<String, class_pro_colu> cpro_colus;
	
	public Map<String, String> getJpros() {
		return jpros;
	}
	public void setJpros(Map<String, String> jpros) {
		this.jpros = jpros;
	}
	public class_pro_table getCpro_table() {
		return cpro_tables;
	}
	public void setCpro_table(class_pro_table cpro_tables) {
		this.cpro_tables = cpro_tables;
	}
	public Map<String, class_pro_colu> getCpro_colus() {
		return cpro_colus;
	}
	public void setCpro_colus(Map<String, class_pro_colu> cpro_colus) {
		this.cpro_colus = cpro_colus;
	}
}
