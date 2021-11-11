package doctolib_service.data.jpa.utils.model;

import java.util.List;

public class Tz {
	public String id;
    public int stdoff;
    public String stdname;
    public int dayoff;
    public String dayname;
    public List<Standard> standard;
    public List<Daylight> daylight;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getStdoff() {
		return stdoff;
	}
	public void setStdoff(int stdoff) {
		this.stdoff = stdoff;
	}
	public String getStdname() {
		return stdname;
	}
	public void setStdname(String stdname) {
		this.stdname = stdname;
	}
	public int getDayoff() {
		return dayoff;
	}
	public void setDayoff(int dayoff) {
		this.dayoff = dayoff;
	}
	public String getDayname() {
		return dayname;
	}
	public void setDayname(String dayname) {
		this.dayname = dayname;
	}
	public List<Standard> getStandard() {
		return standard;
	}
	public void setStandard(List<Standard> standard) {
		this.standard = standard;
	}
	public List<Daylight> getDaylight() {
		return daylight;
	}
	public void setDaylight(List<Daylight> daylight) {
		this.daylight = daylight;
	}
    
    
}

