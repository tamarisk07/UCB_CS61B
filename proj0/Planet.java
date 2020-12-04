public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double g =6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
	}

	public double calcForceExertedBy(Planet p) {
		return Planet.g * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
	}

	public double calcForceExertedByX(Planet p) {
		double dx = p.xxPos - this.xxPos;
		return this.calcForceExertedBy(p) * dx / this.calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		double dy = p.yyPos - this.yyPos;
		return this.calcForceExertedBy(p) * dy / this.calcDistance(p);
	}


	public double calcNetForceExertedByX(Planet[] ps) {
		double res = 0;
		for (Planet p:ps) {
			if(this.equals(p)) {
				continue;
			} else {
				res = res + this.calcForceExertedByX(p);
			}
		}
		return res;
	}

	public double calcNetForceExertedByY(Planet[] ps) {
		double res = 0;
		for (Planet p:ps) {
			if(this.equals(p)) {
				continue;
			} else {
				res = res + this.calcForceExertedByY(p);
			}
		}
		return res;
	}

	public void update(double dt, double fx, double fy) {
		this.xxVel = this.xxVel + dt * fx / this.mass;
		this.yyVel = this.yyVel + dt * fy / this.mass;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}



}