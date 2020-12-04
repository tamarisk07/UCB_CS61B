public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int N = in.readInt();
		in.readDouble();
		Planet[] PlanetArray = new Planet[N];
		for(int i = 0; i < N; i += 1){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			PlanetArray[i] = new Planet(xP, yP, xV, yV, m, img);
		}
		return PlanetArray;
	}

	public static void main(String[] args){
		//read in the parameters
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double RofUniverse = NBody.readRadius(filename);
		Planet[] Planets = NBody.readPlanets(filename); 

		//draw the background
		String imgToDraw = "images/starfield.jpg";
		StdDraw.setScale(-RofUniverse, RofUniverse);
		StdDraw.clear();
		StdDraw.picture(0, 0, imgToDraw);

		//draw all of the planets
		for(Planet p:Planets){
			p.draw();
		}

		//animate the universe
		StdDraw.enableDoubleBuffering();
		double time = 0;
		while(time < T){
			double[] xForces = new double[Planets.length];
			double[] yForces = new double[Planets.length];
			for(int i = 0; i < Planets.length; i += 1){
				xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
				yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
			}
			for(int i = 0; i < Planets.length; i += 1){
				Planets[i].update(dt, xForces[i], yForces[i]);
			}
			//draw the picture again
			StdDraw.clear();
			StdDraw.picture(0, 0, imgToDraw);
			for(Planet p:Planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		//print out the universe
		System.out.println(Planets.length);
		System.out.print(RofUniverse);
		for(int i = 0; i < Planets.length; i += 1){
			System.out.print("\n");
			System.out.print(Planets[i].xxPos + " ");
			System.out.print(Planets[i].yyPos + " ");
			System.out.print(Planets[i].xxVel + " ");
			System.out.print(Planets[i].yyVel + " ");
			System.out.print(Planets[i].mass + " ");
			System.out.print(Planets[i].imgFileName);
		}
	}
}