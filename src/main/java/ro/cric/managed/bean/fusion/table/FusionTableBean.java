package ro.cric.managed.bean.fusion.table;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "fusionTableBean")
public class FusionTableBean {

	private String console = "http://google.org/crisismap/a/gmail.com/crictw?hl=en&amp;llbox=84.1%2C-84.1%2C180%2C-180&amp;t=HYBRID&amp;layers=2%2C1&amp;embedded=true";

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}
}