package doctolib_service.data.jpa.utils.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;

public class Comp {
	 public String method;
	    public int compNum;
	    public boolean rsvp;
	    public String name;
	    public String loc;
	    public List<Alarm> alarm;
	    public boolean noBlob;
	    public String fba;
	    public String fb;
	    public String transp;
	    public Or or;
	    public String url;
	    public boolean isOrg;
	    public String x_uid;
	    public String uid;
	    public int seq;
	    public long d;
	    public String calItemId;
	    public String apptId;
	    public String ciFolder;
	    public String status;
	  
	    public List<S> s;
	    public List<E> e;
		public String getMethod() {
			return method;
		}
		public void setMethod(String method) {
			this.method = method;
		}
		public int getCompNum() {
			return compNum;
		}
		public void setCompNum(int compNum) {
			this.compNum = compNum;
		}
		public boolean isRsvp() {
			return rsvp;
		}
		public void setRsvp(boolean rsvp) {
			this.rsvp = rsvp;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getLoc() {
			return loc;
		}
		public void setLoc(String loc) {
			this.loc = loc;
		}
		public List<Alarm> getAlarm() {
			return alarm;
		}
		public void setAlarm(List<Alarm> alarm) {
			this.alarm = alarm;
		}
		public boolean isNoBlob() {
			return noBlob;
		}
		public void setNoBlob(boolean noBlob) {
			this.noBlob = noBlob;
		}
		public String getFba() {
			return fba;
		}
		public void setFba(String fba) {
			this.fba = fba;
		}
		public String getFb() {
			return fb;
		}
		public void setFb(String fb) {
			this.fb = fb;
		}
		public String getTransp() {
			return transp;
		}
		public void setTransp(String transp) {
			this.transp = transp;
		}
		public Or getOr() {
			return or;
		}
		public void setOr(Or or) {
			this.or = or;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public boolean isOrg() {
			return isOrg;
		}
		public void setOrg(boolean isOrg) {
			this.isOrg = isOrg;
		}
		public String getX_uid() {
			return x_uid;
		}
		public void setX_uid(String x_uid) {
			this.x_uid = x_uid;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public int getSeq() {
			return seq;
		}
		public void setSeq(int seq) {
			this.seq = seq;
		}
		public long getD() {
			return d;
		}
		public void setD(long d) {
			this.d = d;
		}
		public String getCalItemId() {
			return calItemId;
		}
		public void setCalItemId(String calItemId) {
			this.calItemId = calItemId;
		}
		public String getApptId() {
			return apptId;
		}
		public void setApptId(String apptId) {
			this.apptId = apptId;
		}
		public String getCiFolder() {
			return ciFolder;
		}
		public void setCiFolder(String ciFolder) {
			this.ciFolder = ciFolder;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public List<S> getS() {
			return s;
		}
		public void setS(List<S> s) {
			this.s = s;
		}
		public List<E> getE() {
			return e;
		}
		public void setE(List<E> e) {
			this.e = e;
		}
	    
	    
	    
}
