package doctolib_service.data.jpa.utils.model;

import java.util.List;

public class Appt {
	 public String uid;
	    public String id;
	    public String l;
	    public int rev;
	    public int s;
	    public long d;
	    public List<Inv> inv;
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getL() {
			return l;
		}
		public void setL(String l) {
			this.l = l;
		}
		public int getRev() {
			return rev;
		}
		public void setRev(int rev) {
			this.rev = rev;
		}
		public int getS() {
			return s;
		}
		public void setS(int s) {
			this.s = s;
		}
		public long getD() {
			return d;
		}
		public void setD(long d) {
			this.d = d;
		}
		public List<Inv> getInv() {
			return inv;
		}
		public void setInv(List<Inv> inv) {
			this.inv = inv;
		}
	    
	    
}
