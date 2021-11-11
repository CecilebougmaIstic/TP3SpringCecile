package doctolib_service.data.jpa.utils.model;

public class Rel {
	public boolean neg;
    public int m;
    public String related;
	public boolean isNeg() {
		return neg;
	}
	public void setNeg(boolean neg) {
		this.neg = neg;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	public String getRelated() {
		return related;
	}
	public void setRelated(String related) {
		this.related = related;
	}
    
    
}
