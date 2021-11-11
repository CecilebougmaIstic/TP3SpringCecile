package doctolib_service.data.jpa.utils.model;

import java.util.List;

public class Alarm {
	 public String action;
	 public List<Trigger> trigger;
	 public List<Desc> desc;
	 public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public List<Trigger> getTrigger() {
			return trigger;
		}
		public void setTrigger(List<Trigger> trigger) {
			this.trigger = trigger;
		}
		public List<Desc> getDesc() {
			return desc;
		}
		public void setDesc(List<Desc> desc) {
			this.desc = desc;
		}
	    
	    
}
