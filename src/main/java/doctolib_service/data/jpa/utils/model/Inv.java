package doctolib_service.data.jpa.utils.model;

import java.util.List;

public class Inv {
	 public String type;
	    public List<Tz> tz;
	    public int seq;
	    public int id;
	    public int compNum;
	    public List<Comp> comp;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public List<Tz> getTz() {
			return tz;
		}
		public void setTz(List<Tz> tz) {
			this.tz = tz;
		}
		public int getSeq() {
			return seq;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCompNum() {
			return compNum;
		}
		public void setCompNum(int compNum) {
			this.compNum = compNum;
		}
		public List<Comp> getComp() {
			return comp;
		}
		public void setComp(List<Comp> comp) {
			this.comp = comp;
		}
	    
	    
}
