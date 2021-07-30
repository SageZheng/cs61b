public class NBody {

    public static double readRadius(String path) {
        In in = new In(path);//path = address
        in.readInt();//the first word is useless
        //return in.readDouble();//just return is ok
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path) {//pretty nice!
        In in = new In(path);
        int num = in.readInt();
        Planet[] planets = new Planet[num];
        in.readDouble();//radius
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();//read each value
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);//construct
            planets[i] = planet;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        //System.out.println(T);
        double dt = Double.parseDouble(args[1]);//命令行参数
      //  System.out.println(dt);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int n = planets.length;

        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet planet : planets) {
            planet.draw();
        }

        for (double time = 0; time < T; time += dt) {
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for (int i = 0; i < n; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < n; i++)
                planets[i].update(dt, xForces[i], yForces[i]);

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos,
                    planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }

}